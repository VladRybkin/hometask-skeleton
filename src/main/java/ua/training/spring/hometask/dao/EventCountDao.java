package ua.training.spring.hometask.dao;

import ua.training.spring.hometask.domain.EventCount;

public interface EventCountDao extends AbstractDomainObjectDao<EventCount> {

    EventCount getByName(String name);
}
