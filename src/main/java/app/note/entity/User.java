package app.note.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
@Table(name = "USERS")
//@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "userId")})
public class User {
//    @Id @GeneratedValue
//    private long id;

    @Id
    @NotEmpty(message = "아이디는 필수값")
    @Column(unique = true, name = "user_id") // 아이디는 유일값.
    private String userId;

    @OneToMany(mappedBy = "user")
    private List<Board> board = new ArrayList<>();

    @NotEmpty(message = "비번은 필수값")
    private String passwd;
    private String name;
    private LocalDate brith;

    private Gender gender;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL) // authority 도 함께 저장.
    @Builder.Default // 빌더 시 기본값.
    private List<Authority> roles = new ArrayList<>();

    public void setRoles(List<Authority> role) {
        this.roles = role;
        role.forEach(o -> o.serUser(this));
    }

    public User(User user) {
//        this.id = user.getId();
        this.userId = user.getUserId();
        this.passwd = user.getPasswd();
        this.name = user.getName();
        this.brith = user.getBrith();
        this.gender = user.getGender();
        this.roles = user.getRoles();
    }


}
