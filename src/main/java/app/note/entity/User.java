package app.note.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
@Table(name = "USERS")
public class User {
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

    private List<String> roles = new ArrayList<>();

    public void setRoles(List<GrantedAuthority> role) {
        this.roles = role.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }

    public List<GrantedAuthority> ofRoles() {
        return this.roles.stream()
                .map(o -> new SimpleGrantedAuthority(o))
                .collect(Collectors.toList());
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
