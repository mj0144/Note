package app.note.controller;

import app.note.entity.Gender;
import app.note.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import java.time.LocalDate;

@Getter
@Setter
public class UserResponseDto {
    private String userId;
    private String name;
    private LocalDate brith;
    private Gender gender;

    public UserResponseDto(User user) {
        BeanUtils.copyProperties(user, this);
    }


}
