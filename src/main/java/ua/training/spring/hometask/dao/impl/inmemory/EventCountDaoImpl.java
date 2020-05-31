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

    private Map<Long, EventCount> eventCounts = new HashMap<>();

    @Override
    public EventCount save(EventCount object) {
        object.setId((long) (eventCounts.size() + 1));
        eventCounts.put(object.getId(), object);
        return object;
    }

    @Override
    public void remove(EventCount object) {
        eventCounts.remove(object.getId());
    }

    @Override
    public EventCount getById(Long id) {
        return eventCounts.get(id);
    }

    @Override
    public Collection<EventCount> getAll() {
        return eventCounts.values();
    }

    @Override
    public EventCount getByName(String name) {
        return eventCounts.values().stream().filter(ev -> ev.getEventName().equals(name)).findAny().orElse(null);
    }

    @Override
    public boolean update(EventCount eventCount) {
        boolean update = false;
        if (eventCounts.containsKey(eventCount.getId())) {
            eventCounts.put(eventCount.getId(), eventCount);
            update = true;
        }
        return update;
    }
}
