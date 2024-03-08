package app.note.controller;


import app.note.entity.User;
import app.note.service.UserService;
import lombok.AllArgsConstructor;
import org.hibernate.jdbc.Expectation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {

    private final UserService userService;


    // 회원가입
    @PostMapping("/users")
    public ResponseEntity<Boolean> saveUser(UserRequestDto userRequestDto) throws Exception {
        return new ResponseEntity<>(userService.register(userRequestDto), HttpStatus.OK);
//        User user = .orElseThrow();

    }


    // 로그인

    // 개인정보 수정

    // TODO : 탈퇴


}