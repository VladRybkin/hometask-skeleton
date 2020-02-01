package ua.training.spring.hometask.dao.impl.immemory;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ua.training.spring.hometask.dao.EventCountDao;
import ua.training.spring.hometask.domain.EventCount;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
@Primary
public class EventCountDaoImpl implements EventCountDao {

    private Map<Long, EventCount> eventCounts = new HashMap<>();

    @Override
    public EventCount save(@Nonnull EventCount object) {
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
    public void remove(@Nonnull EventCount object) {
        eventCounts.remove(object.getId());
    }

    @Override
    public EventCount getById(@Nonnull Long id) {
        return eventCounts.get(id);
    }

    @Nonnull
    @Override
    public Collection<EventCount> getAll() {
        return eventCounts.values();
    }

    @Override
    public EventCount getByName(String name) {
        return eventCounts.values().stream().filter(ev -> ev.getEventName().equals(name)).findAny().orElse(null);
    }
}
