package ua.training.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.training.spring.hometask.dao.EventDao;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.service.EventService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Service
public class DefaultEventService implements EventService {

    @Autowired
    private EventDao eventDao;


    @Nullable
    @Override
    public Event getByName(@Nonnull String name) {
        return eventDao.getByName(name);
    }

    @Nonnull
    @Override
    public Set<Event> getForDateRange(@Nonnull LocalDateTime from, @Nonnull LocalDateTime to) {
        return eventDao.getForDateRange(from, to);
    }

    @Nonnull
    @Override
    public Set<Event> getNextEvents(@Nonnull LocalDateTime to) {
        return eventDao.getNextEvents(to);
    }

    @Override
    public Event save(@Nonnull Event object) {
        return eventDao.save(object);
    }

    @Override
    public void remove(@Nonnull Event object) {
        eventDao.remove(object);
    }

    @Override
    public Event getById(@Nonnull Long id) {
        return eventDao.getById(id);

    }

    @Nonnull
    @Override
    public Collection<Event> getAll() {
        return eventDao.getAll();
    }

    public void setEventDao(EventDao eventDao) {
        this.eventDao = eventDao;
    }


}
