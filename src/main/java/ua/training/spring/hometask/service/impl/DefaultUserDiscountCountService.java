package ua.training.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.training.spring.hometask.dao.UserDiscountCountDao;
import ua.training.spring.hometask.domain.UserDiscountCount;
import ua.training.spring.hometask.service.UserDiscountCountService;

import java.util.Collection;
import java.util.Objects;

@Service
public class DefaultUserDiscountCountService implements UserDiscountCountService {

    @Autowired
    private UserDiscountCountDao userDiscountCountDao;

    @Transactional
    @Override
    public UserDiscountCount save(final UserDiscountCount object) {
        return userDiscountCountDao.save(object);
    }

    @Transactional
    @Override
    public void remove(UserDiscountCount object) {
        userDiscountCountDao.remove(object);
    }

    @Override
    public UserDiscountCount getById(Long id) {
        return userDiscountCountDao.getById(id);
    }

    @Override
    public Collection<UserDiscountCount> getAll() {
        return userDiscountCountDao.getAll();
    }

    @Transactional
    @Override
    public void countTenthTicketDiscountIncrement(final String name) {
        UserDiscountCount foundUserDiscountCount = userDiscountCountDao.getByName(name);
        if (Objects.isNull(foundUserDiscountCount)) {
            UserDiscountCount eventCountInfo = createUserDiscountCounter(name);
            eventCountInfo.setCountTenthTicketDiscount(eventCountInfo.getCountTenthTicketDiscount() + 1);
            userDiscountCountDao.save(eventCountInfo);
        } else {
            foundUserDiscountCount.setCountTenthTicketDiscount(foundUserDiscountCount.getCountTenthTicketDiscount() + 1);
            userDiscountCountDao.update(foundUserDiscountCount);
        }
    }

    @Transactional
    @Override
    public void countBirthdayDiscountIncrement(final String name) {
        UserDiscountCount foundUserDiscountCount = userDiscountCountDao.getByName(name);
        if (Objects.isNull(foundUserDiscountCount)) {
            UserDiscountCount eventCountInfo = createUserDiscountCounter(name);
            eventCountInfo.setCountBirthdayDiscount(eventCountInfo.getCountBirthdayDiscount() + 1);
            userDiscountCountDao.save(eventCountInfo);
        } else {
            foundUserDiscountCount.setCountBirthdayDiscount(foundUserDiscountCount.getCountBirthdayDiscount() + 1);
            userDiscountCountDao.update(foundUserDiscountCount);
        }

    }

    @Override
    public UserDiscountCount getByName(final String name) {
        return userDiscountCountDao.getByName(name);
    }

    private UserDiscountCount createUserDiscountCounter(final String name) {
        return new UserDiscountCount.Builder()
                .withCountBirthdayDiscount(0)
                .withCountTenthTicketDiscount(0)
                .withUserName(name).build();
    }

    @Override
    public boolean update(final UserDiscountCount userDiscountCount) {
        return userDiscountCountDao.update(userDiscountCount);
    }

    @Override
    public Collection<UserDiscountCount> saveAll(final Collection<UserDiscountCount> userDiscountCounts) {
        userDiscountCounts.forEach(userDiscountCountDao::save);

        return userDiscountCounts;
    }
}
