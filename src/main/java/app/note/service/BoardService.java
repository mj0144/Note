package app.note.service;

import app.note.controller.BoardRequestDto;
import app.note.controller.BoardUpdateRequestDto;
import app.note.dao.BoardRepository;
import app.note.dao.BoardSpringDataRepository;
import app.note.dto.BoardDto;
import app.note.dto.BoardSearchCondition;
import app.note.entity.Board;
import app.note.error.ErrorCode;
import app.note.exception.NotFoundBoardException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardSpringDataRepository boardSpringDataRepository;

    public List<Board> getBoards(BoardSearchCondition condition, Pageable pageable) { // id기준 내림차순
        if (pageable == null) {
            Pageable tmpPageable = PageRequest.of(0, 5); // pageable 이 없는 경우 0번재 페이지부터 5개씩 가져오도록
            return boardRepository.findAll(condition, tmpPageable);
        }
        return boardRepository.findAll(condition, pageable);
    }

    //단건 조회
    public Board getBoard(long id) {
//        return boardRepository.getById(id);
        return  boardSpringDataRepository.findById(id)
                .orElseThrow(() -> new NotFoundBoardException(ErrorCode.NOT_FOUND_BOARD));
    }

    // 저장 - springData
    public Board createBoard(BoardRequestDto boardRequestDto) {
        Board board = Board.builder()
                .title(boardRequestDto.getTitle())
                .content(boardRequestDto.getContent())
                .build();
        return boardSpringDataRepository.save(board);
    }

    // 게시물 수정
    @Transactional
    public void updateBoard(long id, BoardUpdateRequestDto dto) {
        Board board = boardRepository.getById(id);
//        board = Board.builder() // 새로운 객체를 할당하는 방식이라 안됨.
//                .content(dto.getContent())
//                .title(dto.getTitle())
//                .build();

        board.updateContentTitle(dto.getContent(), dto.getTitle()); // 변경감지 사용.
    }

    public void deletBoard(long id) {
        boardSpringDataRepository.deleteById(id);
    }


}
