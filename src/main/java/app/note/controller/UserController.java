package app.note.controller;


import app.note.entity.User;
import app.note.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입 : 기존 회원인 경우 기존 회원 데이터 리턴.
    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody UserRequestDto userRequestDto) throws Exception {

        User user = userService.findByUserId(userRequestDto.getUserId());
        if (user == null) {
            return new ResponseEntity<>(new UserResponseDto(userService.register(userRequestDto)), HttpStatus.OK);
        }

        return new ResponseEntity<>(new UserResponseDto(userService.findByUserId(userRequestDto.getUserId())), HttpStatus.OK);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UserRequestDto userRequestDto) throws Exception {
        return new ResponseEntity<>(userService.login(userRequestDto) , HttpStatus.OK);
    }

    // TODO : 개인정보 수정
    // TODO : 탈퇴


}
