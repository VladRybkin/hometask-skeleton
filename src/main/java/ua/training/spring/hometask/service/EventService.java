package ua.training.spring.hometask.service;

import ua.training.spring.hometask.domain.Event;

import java.time.LocalDateTime;
import java.util.Set;


public interface EventService extends AbstractDomainObjectService<Event> {

    Event getByName(String name);

    Set<Event> getForDateRange(LocalDateTime from,
                               LocalDateTime to);

    Set<Event> getNextEvents(LocalDateTime to);
}
