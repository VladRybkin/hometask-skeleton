package ua.training.spring.hometask.security.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.security.SecurityService;
import ua.training.spring.hometask.service.UserService;

@Service
public class DefaultSecurityService implements SecurityService {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

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
}
