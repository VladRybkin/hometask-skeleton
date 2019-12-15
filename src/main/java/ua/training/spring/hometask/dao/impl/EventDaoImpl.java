package ua.training.spring.hometask.dao.impl;

import org.springframework.stereotype.Component;
import ua.training.spring.hometask.dao.EventDao;
import ua.training.spring.hometask.domain.Event;

import javax.annotation.Nonnull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class EventDaoImpl implements EventDao {

    private static final Map<Long, Event> events = new HashMap<>();

    @Override
    public Event save(@Nonnull Event object) {
        object.setId((long) (events.size() + 1));
        events.put(object.getId(), object);
        return object;

    }

    @Override
    public void remove(@Nonnull Event object) {
        events.remove(object.getId());
    }

    @Override
    public Event getById(@Nonnull Long id) {
        return events.get(id);
    }

    @Nonnull
    @Override
    public Collection<Event> getAll() {
        return events.values();
    }

    @Override
    public Event getByName(String name) {
        return null;
    }

    @Override
    public Set<Event> getForDateRange(LocalDate from, LocalDate to) {
        return null;
    }

    @Override
    public Set<Event> getNextEvents(LocalDateTime to) {
        return null;
    }
}
