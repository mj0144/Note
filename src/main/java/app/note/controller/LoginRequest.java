package app.note.controller;

import app.note.entity.Gender;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class LoginRequest {

    private String userId;
    private String passwd;
    private String name;
    private LocalDate brith;
    private Gender gender;


}
