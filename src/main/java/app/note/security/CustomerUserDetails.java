package app.note.security;

import app.note.entity.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CustomerUserDetails implements UserDetails {

    private final User user;

    // 단순히 권한 문자열 표현
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles().stream()
                .map(o -> new SimpleGrantedAuthority(
                        o.getName()
                )).collect(Collectors.toList());

    }

    @Override
    public String getPassword() {
        return user.getPasswd();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    // 계정 만료 여부 ( true : 만료안됨, false : 만료됨 )
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정 잠금 여부 ( true: 잠겨있지 않음, false : 잠겨있음 )
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    // 비번 만료여부 ( true : 만료되지 않음. false : 만료됨 )
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 활성화 여부 ( true:활성화, false : 비활성화 )
    @Override
    public boolean isEnabled() {
        return true;
    }
}
