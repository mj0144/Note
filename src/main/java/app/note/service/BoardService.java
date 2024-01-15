package app.note.service;

import app.note.dao.BoardRepository;
import app.note.dao.BoardSpringDataRepository;
import app.note.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardSpringDataRepository boardSpringDataRepository;

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    public List<Board> findAll_springData() {
        return boardSpringDataRepository.findAll();
    }
}
