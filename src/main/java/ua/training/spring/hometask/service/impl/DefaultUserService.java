package ua.training.spring.hometask.service.impl;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.training.spring.hometask.dao.UserDao;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.service.UserService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

@Service
public class DefaultUserService implements UserService {

    @Autowired
    private UserDao userDao;

    @Nullable
    @Transactional(readOnly = true)
    @Override
    public User getUserByEmail(@Nonnull String email) throws Exception {
        return userDao.getUserByEmail(email);
    }

    @Override
    @Transactional
    public User save(@Nonnull User object) {
        return userDao.save(object);
    }

    @Override
    @Transactional
    public void remove(@Nonnull User object) {
        userDao.remove(object);
    }

    @Transactional(readOnly = true)
    @Override
    public User getById(@Nonnull Long id) {
        return userDao.getById(id);
    }

    @Nonnull
    @Transactional(readOnly = true)
    @Override
    public Collection<User> getAll() {
        return Lists.newArrayList(userDao.getAll());
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
