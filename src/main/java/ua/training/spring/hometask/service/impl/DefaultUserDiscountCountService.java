package ua.training.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.training.spring.hometask.dao.UserDiscountDao;
import ua.training.spring.hometask.domain.DiscountCountInfo;
import ua.training.spring.hometask.service.UserDiscountService;

import java.util.Collection;
import java.util.Objects;

@Component
public class DefaultUserDiscountCountService implements UserDiscountService {

    @Autowired
    private UserDiscountDao userDiscountDao;

    @Override
    public DiscountCountInfo save(DiscountCountInfo object) {
        return userDiscountDao.save(object);
    }

    @Override
    public void remove(DiscountCountInfo object) {
        userDiscountDao.remove(object);
    }

    @Override
    public DiscountCountInfo getById(Long id) {
        return userDiscountDao.getById(id);
    }

    @Override
    public Collection<DiscountCountInfo> getAll() {
        return userDiscountDao.getAll();
    }

    @Override
    public void countTenthTicketDiscountIncrement(String name) {
        DiscountCountInfo foundDiscountCountInfo = userDiscountDao.getByName(name);
        if (Objects.isNull(foundDiscountCountInfo)) {
            DiscountCountInfo eventCountInfo = createUserDiscountCounter(name);
            eventCountInfo.setCountTenthTicketDiscount(eventCountInfo.getCountTenthTicketDiscount() + 1);
            userDiscountDao.save(eventCountInfo);
        } else {
            foundDiscountCountInfo.setCountTenthTicketDiscount(foundDiscountCountInfo.getCountTenthTicketDiscount() + 1);
            userDiscountDao.save(foundDiscountCountInfo);
        }
    }

    @Override
    public void countBirthdayDiscountIncrement(String name) {
        DiscountCountInfo foundDiscountCountInfo = userDiscountDao.getByName(name);
        if (Objects.isNull(foundDiscountCountInfo)) {
            DiscountCountInfo eventCountInfo = createUserDiscountCounter(name);
            eventCountInfo.setCountBirthdayDiscount(eventCountInfo.getCountBirthdayDiscount() + 1);
            userDiscountDao.save(eventCountInfo);
        } else {
            foundDiscountCountInfo.setCountBirthdayDiscount(foundDiscountCountInfo.getCountBirthdayDiscount() + 1);
            userDiscountDao.save(foundDiscountCountInfo);
        }

    }

    @Override
    public DiscountCountInfo getByName(String name) {
        return userDiscountDao.getByName(name);
    }

    private DiscountCountInfo createUserDiscountCounter(String name) {
        return new DiscountCountInfo.Builder()
                .withCountBirthdayDiscount(0)
                .withCountTenthTicketDiscount(0)
                .withUserName(name).build();
    }

    public void setUserDiscountDao(UserDiscountDao userDiscountDao) {
        this.userDiscountDao = userDiscountDao;
    }
}
