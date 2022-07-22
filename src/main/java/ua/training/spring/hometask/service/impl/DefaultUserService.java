package ua.training.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.training.spring.hometask.dao.UserDao;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.service.UserService;

import java.util.Collection;

@Service
public class DefaultUserService implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserByEmail(final String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    @Transactional
    public User save(final User user) {
        return userDao.save(user);
    }

    @Override
    @Transactional
    public void remove(final User user) {
        userDao.remove(user);
    }

    @Override
    public User getById(final Long id) {
        return userDao.getById(id);
    }

    @Override
    public Collection<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public boolean update(final User user) {
        return userDao.update(user);
    }

    @Transactional
    @Override
    public Collection<User> saveAll(final Collection<User> users) {
        users.forEach(userDao::save);

        return users;
    }
}
