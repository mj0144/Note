package app.note.controller;


import app.note.entity.Gender;
import app.note.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter @Builder
public class UserRequestDto {

    private String userId;
    private String passwd;
    private LocalDate brith;
    private String name;
    private Gender gender;


    public User toEntity() {
        return User.builder()
                .userId(userId)
                .passwd(passwd)
                .brith(brith)
                .name(name)
                .gender(gender)
                .build();
    }


}
