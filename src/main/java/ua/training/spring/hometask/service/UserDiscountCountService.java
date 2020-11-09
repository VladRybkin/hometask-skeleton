package ua.training.spring.hometask.service;

import ua.training.spring.hometask.domain.UserDiscountCount;

public interface UserDiscountCountService extends AbstractDomainObjectService<UserDiscountCount> {

    UserDiscountCount getByName(String name);

    void countTenthTicketDiscountIncrement(String name);

    void countBirthdayDiscountIncrement(String name);
}
