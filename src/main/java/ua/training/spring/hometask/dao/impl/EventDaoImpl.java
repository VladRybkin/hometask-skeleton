package ua.training.spring.hometask.dao.impl;

import org.springframework.stereotype.Component;
import ua.training.spring.hometask.dao.AbstractDomainObjectDao;
import ua.training.spring.hometask.domain.Event;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class EventDaoImpl implements AbstractDomainObjectDao<Event> {

    private static final Map<Long, Event> events = new HashMap<>();

    @Override
    public Event save(@Nonnull Event object) {
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
}
