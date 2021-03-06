package ua.training.spring.hometask.dao.impl.inmemory;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ua.training.spring.hometask.dao.EventDao;
import ua.training.spring.hometask.domain.Event;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;


@Repository("eventDaoImpl")
@Profile("IN_MEMORY")
public class EventDaoImpl implements EventDao {

    private static final Map<Long, Event> events = new HashMap<>();

    @Override
    public Event save(Event object) {
        object.setId((long) (events.size() + 1));
        events.put(object.getId(), object);
        return object;
    }

    @Override
    public void remove(Event object) {
        events.remove(object.getId());
    }

    @Override
    public Event getById(Long id) {
        return events.get(id);
    }

    @Override
    public Collection<Event> getAll() {
        return events.values();
    }

    @Override
    public Event getByName(String name) {
        return events.values().stream().filter(user -> user.getName().equals(name)).findAny().orElse(null);
    }

    @Override
    public Set<Event> getForDateRange(LocalDateTime from, LocalDateTime to) {
        Set<Event> filteredEvents = new HashSet<>();
        events.values().forEach(event -> event.getAirDates().stream()
                .filter(filterAfterDate(from).and(filterBeforeDate(to))).map(localDate -> event)
                .forEach(filteredEvents::add));

        return filteredEvents;
    }


    @Override
    public Set<Event> getNextEvents(LocalDateTime to) {
        Set<Event> filteredEvents = new HashSet<>();
        events.values().forEach(event -> event.getAirDates().stream()
                .filter(filterBeforeDate(to)).map(localDate -> event)
                .forEach(filteredEvents::add));

        return filteredEvents;
    }

    @Override
    public boolean update(Event event) {
        boolean update = false;
        if (events.containsKey(event.getId())) {
            events.put(event.getId(), event);
            update = true;
        }

        return update;
    }

    private Predicate<LocalDateTime> filterBeforeDate(LocalDateTime to) {
        return localDate -> localDate.isBefore(to);
    }

    private Predicate<LocalDateTime> filterAfterDate(LocalDateTime from) {
        return localDate -> localDate.isAfter(from);
    }
}
