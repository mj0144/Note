package app.note.controller;

import app.note.entity.Gender;
import app.note.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class LoginResponse {

    private String userId;
    private String name;
    private LocalDate brith;
    private Gender gender;

    private List<String> roles = new ArrayList<>();

    private String token;


}
