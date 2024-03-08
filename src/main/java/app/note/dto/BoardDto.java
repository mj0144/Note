package app.note.dto;

import app.note.embeded.BaseTime;
import app.note.embeded.BaseUser;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDto {
    private long id;

    private String title;
    private String content;
    private BaseTime baseTime;
    private BaseUser baseUser;

    @Builder
    public BoardDto(String title, String content) {
        this.title = title;
        this.content = content;

    }

    public BoardDto() {
    }
}
