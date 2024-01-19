package app.note.dao;

import app.note.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * spring data 사용.
 */
@Repository
public interface BoardSpringDataRepository extends JpaRepository<Board, Long> {

    //public List<Board> findAll(); // 전체리스트
    @Query("select b from Board b")
    public Page<Board> findPaging();

    // 저장
    //public Board save(Board board);

    // 1건 조회
    //Board getById(Long aLong);

    // 수정

    // 삭제
}
