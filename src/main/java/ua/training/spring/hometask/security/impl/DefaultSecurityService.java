package ua.training.spring.hometask.security.impl;

import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.security.SecurityService;
import ua.training.spring.hometask.service.UserService;

import java.util.Set;

@Service
public class DefaultSecurityService implements SecurityService {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    public UserDetails fromUser(User user) {
        Set<SimpleGrantedAuthority> grantedAuthorities = getGrantedAuthoritiesFromUserRoles(user);

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(),
                grantedAuthorities
        );
    }

    @Override
    public void autoLogin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);


    }

    @Override
    public User getLoggedUser() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        return userService.getUserByEmail(userEmail);
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
