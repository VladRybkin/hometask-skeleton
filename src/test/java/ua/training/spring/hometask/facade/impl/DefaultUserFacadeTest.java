package ua.training.spring.hometask.facade.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.service.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DefaultUserFacadeTest {

    @InjectMocks
    private DefaultUserFacade userFacade;

    @Mock
    private UserService userService;

    @Test
    void saveUser() {
        User user = new User();
        user.setEmail("testemail");
        LocalDate birthday = LocalDate.now();
        User persistedUser = userFacade.saveUser(user, birthday);
        LocalDateTime expectedBirthdayDateTime = birthday.atTime(LocalTime.MIDNIGHT).truncatedTo(DAYS);

        assertThat(persistedUser.getDateOfBirth(), is(expectedBirthdayDateTime));

        user.setDateOfBirth(expectedBirthdayDateTime);
        verify(userService).save(user);
    }
}