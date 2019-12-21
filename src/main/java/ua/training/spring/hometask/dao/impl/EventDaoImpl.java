package ua.training.spring.hometask.dao.impl;

import org.springframework.stereotype.Component;
import ua.training.spring.hometask.dao.EventDao;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.exceptions.EventNotFoundException;

import javax.annotation.Nonnull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


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
        Optional<Event> optionalEvent = events.values().stream().filter(user -> user.getName().equals(name)).findAny();
        Event event;
        if (optionalEvent.isPresent()) {
            event = optionalEvent.get();
        } else {
            throw new EventNotFoundException("Event with name " + name + " not found");
        }
        return event;
    }

    @Override
    public Set<Event> getForDateRange(LocalDate from, LocalDate to) {
        Set<Event> filteredEvents = new HashSet<>();

        return filteredEvents;
    }

    @Override
    public Set<Event> getNextEvents(LocalDateTime to) {
        Set<Event> filteredEvents = new HashSet<>();

        events.values().forEach(event -> event.getAirDates().stream()
                .filter(localDate -> localDate.isBefore(to)).map(localDate -> event)
                .forEach(filteredEvents::add));

        return filteredEvents;

    }
}
