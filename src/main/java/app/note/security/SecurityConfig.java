package app.note.security;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.DebugBeanDefinitionParser;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.io.IOException;
import java.util.List;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        System.out.println("secyrity call");
        // httpBasic은 7.0버전에서 제거. 람다식 지향.
        http
                .httpBasic(Customizer.withDefaults())
                .csrf((csrf) -> csrf.disable()) // csrf 비활서화
                // CORS 설정
//                .cors(c -> {
//                            CorsConfigurationSource source = request -> {
//                                // Cors 허용 패턴
//                                CorsConfiguration config = new CorsConfiguration();
//                                config.setAllowedOrigins(
//                                        List.of("*")
//                                );
//                                config.setAllowedMethods(
//                                        List.of("*")
//                                );
//                                return config;
//                            };
//                            c.configurationSource(source);
//                        }
//                )
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .sessionManagement(httpSecuritySessionManagementConfigurer -> {
                    httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .authorizeHttpRequests(authorize -> authorize
                                .requestMatchers(HttpMethod.POST, "/api/**").permitAll()
//                                .anyRequest().denyAll()
//                        .requestMatchers("/users/**").hasRole("USER")
//                        .anyRequest().authenticated()
                )
                .exceptionHandling((exceptionHandling ) ->
                        exceptionHandling
                                .authenticationEntryPoint((request, response, authException) -> { // 인증 문제
                                    response.setStatus(401);
                                    response.setCharacterEncoding("utf-8");
                                    response.setContentType("text/html; charset=UTF-8");
                                    response.getWriter().write("인증되지 않은 사용자");
                                })
                                .accessDeniedHandler((request, response, accessDeniedException) -> { // 권한 문제
                                        response.setStatus(403);
                                        response.setCharacterEncoding("utf-8");
                                        response.setContentType("text/html; charset=UTF-8");
                                        response.getWriter().write("권한이 없는 사용자.");
                                    }
                                )
                );
//                .rememberMe(Customizer.withDefaults()); // 브라우저 종료후에도 자동으로 로그인 상태 재활성.

        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    @Bean
    public AuthenticationFilter authenticationFilter() {
        return new AuthenticationFilter();
    }

}
