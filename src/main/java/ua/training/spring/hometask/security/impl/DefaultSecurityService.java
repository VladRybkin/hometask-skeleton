package ua.training.spring.hometask.security.impl;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.security.SecurityService;

import java.util.Set;

@Service
public class DefaultSecurityService implements SecurityService {

    public UserDetails fromUser(User user) {
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
