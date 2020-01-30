package ua.training.spring.hometask.service;

import ua.training.spring.hometask.domain.EventCount;

public interface EventCountService extends AbstractDomainObjectService<EventCount> {

    EventCount getByName(String name);

    void getByNameCountIncrement(String eventName);

    void bookTicketsCountIncrement(String eventName);

    void getPriceCountIncrement(String eventName);

}
