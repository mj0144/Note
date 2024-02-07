package app.note.exception;

import app.note.error.ErrorCode;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;

@Getter
public class NotFoundBoardException extends EntityNotFoundException {
    private final ErrorCode errorCode;

    public NotFoundBoardException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.errorCode = errorCode;
    }

}
