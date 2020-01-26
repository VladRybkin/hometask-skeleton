package ua.training.spring.hometask.service;

import ua.training.spring.hometask.domain.EventCountInfo;

public interface EventCountService extends AbstractDomainObjectService<EventCountInfo> {

    EventCountInfo getByName(String name);

    void getByNameCountIncrement(String eventName);

    void bookTicketsCountIncrement(String eventName);

    void getPriceCountIncrement(String eventName);

}
