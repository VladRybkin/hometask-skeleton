package ua.training.spring.hometask.dao.impl.immemory;

import org.springframework.stereotype.Repository;
import ua.training.spring.hometask.dao.UserDiscountCountDao;
import ua.training.spring.hometask.domain.UserDiscountCount;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserDiscountCountDaoImpl implements UserDiscountCountDao {

    private Map<Long, UserDiscountCount> userDiscountCounts = new HashMap<>();

    @Override
    public UserDiscountCount save(UserDiscountCount object) {
        if (userDiscountCounts.containsKey(object.getId())) {
            userDiscountCounts.put(object.getId(), object);
            return object;
        } else {
            object.setId((long) (userDiscountCounts.size() + 1));
            userDiscountCounts.put(object.getId(), object);
            return object;
        }
    }

    @Override
    public void remove(UserDiscountCount object) {
        userDiscountCounts.remove(object.getId());
    }

    @Override
    public UserDiscountCount getById(Long id) {
        return userDiscountCounts.get(id);
    }

    @Override
    public Collection<UserDiscountCount> getAll() {
        return userDiscountCounts.values();
    }

    @Override
    public UserDiscountCount getByName(String name) {
        return userDiscountCounts.values().stream().filter(ev -> ev.getName().equals(name)).findAny().orElse(null);
    }
}
