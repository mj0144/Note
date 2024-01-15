package app.note;

import app.note.dao.BoardRepository;
import app.note.entity.Board;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@RunWith(SpringRunner.class)
class NoteApplicationTests {

    @Autowired
    BoardRepository boardRepository;

    @Test
    @Transactional
    void 초기_Insert테스트() {

        //Board board = new Board();
        Board board = Board.builder()
                .title("테스트 제목")
                .content("테스트내용")
                .build();

        boardRepository.save(board);

        List<Board> all = boardRepository.findAll();

        // TODO : all 순회.

    }

}
