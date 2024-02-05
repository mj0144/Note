package app.note.controller;

import lombok.Data;

@Data
public class BoardUpdateRequestDto {

    private long id;

    private String content;
    private String title;

    public BoardUpdateRequestDto(long id, String content, String title) {
        this.id = id;
        this.content = content;
        this.title = title;
    }
}
