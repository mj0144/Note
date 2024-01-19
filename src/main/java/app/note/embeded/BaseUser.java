package app.note.embeded;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
//@MappedSuperclass  // 테이블에서 사용할거야.
//@EntityListeners(AuditingEntityListener.class) // 이건 확인해보기.
public class BaseUser extends BaseTime{
    private String createUser;
    private String lastModifiedUser;

    public BaseUser() {
    }
}
