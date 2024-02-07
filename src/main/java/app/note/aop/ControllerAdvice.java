package app.note.aop;

import app.note.error.ErrorCode;
import app.note.error.ErrorResponse;
import app.note.exception.NotFoundBoardException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 예외 처리.
@RestControllerAdvice(annotations = RestController.class)
public class ControllerAdvice {

    @ExceptionHandler(NotFoundBoardException.class)
    public ResponseEntity<ErrorResponse> notFoundBoardException(NotFoundBoardException e) {
        ErrorCode errorCode = e.getErrorCode();
        ErrorResponse errorResponse = errorCode.getErrorResponse();

        return new ResponseEntity<>(errorResponse, errorCode.toHttpStatus());
    }


}
