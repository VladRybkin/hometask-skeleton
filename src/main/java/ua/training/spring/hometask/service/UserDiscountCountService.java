package ua.training.spring.hometask.service;

import ua.training.spring.hometask.dao.AbstractDomainObjectDao;
import ua.training.spring.hometask.domain.UserDiscountCount;

public interface UserDiscountCountService extends AbstractDomainObjectDao<UserDiscountCount> {

    UserDiscountCount getByName(String name);

    void countTenthTicketDiscountIncrement(String name);

    void countBirthdayDiscountIncrement(String name);

}
