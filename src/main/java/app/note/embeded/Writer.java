package app.note.embeded;

import jakarta.persistence.Embeddable;

@Embeddable
public class Writer {
    private String idFrt;
    private String idLst;

    public Writer() {
    }
}
