package ua.training.spring.hometask.dao.impl.inmemory;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ua.training.spring.hometask.dao.EventCountDao;
import ua.training.spring.hometask.domain.EventCount;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository("eventCountDaoImpl")
@Profile("IN_MEMORY")
public class EventCountDaoImpl implements EventCountDao {

    private final Map<Long, EventCount> eventCounts = new HashMap<>();

    @Override
    public EventCount save(final EventCount eventCount) {
        eventCount.setId((long) (eventCounts.size() + 1));
        eventCounts.put(eventCount.getId(), eventCount);
        return eventCount;
    }

    @Override
    public void remove(final EventCount eventCount) {
        eventCounts.remove(eventCount.getId());
    }

    @Override
    public EventCount getById(final Long id) {
        return eventCounts.get(id);
    }

    @Override
    public Collection<EventCount> getAll() {
        return eventCounts.values();
    }

    @Override
    public EventCount getByName(final String name) {
        return eventCounts.values().stream().filter(ev -> ev.getEventName().equals(name)).findAny().orElse(null);
    }

    @Override
    public boolean update(final EventCount eventCount) {
        boolean update = false;
        if (eventCounts.containsKey(eventCount.getId())) {
            eventCounts.put(eventCount.getId(), eventCount);
            update = true;
        }

        return update;
    }
}
