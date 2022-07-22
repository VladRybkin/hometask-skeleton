package ua.training.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.training.spring.hometask.dao.EventDao;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.service.EventService;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Service
public class DefaultEventService implements EventService {

    @Autowired
    private EventDao eventDao;

    @Override
    public Event getByName(String name) {
        return eventDao.getByName(name);
    }

    @Override
    public Set<Event> getForDateRange(final LocalDateTime from, final LocalDateTime to) {
        return eventDao.getForDateRange(from, to);
    }

    @Override
    public Set<Event> getNextEvents(final LocalDateTime to) {
        return eventDao.getNextEvents(to);
    }

    @Transactional
    @Override
    public Event save(final Event object) {
        return eventDao.save(object);
    }

    @Transactional
    @Override
    public void remove(final Event object) {
        eventDao.remove(object);
    }

    @Override
    public Event getById(final Long id) {
        return eventDao.getById(id);
    }

    @Override
    public Collection<Event> getAll() {
        return eventDao.getAll();
    }

    @Override
    public boolean update(Event event) {
        return eventDao.update(event);
    }

    @Transactional
    @Override
    public Collection<Event> saveAll(final Collection<Event> events) {
        events.forEach(eventDao::save);

        return events;
    }
}
