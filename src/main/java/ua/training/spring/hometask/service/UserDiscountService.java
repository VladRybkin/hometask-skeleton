package ua.training.spring.hometask.service;

import ua.training.spring.hometask.dao.AbstractDomainObjectDao;
import ua.training.spring.hometask.domain.DiscountCountInfo;

public interface UserDiscountService extends AbstractDomainObjectDao<DiscountCountInfo> {

    DiscountCountInfo getByName(String name);

    void countTenthTicketDiscountIncrement(String name);

    void countBirthdayDiscountIncrement(String name);

}
