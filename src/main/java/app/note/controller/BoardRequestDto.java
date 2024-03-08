package app.note.controller;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BoardRequestDto {
    private String title;
    private String content;

    public BoardRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
