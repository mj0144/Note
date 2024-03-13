package app.note.service;


import app.note.controller.LoginRequest;
import app.note.controller.LoginResponse;
import app.note.controller.UserRequestDto;
import app.note.dao.UserSpringDataRepository;
import app.note.entity.Authority;
import app.note.security.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import app.note.entity.User;

import java.util.Collections;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserService {

    private final UserSpringDataRepository userSpringDataRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public Boolean register(UserRequestDto userRequestDto) throws Exception {
        try {
            User user = User.builder()
                    .userId(userRequestDto.getUserId())
                    .name(userRequestDto.getName())
                    .passwd(passwordEncoder.encode(userRequestDto.getPasswd()))
                    .gender(userRequestDto.getGender())
                    .brith(userRequestDto.getBrith())
                    .build();

            user.setRoles(Collections.singletonList(Authority.builder()
                    .name("ROLE_USER")
                    .build())
            );
            //        return Optional.of(userSpringDataRepository.save(user));
            userSpringDataRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("잘못된 요청");
        }

        return true;
    }

    public LoginResponse login(LoginRequest loginRequest) throws Exception{
        User user = userSpringDataRepository.findByUserId(loginRequest.getUserId())
                .orElseThrow(() -> new BadCredentialsException("없는 아이디"));

        if (!passwordEncoder.matches(loginRequest.getPasswd(), user.getPasswd())) {
            throw new BadCredentialsException("잘못된 비밀번호");
        }

        return LoginResponse.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .gender(user.getGender())
                .brith(user.getBrith())
                .roles(user.getRoles())
                .token(jwtProvider.createToken(user.getUserId(), user.getRoles())) // 토큰 생성하여 넘기기.
                .build();

    }


}
