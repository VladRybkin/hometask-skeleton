package ua.training.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.training.spring.hometask.dao.EventCountDao;
import ua.training.spring.hometask.domain.EventCount;
import ua.training.spring.hometask.service.EventCountService;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Objects;

@Service
public class DefaultEventCountService implements EventCountService {

    @Autowired
    private EventCountDao eventCountDao;

    @Transactional
    @Override
    public EventCount save(@Nonnull EventCount object) {
        return eventCountDao.save(object);
    }

    @Transactional
    @Override
    public void remove(@Nonnull EventCount object) {
        eventCountDao.remove(object);
    }


    @Override
    public EventCount getById(@Nonnull Long id) {
        return eventCountDao.getById(id);
    }

    @Nonnull
    @Override
    public Collection<EventCount> getAll() {
        return eventCountDao.getAll();
    }

    @Transactional
    @Override
    public void getByNameCountIncrement(String eventName) {
        EventCount foundEventCount = eventCountDao.getByName(eventName);
        if (Objects.isNull(foundEventCount)) {
            EventCount eventCount = createEventCounter(eventName);
            eventCount.setCountGetByName(eventCount.getCountGetByName() + 1);
            eventCountDao.save(eventCount);
        } else {
            foundEventCount.setCountGetByName(foundEventCount.getCountGetByName() + 1);
            eventCountDao.update(foundEventCount);
        }
    }

    @Transactional
    @Override
    public void bookTicketsCountIncrement(String eventName) {
        EventCount foundEventCount = eventCountDao.getByName(eventName);
        if (Objects.isNull(foundEventCount)) {
            EventCount eventCount = createEventCounter(eventName);
            eventCount.setCountBookTickets(eventCount.getCountBookTickets() + 1);
            eventCountDao.save(eventCount);
        } else {
            foundEventCount.setCountBookTickets(foundEventCount.getCountBookTickets() + 1);
            eventCountDao.update(foundEventCount);
        }

    }

    @Transactional
    @Override
    public void getPriceCountIncrement(String eventName) {
        EventCount foundEventCount = eventCountDao.getByName(eventName);
        if (Objects.isNull(foundEventCount)) {
            EventCount eventCount = createEventCounter(eventName);
            eventCount.setCountGetPrice(eventCount.getCountGetPrice() + 1);
            eventCountDao.save(eventCount);
        } else {
            foundEventCount.setCountGetPrice(foundEventCount.getCountGetPrice() + 1);
            eventCountDao.update(foundEventCount);
        }
    }

    @Override
    public EventCount getByName(String name) {
        return eventCountDao.getByName(name);
    }

    private EventCount createEventCounter(String eventName) {
        return new EventCount.Builder()
                .withEventName(eventName)
                .withCountGetPrice(0)
                .withCountGetByName(0)
                .withCountBookTicket(0)
                .build();
    }
}
