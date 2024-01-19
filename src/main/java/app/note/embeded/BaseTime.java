package app.note.embeded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Embeddable
@Getter
//@MappedSuperclass  // 테이블에서 사용할거야.
//@EntityListeners(AuditingEntityListener.class) // 이건 확인해보기.
public class BaseTime {

    @CreatedDate // 생성일 자동 생성.
    @Column(updatable = false) //  생성일은 수정 불가처리.
    private LocalDateTime createdDate;

    @LastModifiedDate // 수정일 자동 생성.
    private LocalDateTime lastModifiedDate;

    public BaseTime() {
    }
}
