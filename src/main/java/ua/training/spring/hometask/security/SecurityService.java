package ua.training.spring.hometask.security;

import ua.training.spring.hometask.domain.User;

public interface SecurityService {

    void autoLogin(String username);

    User getLoggedUser();
}
