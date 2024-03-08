package app.note.controller;

import app.note.embeded.BaseTime;
import app.note.entity.Board;
import app.note.embeded.BaseUser;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardResponseDto extends BaseUser {

    private long id;
    private String title;
    private String content;

    public BoardResponseDto() {
    }

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        setCreatedDate(board.getCreatedDate());
        setLastModifiedDate(board.getLastModifiedDate());
        setCreateBy(board.getCreateBy());
        setLastModifiedBy(board.getLastModifiedBy());
    }

}
