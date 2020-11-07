package ua.training.spring.hometask.facade;

import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.dto.RegistrationUserForm;

import java.time.LocalDate;

public interface UserFacade {

    User saveUser(User user, LocalDate birthday);

    User registerUser(RegistrationUserForm userForm, LocalDate birthday);
}
