package ua.training.spring.hometask.security;

import org.springframework.security.core.userdetails.UserDetails;
import ua.training.spring.hometask.domain.User;

public interface SecurityService {

    UserDetails fromUser(User user);

    void autoLogin(String username, String password);

    String getLoggedUserByEmail();
}
