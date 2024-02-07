package app.note.test;

import app.note.dao.BoardRepository;
import app.note.entity.Board;
import app.note.service.BoardService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
@Profile("test") // 테스트 환경에서만 실행.
public class TestConfiguration {

    private final InitData initData;

    @PostConstruct
    public void init() {
        initData.testData();
    }

    @Component
    static class InitData {

        @PersistenceContext
        private EntityManager em;

        @Transactional
        public void testData() {
            for(int i=0; i<6; i++) {
                Board board = Board.builder()
                        .title("테스트 제목"+i)
                        .content("테스트내용"+i)
                        .build();
                em.persist(board);
            }
        }
    }

}
