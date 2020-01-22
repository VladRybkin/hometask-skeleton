package ua.training.spring.hometask.dao.impl;

import org.springframework.stereotype.Component;
import ua.training.spring.hometask.dao.EventCounterDao;
import ua.training.spring.hometask.domain.EventCountInfo;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class EventCounterDaoImpl implements EventCounterDao {

    private Map<Long, EventCountInfo> eventCounts = new HashMap<>();

    @Override
    public EventCountInfo save(@Nonnull EventCountInfo object) {
        if (eventCounts.containsKey(object.getId())) {
            eventCounts.put(object.getId(), object);
            return object;
        } else {
            object.setId((long) (eventCounts.size() + 1));
            eventCounts.put(object.getId(), object);
            return object;
        }
    }

    @Override
    public void remove(@Nonnull EventCountInfo object) {
        eventCounts.remove(object.getId());
    }

    @Override
    public EventCountInfo getById(@Nonnull Long id) {
        return eventCounts.get(id);
    }

    @Nonnull
    @Override
    public Collection<EventCountInfo> getAll() {
        return eventCounts.values();
    }

    @Override
    public EventCountInfo getByName(String name) {
        return eventCounts.values().stream().filter(ev -> ev.getEventName().equals(name)).findAny().orElse(null);
    }
}
