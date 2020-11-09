package ua.training.spring.hometask.security.impl;

import com.google.common.collect.Sets;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.service.UserService;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static ua.training.spring.hometask.utills.BuildTestEntityUtill.buildTestUser;

@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImplTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    private static final String userName = "testUserName";

    @Test
    void loadUserByUsername() {
        User user = buildTestUser();

        when(userService.getUserByEmail(userName)).thenReturn(user);

        assertThat(userDetailsService.loadUserByUsername(userName), is(getUserDetailsFromUser(user)));
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
            SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getName());
            grantedAuthorities.add(grantedAuthority);
        });

        return grantedAuthorities;
    }
}