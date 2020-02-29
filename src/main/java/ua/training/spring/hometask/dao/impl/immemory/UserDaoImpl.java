package ua.training.spring.hometask.dao.impl.immemory;

import org.springframework.stereotype.Repository;
import ua.training.spring.hometask.dao.UserDao;
import ua.training.spring.hometask.domain.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Repository("userDaoImpl")
public class UserDaoImpl implements UserDao {

    private static final Map<Long, User> users = new HashMap<>();

    @Override
    public User save(User object) {
        object.setId((long) (users.size() + 1));
        users.put(object.getId(), object);

        return object;
    }

    @Override
    public void remove(User object) {
        users.remove(object.getId());
    }

    @Override
    public User getById(Long id) {
        return users.get(id);
    }

    @Override
    public Collection<User> getAll() {
        return users.values();
    }

    @Override
    public User getUserByEmail(String email) {
        return users.values().stream().filter(user -> user.getEmail().equals(email)).findAny().orElse(null);
    }
}
