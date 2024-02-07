package app.note.embeded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Embeddable
@Getter
@MappedSuperclass  // 테이블로 생성 X
@EntityListeners(AuditingEntityListener.class) // JPA 내부에서 엔티티가 생성/변경 감지. ( AuditingEntityListener을 활성화 시키기 위해서는 프로젝트에 @EnableJpaAuditing설정 필요 )
public class BaseTime {

    @CreatedDate // 생성일 자동 생성.
    @Column(updatable = false) //  생성일은 수정 불가처리.
    private LocalDateTime createdDate;

    @LastModifiedDate // 수정일 자동 생성.
    private LocalDateTime lastModifiedDate;

    public BaseTime() {
    }
}
