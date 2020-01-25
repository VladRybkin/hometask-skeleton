package ua.training.spring.hometask.dao;

import ua.training.spring.hometask.domain.DiscountCountInfo;

public interface UserDiscountDao extends AbstractDomainObjectDao<DiscountCountInfo>{

    DiscountCountInfo getByName(String name);

}
