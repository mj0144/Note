package app.note.dao;

import app.note.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardSpringDataRepository extends JpaRepository<Board, Long> {

    List<Board> findAll();
}
