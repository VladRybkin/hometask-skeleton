package ua.training.spring.hometask.dao.impl;

import org.springframework.stereotype.Component;

import ua.training.spring.hometask.dao.UserDao;
import ua.training.spring.hometask.domain.User;


import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Component
public class UserDaoImpl implements UserDao {

    private static final Map<Long, User> users = new HashMap<>();

    @Override
    public User save(@Nonnull User object) {
        object.setId((long) (users.size() + 1));
        users.put(object.getId(), object);
        return object;
    }

    @Override
    public void remove(@Nonnull User object) {
        users.remove(object.getId());
    }

    @Override
    public User getById(@Nonnull Long id) {
        return users.get(id);
    }

    @Nonnull
    @Override
    public Collection<User> getAll() {
        return users.values();
    }

    @Override
    public User getUserByEmail(String email) throws Exception {
        return users.values().stream().filter(user -> user.getEmail().equals(email)).findAny().orElse(null);
    }
}
