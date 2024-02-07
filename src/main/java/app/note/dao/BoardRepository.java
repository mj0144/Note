package app.note.dao;

import app.note.dto.BoardSearchCondition;
import app.note.entity.Board;
import com.querydsl.core.types.ExpressionException;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Pageable;
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
//    public List<Board> findAll(BoardSearchCondition condition, int offset, int limit) {
    public List<Board> findAll(BoardSearchCondition condition, Pageable pageable) {
        return queryFactory.selectFrom(board)
                .where(titleLike(condition),
                        contentLike(condition))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    private BooleanExpression titleLike(BoardSearchCondition condition){
        if(condition != null) {
            return StringUtils.isEmpty(condition.getTitle()) ? null : board.title.like(condition.getTitle());
        }
        return null;
    }

    private BooleanExpression contentLike(BoardSearchCondition condition){
        if(condition != null) {
            return StringUtils.isEmpty(condition.getContent()) ? null : board.content.like(condition.getContent());
        }
        return null;
    }



    //업데이트 -> 변경감지 사용.
//    public void updateBoard(long id) throws Exception {
//
//    }

    //삭제
    public void deleteBoard(long id) throws Exception {

// queryDSL
//        queryFactory.delete(board)
//                .where(board.id.eq(id))
//                .execute();
    }

}
