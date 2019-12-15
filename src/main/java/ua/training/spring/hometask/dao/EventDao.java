package ua.training.spring.hometask.dao;

import ua.training.spring.hometask.domain.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public interface EventDao extends AbstractDomainObjectDao<Event> {

    Event getByName(String name);


    Set<Event> getForDateRange(LocalDate from,
            LocalDate to);


    Set<Event> getNextEvents(LocalDateTime to);

}
