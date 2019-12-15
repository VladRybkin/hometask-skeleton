package ua.training.spring.hometask.dao;

import ua.training.spring.hometask.domain.User;

public interface UserDao extends AbstractDomainObjectDao<User> {


    User getUserByEmail(String email) throws Exception;

}
