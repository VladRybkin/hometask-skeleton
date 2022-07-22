package ua.training.spring.hometask.dao.impl.inmemory;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ua.training.spring.hometask.dao.UserDiscountCountDao;
import ua.training.spring.hometask.domain.UserDiscountCount;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository("userDiscountCountDaoImpl")
@Profile("IN_MEMORY")
public class UserDiscountCountDaoImpl implements UserDiscountCountDao {

    private Map<Long, UserDiscountCount> userDiscountCounts = new HashMap<>();

    @Override
    public UserDiscountCount save(final UserDiscountCount userDiscountCount) {
        userDiscountCount.setId((long) (userDiscountCounts.size() + 1));
        userDiscountCounts.put(userDiscountCount.getId(), userDiscountCount);

        return userDiscountCount;
    }

    @Override
    public void remove(final UserDiscountCount userDiscountCount) {
        userDiscountCounts.remove(userDiscountCount.getId());
    }

    @Override
    public UserDiscountCount getById(final Long id) {
        return userDiscountCounts.get(id);
    }

    @Override
    public Collection<UserDiscountCount> getAll() {
        return userDiscountCounts.values();
    }

    @Override
    public UserDiscountCount getByName(final String name) {
        return userDiscountCounts.values().stream().filter(ev -> ev.getName().equals(name)).findAny().orElse(null);
    }

    @Override
    public boolean update(final UserDiscountCount userDiscountCount) {
        boolean updated = false;
        if (userDiscountCounts.containsKey(userDiscountCount.getId())) {
            userDiscountCounts.put(userDiscountCount.getId(), userDiscountCount);
            updated = true;
        }

        return updated;
    }
}
