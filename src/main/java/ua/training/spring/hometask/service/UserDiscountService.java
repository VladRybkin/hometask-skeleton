package ua.training.spring.hometask.service;

import ua.training.spring.hometask.dao.AbstractDomainObjectDao;
import ua.training.spring.hometask.domain.UserDiscountCountInfo;

public interface UserDiscountService extends AbstractDomainObjectDao<UserDiscountCountInfo> {

    UserDiscountCountInfo getByName(String name);

    void countTenthTicketDiscountIncrement(String name);

    void countBirthdayDiscountIncrement(String name);

}
