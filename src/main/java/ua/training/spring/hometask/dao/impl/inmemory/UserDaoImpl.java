package ua.training.spring.hometask.dao.impl.inmemory;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ua.training.spring.hometask.dao.UserDao;
import ua.training.spring.hometask.domain.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Repository("userDaoImpl")
@Profile("IN_MEMORY")
public class UserDaoImpl implements UserDao {

    private static final Map<Long, User> users = new HashMap<>();

    @Override
    public User save(final User user) {
        if (users.containsKey(user.getId())) {
            users.put(user.getId(), user);
        } else {
            user.setId((long) (users.size() + 1));
            users.put(user.getId(), user);
        }

        return user;
    }

    @Override
    public boolean update(final User user) {
        boolean update = false;
        if (users.containsKey(user.getId())) {
            users.put(user.getId(), user);
            update = true;
        }

        return update;
    }

    @Override
    public void remove(final User user) {
        users.remove(user.getId());
    }

    @Override
    public User getById(final Long id) {
        return users.get(id);
    }

    @Override
    public Collection<User> getAll() {
        return users.values();
    }

    @Override
    public User getUserByEmail(final String email) {
        return users.values().stream().filter(user -> user.getEmail().equals(email)).findAny().orElse(null);
    }
}
