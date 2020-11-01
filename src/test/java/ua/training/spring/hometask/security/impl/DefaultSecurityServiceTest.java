package ua.training.spring.hometask.security.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import ua.training.spring.hometask.config.WebMvcConfig;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.security.SecurityService;
import ua.training.spring.hometask.service.UserService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = WebMvcConfig.class)
@ActiveProfiles({"TEST", "IN_MEMORY"})
@WebAppConfiguration
class DefaultSecurityServiceTest {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserService userService;

    @Test
    void autoLogin() {
        securityService.autoLogin("VladTV@mail");
        assertThat(SecurityContextHolder.getContext().getAuthentication().getName(), is("VladTV@mail"));
    }

    @Test
    void getLoggedUser() {
        securityService.autoLogin("VladTV@mail");

        User user=userService.getUserByEmail("VladTV@mail");

        assertThat(securityService.getLoggedUser(), is(user));
    }
}