package app.note;

import app.note.controller.BoardUpdateRequestDto;
import app.note.dao.BoardRepository;
import app.note.dto.BoardDto;
import app.note.dto.BoardSearchCondition;
import app.note.entity.Board;
import app.note.exception.NotFoundBoardException;
import app.note.service.BoardService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.PrePersist;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Transactional
    @BeforeEach
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
    @Rollback(value = false)
    @DisplayName("Board_insert_테스트")
    void 초기_Insert테스트() {

        //Board board = new Board();
        Board board = Board.builder()
                .title("테스트 제목")
                .content("테스트내용")
                .build();

        long saveId = boardRepository.save(board);
        Board byId = boardRepository.getById(saveId);
//        Assertions.assertEquals(byId.getContent(), "테스트내용1");
        Assertions.assertEquals(byId.getContent(), "테스트내용");

    }

    @Test
    @Transactional
    @Rollback(false)
    @DisplayName("board_업데이트_테스트")
    public void board_업데이트_테스트() {
        Board board = Board.builder()
                .title("테스트 제목")
                .content("테스트내용")
                .build();

        long saveID = boardRepository.save(board);

        BoardUpdateRequestDto dto = new BoardUpdateRequestDto(saveID, "제목수정1", "내용수정1");
        boardService.updateBoard(saveID, dto);

        Board byId = boardRepository.getById(saveID);

        Assertions.assertEquals(byId.getContent(), "제목수정1");
        Assertions.assertEquals(byId.getTitle(),"내용수정1");
    }


    @Test
    @Transactional
    @Rollback(value = false)
    @DisplayName("board_리스트_페이징_테스트")
    // TODO : 각 객체가 null인 경우는 없을까?
    public void board_리스트_페이징_테스트() {
//        boardRepository.findAll(null, null);
        BoardSearchCondition condition = new BoardSearchCondition();
        condition.setTitle("테스트내용1");
        boardService.getBoards(condition, null);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    @DisplayName("board_리스트_페이징_예외_offset이 음수")
    // TODO : 각 객체가 null인 경우는 없을까?
    public void board_리스트_페이징_예외() {
//        boardRepository.findAll(null, null);
        BoardSearchCondition condition = new BoardSearchCondition();
        condition.setTitle("테스트내용1");
        Pageable pageable = PageRequest.of(-1, 5);
        boardService.getBoards(condition, null);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    @DisplayName("board_리스트_페이징_예외_size기 음수")
    // TODO : 각 객체가 null인 경우는 없을까?
    public void board_리스트_페이징_예외2() {
//        boardRepository.findAll(null, null);
        BoardSearchCondition condition = new BoardSearchCondition();
        condition.setTitle("테스트내용1");
        Pageable pageable = PageRequest.of(0, -1);
        boardService.getBoards(condition, null);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    @DisplayName("board_삭제_테스트")

    public void board_삭제_테스트() {
        Board board = boardService.getBoard(1);
        boardService.deletBoard(board.getId());

        NotFoundBoardException notFoundBoardException = Assertions.assertThrows(NotFoundBoardException.class, () -> boardService.getBoard(1));
        Assertions.assertEquals("게시물이 존재하지 않습니다", notFoundBoardException.getMessage());
    }
}
