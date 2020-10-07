package ua.training.spring.hometask.sequrity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.service.UserService;

import java.util.Objects;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getUserByEmail(email);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("user with email: " + email+" not found");
        }

        return SecurityUser.fromUser(user);
    }
}
