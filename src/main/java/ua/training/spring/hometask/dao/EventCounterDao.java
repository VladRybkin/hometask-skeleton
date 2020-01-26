package ua.training.spring.hometask.dao;

import ua.training.spring.hometask.domain.EventCountInfo;

public interface EventCounterDao extends AbstractDomainObjectDao<EventCountInfo> {

    EventCountInfo getByName(String name);
}
