package ua.training.spring.hometask.dao.impl;

import org.springframework.stereotype.Repository;
import ua.training.spring.hometask.dao.UserDiscountDao;
import ua.training.spring.hometask.domain.UserDiscountCountInfo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserDiscountCounterDaoImpl implements UserDiscountDao {

    private Map<Long, UserDiscountCountInfo> userDiscountCounts = new HashMap<>();

    @Override
    public UserDiscountCountInfo save(UserDiscountCountInfo object) {
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
    public void remove(UserDiscountCountInfo object) {
        userDiscountCounts.remove(object.getId());
    }

    @Override
    public UserDiscountCountInfo getById(Long id) {
        return userDiscountCounts.get(id);
    }

    @Override
    public Collection<UserDiscountCountInfo> getAll() {
        return userDiscountCounts.values();
    }

    @Override
    public UserDiscountCountInfo getByName(String name) {
        return userDiscountCounts.values().stream().filter(ev -> ev.getName().equals(name)).findAny().orElse(null);
    }
}
