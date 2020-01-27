package ua.training.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.training.spring.hometask.dao.EventCounterDao;
import ua.training.spring.hometask.domain.EventCountInfo;
import ua.training.spring.hometask.service.EventCountService;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Objects;

@Component
public class DefaultEventCountService implements EventCountService {

    @Autowired
    private EventCounterDao eventCounterDao;

    @Override
    public EventCountInfo save(@Nonnull EventCountInfo object) {
        return eventCounterDao.save(object);
    }

    @Override
    public void remove(@Nonnull EventCountInfo object) {
        eventCounterDao.remove(object);
    }

    @Override
    public EventCountInfo getById(@Nonnull Long id) {
        return eventCounterDao.getById(id);
    }

    @Nonnull
    @Override
    public Collection<EventCountInfo> getAll() {
        return eventCounterDao.getAll();
    }

    @Override
    public void getByNameCountIncrement(String eventName) {
        EventCountInfo foundEventCountInfo = eventCounterDao.getByName(eventName);
        if (Objects.isNull(foundEventCountInfo)) {
            EventCountInfo eventCountInfo = createEventCounter(eventName);
            eventCountInfo.setCountGetByName(eventCountInfo.getCountGetByName() + 1);
            eventCounterDao.save(eventCountInfo);
        } else {
            foundEventCountInfo.setCountGetByName(foundEventCountInfo.getCountGetByName() + 1);
            eventCounterDao.save(foundEventCountInfo);
        }
    }


    @Override
    public void bookTicketsCountIncrement(String eventName) {
        EventCountInfo foundEventCountInfo = eventCounterDao.getByName(eventName);
        if (Objects.isNull(foundEventCountInfo)) {
            EventCountInfo eventCountInfo = createEventCounter(eventName);
            eventCountInfo.setCountBookTickets(eventCountInfo.getCountBookTickets() + 1);
            eventCounterDao.save(eventCountInfo);
        } else {
            foundEventCountInfo.setCountBookTickets(foundEventCountInfo.getCountBookTickets() + 1);
            eventCounterDao.save(foundEventCountInfo);
        }

    }

    @Override
    public void getPriceCountIncrement(String eventName) {
        EventCountInfo foundEventCountInfo = eventCounterDao.getByName(eventName);
        if (Objects.isNull(foundEventCountInfo)) {
            EventCountInfo eventCountInfo = createEventCounter(eventName);
            eventCountInfo.setCountGetPrice(eventCountInfo.getCountGetPrice() + 1);
            eventCounterDao.save(eventCountInfo);
        } else {
            foundEventCountInfo.setCountGetPrice(foundEventCountInfo.getCountGetPrice() + 1);
            eventCounterDao.save(foundEventCountInfo);
        }
    }

    @Override
    public EventCountInfo getByName(String name) {
        return eventCounterDao.getByName(name);
    }

    private EventCountInfo createEventCounter(String eventName) {
        return new EventCountInfo.Builder()
                .withEventName(eventName)
                .withCountGetPrice(0)
                .withCountGetByName(0)
                .withCountBookTicket(0)
                .build();
    }

    public void setEventCounterDao(EventCounterDao eventCounterDao) {
        this.eventCounterDao = eventCounterDao;
    }

}
