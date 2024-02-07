package app.note.error;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

@Getter
public enum ErrorCode {
    NOT_FOUND_BOARD(400, "C001", "게시물이 존재하지 않습니다");

    private final int status;
    private final String errorCode;
    private final String msg;

    ErrorCode(int status, String errorCode, String msg) {
        this.status = status;
        this.errorCode = errorCode;
        this.msg = msg;
    }

//    public ErrorCode getErrorResponse() { // enum은 builder를 만들수 없으니.. 따로 클래스를 따서 진행.
//        return ErrorCode.
//    }

    public ErrorResponse getErrorResponse() {
        return ErrorResponse.builder()
                .status(this.status)
                .errorCode(this.errorCode)
                .msg(this.msg)
                .time(LocalDateTime.now())
                .build();
    }

    public HttpStatusCode toHttpStatus() {
        return HttpStatusCode.valueOf(status);
    }
}
