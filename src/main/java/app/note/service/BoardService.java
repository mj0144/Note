package app.note.service;

import app.note.controller.BoardRequestDto;
import app.note.dao.BoardRepository;
import app.note.dao.BoardSpringDataRepository;
import app.note.dto.BoardDto;
import app.note.dto.BoardSearchCondition;
import app.note.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardSpringDataRepository boardSpringDataRepository;

    // 페이징 조회. springData
    public Page<Board> findPaging() {
        return boardSpringDataRepository.findPaging();
    }


    // 리스트 - 페이징 동적 조회
    public List<Board> findAll(BoardSearchCondition condition, int offset, int limit) {
        return boardRepository.findAll(condition, offset, limit);
    }








    // 전체 조회 - springData
    public List<Board> findAll_springData() {
        return boardSpringDataRepository.findAll();
    }

    // 수정
    @Transactional
    public void update(BoardDto dto) {
        Board board = boardRepository.getById(dto.getId());
//        board = Board.builder() // 새로운 객체를 할당하는 방식이라 안됨.
//                .content(dto.getContent())
//                .title(dto.getTitle())
//                .build();

        board.updateContentTitle(dto.getContent(), dto.getTitle()); // 변경감지 사용.
    }

    //단건조회
    public Board findById(long id) {
        return boardRepository.getById(id);
    }

    // 저장 - springData
    public Board save(BoardRequestDto boardRequestDto) {
        Board board = Board.builder()
                .title(boardRequestDto.getTitle())
                .content(boardRequestDto.getContent())
                .build();
        return boardSpringDataRepository.save(board);
    }


}
