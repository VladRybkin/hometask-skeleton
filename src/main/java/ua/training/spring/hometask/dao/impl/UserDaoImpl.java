package ua.training.spring.hometask.dao.impl;

import org.springframework.stereotype.Component;
import ua.training.spring.hometask.dao.AbstractDomainObjectDao;
import ua.training.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
@Component
public class UserDaoImpl implements AbstractDomainObjectDao<User> {

    private static final Map<Long, User> users = new HashMap<>();

    @Override
    public User save(@Nonnull User object) {
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
}
