package ua.training.spring.hometask.dao.impl.jdbctemplate;

import ua.training.spring.hometask.dao.EventDao;
import ua.training.spring.hometask.domain.Event;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

public class JdbcEventDaoImpl implements EventDao {

    @Override
    public Event getByName(String name) {
        return null;
    }

    @Override
    public Set<Event> getForDateRange(LocalDateTime from, LocalDateTime to) {
        return null;
    }

    @Override
    public Set<Event> getNextEvents(LocalDateTime to) {
        return null;
    }

    @Override
    public Event save(Event object) {
        return null;
    }

    @Override
    public void remove(Event object) {

    }

    @Override
    public Event getById(Long id) {
        return null;
    }

    @Override
    public Collection<Event> getAll() {
        return null;
    }
}
