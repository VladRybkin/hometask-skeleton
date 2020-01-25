package ua.training.spring.hometask.dao.impl;

import org.springframework.stereotype.Component;
import ua.training.spring.hometask.dao.UserDiscountDao;
import ua.training.spring.hometask.domain.DiscountCountInfo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserDiscountCounterDaoImpl implements UserDiscountDao {

    private Map<Long, DiscountCountInfo> userDiscountCounts = new HashMap<>();

    @Override
    public DiscountCountInfo save(DiscountCountInfo object) {
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
    public void remove(DiscountCountInfo object) {
        userDiscountCounts.remove(object.getId());
    }

    @Override
    public DiscountCountInfo getById(Long id) {
        return userDiscountCounts.get(id);
    }

    @Override
    public Collection<DiscountCountInfo> getAll() {
        return userDiscountCounts.values();
    }

    @Override
    public DiscountCountInfo getByName(String name) {
        return userDiscountCounts.values().stream().filter(ev -> ev.getName().equals(name)).findAny().orElse(null);
    }
}
