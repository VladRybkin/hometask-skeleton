package ua.training.spring.hometask.facade.impl;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.facade.UserFacade;
import ua.training.spring.hometask.service.UserService;

import java.time.LocalDate;
import java.time.LocalTime;

import static java.time.temporal.ChronoUnit.DAYS;

@Component
public class DefaultUserFacade implements UserFacade {

    @Autowired
    private UserService userService;

    @Override
    public User saveUser(User user, LocalDate birthday) {
        Validate.notNull(birthday, "user birthday should be present");
        user.setDateOfBirth(birthday.atTime(LocalTime.MIDNIGHT).truncatedTo(DAYS));
        userService.save(user);

        return user;
    }
}
