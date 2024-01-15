package app.note.embeded;

import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;

@Embeddable
public class Period {

    private LocalDateTime dtFrt;
    private LocalDateTime dtLst;

    public Period() {
    }
}
