package ua.training.spring.hometask.security.impl;

import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.service.UserService;

import java.util.Objects;
import java.util.Set;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getUserByEmail(email);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("user with email: " + email + " not found");
        }

        return getUserDetailsFromUser(user);
    }

    private UserDetails getUserDetailsFromUser(User user) {
        Set<SimpleGrantedAuthority> grantedAuthorities = getGrantedAuthoritiesFromUserRoles(user);

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(),
                grantedAuthorities
        );
    }

    private Set<SimpleGrantedAuthority> getGrantedAuthoritiesFromUserRoles(User user) {
        Set<SimpleGrantedAuthority> grantedAuthorities = Sets.newHashSet();

        user.getRoles().forEach(role -> {
            SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.name());
            grantedAuthorities.add(grantedAuthority);
        });

        return grantedAuthorities;
    }
}
