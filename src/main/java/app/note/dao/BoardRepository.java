package app.note.dao;

import app.note.entity.Board;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em;

    public List<Board> findAll() {
        return em.createQuery("select b from Board b", Board.class)
                .getResultList();
    }

    // 저장
    public long save(Board board) {
        em.persist(board);
        return board.getId();
    }

    // 1건 조회
    public Board getById(long id) {
        return em.createQuery("select b from Board b where b.id = :id", Board.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    //업데이트 -> 변경감지 사용.


    //삭제

}
