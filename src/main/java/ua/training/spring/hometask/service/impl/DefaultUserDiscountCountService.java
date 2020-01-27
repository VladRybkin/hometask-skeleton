package ua.training.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.training.spring.hometask.dao.UserDiscountDao;
import ua.training.spring.hometask.domain.UserDiscountCountInfo;
import ua.training.spring.hometask.service.UserDiscountService;

import java.util.Collection;
import java.util.Objects;

@Service
public class DefaultUserDiscountCountService implements UserDiscountService {

    @Autowired
    private UserDiscountDao userDiscountDao;

    @Override
    public UserDiscountCountInfo save(UserDiscountCountInfo object) {
        return userDiscountDao.save(object);
    }

    @Override
    public void remove(UserDiscountCountInfo object) {
        userDiscountDao.remove(object);
    }

    @Override
    public UserDiscountCountInfo getById(Long id) {
        return userDiscountDao.getById(id);
    }

    @Override
    public Collection<UserDiscountCountInfo> getAll() {
        return userDiscountDao.getAll();
    }

    @Override
    public void countTenthTicketDiscountIncrement(String name) {
        UserDiscountCountInfo foundUserDiscountCountInfo = userDiscountDao.getByName(name);
        if (Objects.isNull(foundUserDiscountCountInfo)) {
            UserDiscountCountInfo eventCountInfo = createUserDiscountCounter(name);
            eventCountInfo.setCountTenthTicketDiscount(eventCountInfo.getCountTenthTicketDiscount() + 1);
            userDiscountDao.save(eventCountInfo);
        } else {
            foundUserDiscountCountInfo.setCountTenthTicketDiscount(foundUserDiscountCountInfo.getCountTenthTicketDiscount() + 1);
            userDiscountDao.save(foundUserDiscountCountInfo);
        }
    }

    @Override
    public void countBirthdayDiscountIncrement(String name) {
        UserDiscountCountInfo foundUserDiscountCountInfo = userDiscountDao.getByName(name);
        if (Objects.isNull(foundUserDiscountCountInfo)) {
            UserDiscountCountInfo eventCountInfo = createUserDiscountCounter(name);
            eventCountInfo.setCountBirthdayDiscount(eventCountInfo.getCountBirthdayDiscount() + 1);
            userDiscountDao.save(eventCountInfo);
        } else {
            foundUserDiscountCountInfo.setCountBirthdayDiscount(foundUserDiscountCountInfo.getCountBirthdayDiscount() + 1);
            userDiscountDao.save(foundUserDiscountCountInfo);
        }

    }

    @Override
    public UserDiscountCountInfo getByName(String name) {
        return userDiscountDao.getByName(name);
    }

    private UserDiscountCountInfo createUserDiscountCounter(String name) {
        return new UserDiscountCountInfo.Builder()
                .withCountBirthdayDiscount(0)
                .withCountTenthTicketDiscount(0)
                .withUserName(name).build();
    }

    public void setUserDiscountDao(UserDiscountDao userDiscountDao) {
        this.userDiscountDao = userDiscountDao;
    }
}
