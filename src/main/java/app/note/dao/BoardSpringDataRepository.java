package app.note.dao;

import app.note.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardSpringDataRepository extends JpaRepository<Board, Long> {

    //public List<Board> findAll(); // 전체리스트

    // 저장
    //public Board save(Board board);

    // 1건 조회
    //Board getById(Long aLong);

    // 수정

    // 삭제
}
