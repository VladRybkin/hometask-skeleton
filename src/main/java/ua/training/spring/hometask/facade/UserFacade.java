package ua.training.spring.hometask.facade;

import ua.training.spring.hometask.domain.User;

import java.time.LocalDate;

public interface UserFacade {

    User saveUser(User user, LocalDate birthday);
}
