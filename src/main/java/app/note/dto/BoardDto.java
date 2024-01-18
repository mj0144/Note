package app.note.dto;

import app.note.embeded.Period;
import app.note.embeded.Writer;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDto {
    private long id;

    private String title;
    private String content;
    private Period period;
    private Writer writer;

    @Builder
    public BoardDto(String title, String content, String id_frt) {
        this.title = title;
        this.content = content;

    }

    public BoardDto() {
    }
}
