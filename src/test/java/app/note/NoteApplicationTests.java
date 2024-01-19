package app.note;

import app.note.dao.BoardRepository;
import app.note.dto.BoardDto;
import app.note.dto.BoardSearchCondition;
import app.note.entity.Board;
import app.note.service.BoardService;
import jakarta.persistence.PrePersist;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class NoteApplicationTests {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    BoardService boardService;

    @PrePersist
    @Transactional
    public void init() {
        for(int i=0; i<10; i++) {
            Board board = Board.builder()
                    .title("테스트 제목"+i)
                    .content("테스트내용"+i)
                    .build();

            boardRepository.save(board);
        }


    }



    @Test
    @Transactional
    void 초기_Insert테스트() {

        //Board board = new Board();
        Board board = Board.builder()
                .title("테스트 제목")
                .content("테스트내용")
                .build();

        boardRepository.save(board);

        BoardSearchCondition boardSearchCondition = new BoardSearchCondition();
        int offset = 1;
        int limit = 2;

        List<Board> all = boardRepository.findAll(boardSearchCondition, offset, limit);

        // TODO : all 순회.

    }

    @Test
    @Transactional
    @Rollback(false)
    public void board_업데이트_테스트() {
        Board board = Board.builder()
                .title("테스트 제목")
                .content("테스트내용")
                .build();

        long saveID = boardRepository.save(board);

        BoardDto dto = new BoardDto();
        dto.setTitle("제목수정1");
        dto.setContent("내용수정1");
        dto.setId(saveID);
        boardService.update(dto);
    }


    @Test
    @Transactional
    @Rollback(value = false)
    public void board_리스트_페이징_테스트() {

    }

}
