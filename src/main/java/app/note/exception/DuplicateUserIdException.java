package app.note.exception;

import app.note.error.ErrorCode;
import lombok.Getter;

@Getter
public class DuplicateUserIdException extends Exception {

    public DuplicateUserIdException(String msg) {
        super(msg);
    }
}
