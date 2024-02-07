package app.note.error;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorResponse {

    private String errorCode;
    private String msg;
    private int status;
    private LocalDateTime time;


}
