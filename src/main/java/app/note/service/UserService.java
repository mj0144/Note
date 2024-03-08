package app.note.service;


import app.note.controller.UserRequestDto;
import app.note.dao.UserSpringDataRepository;
import app.note.entity.Authority;
import lombok.AllArgsConstructor;
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

    public Boolean register(UserRequestDto userRequestDto) throws Exception {
        try {
            User user = User.builder()
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
            throw new Exception("잘못된 요청");
        }

        return true;


    }
}
