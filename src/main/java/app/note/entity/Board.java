package app.note.entity;

import app.note.embeded.Period;
import app.note.embeded.Writer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Setter
@Table(name = "board")
@NoArgsConstructor
public class Board {

    @Id @GeneratedValue
    @Column(name = "board_id")
    private long id;

    @NotEmpty(message = "제목은 필수값")
    private String title;

    @NotEmpty(message = "내용은 필수값")
    private String content;

    /**
     * 값타입
     */
    @Embedded
    private Period period; // TODO : 자동으로 세팅되도록 수정
    @Embedded
    private Writer writer; // TODO : 기본값으로 세팅되도록 수정

    @Builder
    public Board(String title, String content, String id_frt) {
        this.title = title;
        this.content = content;
    }

    public void updateContentTitle(String content, String title) {
        this.content = content;
        this.title = title;
    }

}
