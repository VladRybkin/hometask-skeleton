package ua.training.spring.hometask.dao.impl.immemory;

import org.springframework.stereotype.Repository;
import ua.training.spring.hometask.dao.UserDao;
import ua.training.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Repository
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
    public User getUserByEmail(String email) {
        return users.values().stream().filter(user -> user.getEmail().equals(email)).findAny().orElse(null);
    }
}
