package ua.training.spring.hometask.dao;

import ua.training.spring.hometask.domain.UserDiscountCountInfo;

public interface UserDiscountDao extends AbstractDomainObjectDao<UserDiscountCountInfo> {

    UserDiscountCountInfo getByName(String name);

}
