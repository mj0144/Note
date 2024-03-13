package app.note.controller;

import app.note.entity.Authority;
import app.note.entity.Board;
import app.note.entity.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
