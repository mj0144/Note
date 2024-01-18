package app.note.service;

import app.note.dao.BoardRepository;
import app.note.dao.BoardSpringDataRepository;
import app.note.dto.BoardDto;
import app.note.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardSpringDataRepository boardSpringDataRepository;

    // 리스트 조회
    public List<Board> findAll() {
        return boardRepository.findAll();
    }

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

        board.updateContentTitle(dto.getContent(), dto.getTitle());
    }

    //단건조회
    public Board findById(long id) {
        return boardRepository.getById(id);
    }

    //
}
