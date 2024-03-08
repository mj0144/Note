package app.note.entity;

import app.note.embeded.BaseTime;
import app.note.embeded.BaseUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Entity
@Table(name = "board")
@NoArgsConstructor
public class Board extends BaseUser {

    @Id @GeneratedValue
    @Column(name = "board_id")
    private long id;

    @NotEmpty(message = "제목은 필수값")
    private String title;

    @NotEmpty(message = "내용은 필수값")
    private String content;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @Builder
    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void updateContentTitle(String content, String title) {
        this.content = content;
        this.title = title;
    }

}
