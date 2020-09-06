package ua.training.spring.hometask.dao;

import ua.training.spring.hometask.domain.Event;

import java.time.LocalDateTime;
import java.util.Set;

public interface EventDao extends AbstractDomainObjectDao<Event> {

    Event getByName(String name);

    Set<Event> getForDateRange(LocalDateTime from,
                               LocalDateTime to);

    Set<Event> getNextEvents(LocalDateTime to);
}
