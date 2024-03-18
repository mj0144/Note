package app.note.security;

import app.note.entity.Authority;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class JwtProvider {

    @Value("${jwt.secret.key}") // application.yml에서 값 주입
    private String salt;

    private Key secretKey;

    // 만료시간 : 1Hour
    private final long exp = 1000L * 60 * 60;

    private final CustomerUserDetailsService userDetailsService;

    private final String jwtValue = "BEARER ";

    private final String jwtHeader = "Authorization"; // jwt 토큰 request 헤더

    // 키 생성. HS256알고리즘 사용
    @PostConstruct
    protected void init() {
        secretKey = Keys.hmacShaKeyFor(salt.getBytes(StandardCharsets.UTF_8));
    }

    // 토큰 생성
    public String createToken(String id, List<Authority> Authorites) {
        Claims claims = Jwts.claims().setSubject(id);
        String roles = Authorites.stream()
                .map(Authority::getName)
                .collect(Collectors.joining(","));

        claims.put("roles", roles);
        Date now = new Date();
        return Jwts.builder()
//                .setPayload(id)
                .setClaims(claims)
                .setIssuedAt(now) // jwt가 생성된 타임스탬프
                .setExpiration(new Date(now.getTime() + exp)) // 만료시간
                .signWith(secretKey, SignatureAlgorithm.HS256) // 서명 데이터
                .compact();
    }

    // 권한정보 획득
    // Spring Security 인증과정에서 권한확인을 위한 기능
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getAccount(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰에 담겨있는 유저 account 획득
    public String getAccount(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getSubject();
    }

    // Authorization Header를 통해 인증을 한다.
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader(jwtHeader);
    }

    // 토큰 검증 - 만료시간 기반
    public boolean validateToken(String token) {
        try {
            // Bearer 검증
            if (!token.substring(0, jwtValue.length()).equalsIgnoreCase(jwtValue)) { // JWT value의 시작은 BEARER로 시작.
                return false;
            }

            token = token.split(jwtValue)[1].trim(); // BEARER 이후 값.

            // payload의 클레임값 꺼내오기.
            Jws<Claims> claims = Jwts.parserBuilder() // 스레드 안전
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);

            // 만료되었을 시 false
            return !claims.getBody().getExpiration().before(new Date());

        } catch (Exception e) {
            return false;
        }
    }
}