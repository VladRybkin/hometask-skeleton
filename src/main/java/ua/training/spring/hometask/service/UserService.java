package ua.training.spring.hometask.service;

import ua.training.spring.hometask.domain.User;


public interface UserService extends AbstractDomainObjectService<User> {

    User getUserByEmail(String email);
}
