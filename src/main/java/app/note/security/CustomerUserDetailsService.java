package app.note.security;

import app.note.dao.UserSpringDataRepository;
import app.note.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomerUserDetailsService implements UserDetailsService {

    private final UserSpringDataRepository userSpringDataRepository;

    // 실제 사용자 정보를 불러오는 핵심로직.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userSpringDataRepository.findByUserId(username).orElseThrow(
                () -> new UsernameNotFoundException("")
        );

        return new CustomerUserDetails(user);
    }
}
