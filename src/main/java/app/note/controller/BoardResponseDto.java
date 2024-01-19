package app.note.controller;

import app.note.embeded.BaseTime;
import app.note.entity.Board;
import app.note.embeded.BaseUser;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardResponseDto {

    private long id;
    private String title;
    private String content;
    private BaseTime baseTime;
    private BaseUser baseUser;

    public BoardResponseDto() {
    }

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.baseTime = board.getBaseTime();
        this.baseUser = board.getBaseUser();
    }

}
