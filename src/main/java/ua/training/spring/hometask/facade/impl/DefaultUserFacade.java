package ua.training.spring.hometask.facade.impl;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ua.training.spring.hometask.domain.Role;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.dto.RegistrationUserForm;
import ua.training.spring.hometask.facade.UserFacade;
import ua.training.spring.hometask.service.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static java.time.temporal.ChronoUnit.DAYS;

@Component
public class DefaultUserFacade implements UserFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public User saveUser(User user, LocalDate birthday) {
        Validate.notNull(birthday, "user birthday should be present");
        user.setDateOfBirth(birthday.atTime(LocalTime.MIDNIGHT).truncatedTo(DAYS));
        userService.save(user);

        return user;
    }

    @Override
    public User registerUser(RegistrationUserForm userForm, LocalDate dateOfTheBirth) {
        User user = getUserFromUserFormRequest(userForm, dateOfTheBirth);
        userService.save(user);

        return user;
    }

    private User getUserFromUserFormRequest(RegistrationUserForm userForm, LocalDate dateOfTheBirth) {
        User user = new User();
        user.setEmail(userForm.getEmail());
        user.setPassword(encoder.encode(userForm.getPassword()));
        user.setFirstName(userForm.getFirstName());
        user.setDateOfBirth(LocalDateTime.of(dateOfTheBirth, LocalTime.MIDNIGHT));
        user.getRoles().add(Role.USER);

        return user;
    }
}
