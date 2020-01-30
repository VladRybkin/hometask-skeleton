package ua.training.spring.hometask.dao;

import ua.training.spring.hometask.domain.UserDiscountCount;

public interface UserDiscountCountDao extends AbstractDomainObjectDao<UserDiscountCount> {

    UserDiscountCount getByName(String name);

}
