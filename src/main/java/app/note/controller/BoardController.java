package app.note.controller;

import app.note.embeded.Period;
import app.note.embeded.Writer;
import app.note.entity.Board;
import app.note.service.BoardService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/api/board")
    public List<ResponseEntity> findAll() {
        List<Board> list = boardService.findAll();
        return list.stream().map(o -> new ResponseEntity(o))
                .collect(Collectors.toList());
    }


}

// response dto 랑 엔티티랑 항목이 같아도 따로 뺴느게 좋겟지.
// 명세가 언제 어떻게 바뀔지 모르니깐
@Data
class ResponseEntity {
    private long id;
    private String title;
    private String content;
    private Period period;
    private Writer writer;

    public ResponseEntity(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.period = board.getPeriod();
        this.writer = board.getWriter();
    }
}