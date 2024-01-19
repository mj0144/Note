package app.note.dao;

import app.note.dto.BoardSearchCondition;
import app.note.entity.Board;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static app.note.entity.QBoard.*;

/**
 * jqpl + queyrDsl 사용.
 */

@Repository
public class BoardRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public BoardRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    // 전제조회
//    public List<Board> findAll() {
//        return em.createQuery("select b from Board b", Board.class)
//                .getResultList();
//
//        // QueryDsl
////        return queryFactory
////                .selectFrom(board)
////                .fetch();
//    }

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

        // queryDsl
//        return queryFactory
//                .selectFrom(board)
//                .where(board.id.eq(id))
//                .fetch();
    }

    // TODO : 동적쿼리 필요.
    public List<Board> findAll(BoardSearchCondition condition, int offset, int limit) {
        return queryFactory.selectFrom(board)
                .where(titleLike(condition.getTitle()),
                        contentLike(condition.getContent()))
                .offset(offset)
                .limit(limit)
                .fetch();
    }

    private BooleanExpression titleLike(String title){
        return StringUtils.isEmpty(title) ? null : board.title.like(title);
    }

    private BooleanExpression contentLike(String content){
        return StringUtils.isEmpty(content) ? null : board.content.like(content);
    }



    //업데이트 -> 변경감지 사용.


    //삭제

}
