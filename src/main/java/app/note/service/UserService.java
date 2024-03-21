package app.note.service;

import app.note.controller.LoginResponse;
import app.note.controller.UserRequestDto;
import app.note.dao.UserSpringDataRepository;
import app.note.exception.DuplicateUserIdException;
import app.note.security.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import app.note.entity.User;

import java.util.*;


@Service
@AllArgsConstructor
public class UserService {

    private final UserSpringDataRepository userSpringDataRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public User register(UserRequestDto userRequestDto) throws Exception {
            // 기존 회원찾기.
        userSpringDataRepository.findByUserId(userRequestDto.getUserId())
                .ifPresent(user -> new DuplicateUserIdException("잘못된 요청"));

        User user = User.builder()
                .userId(userRequestDto.getUserId())
                .name(userRequestDto.getName())
                .passwd(passwordEncoder.encode(userRequestDto.getPasswd()))
                .gender(userRequestDto.getGender())
                .brith(userRequestDto.getBrith())
                .roles(Arrays.asList("ROLE_USER"))
                .build();

        return userSpringDataRepository.save(user);
    }

    public LoginResponse login(UserRequestDto userRequestDto) throws Exception {
        User user = userSpringDataRepository.findByUserId(userRequestDto.getUserId())
                .orElseThrow(() -> new BadCredentialsException("아이디가 잘못되었습니다."));

        if (!passwordEncoder.matches(userRequestDto.getPasswd(), user.getPasswd())) {
            throw new BadCredentialsException("비밀번호가 잘못되었습니다.");
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

    public User findByUserId(String userId) {
        return userSpringDataRepository.findByUserId(userId).orElse(null);
    }




}
