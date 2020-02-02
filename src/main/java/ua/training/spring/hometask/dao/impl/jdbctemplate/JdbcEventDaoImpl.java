package ua.training.spring.hometask.dao.impl.jdbctemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.training.spring.hometask.dao.EventDao;
import ua.training.spring.hometask.dao.mapper.EventMapper;
import ua.training.spring.hometask.domain.Event;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Repository
@Primary
public class JdbcEventDaoImpl implements EventDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Event getByName(String name) {
        String sql = "SELECT * FROM `events` WHERE `name` = ?";
        Event event = jdbcTemplate.queryForObject(sql, new Object[]{name},
                new EventMapper());

        return event;
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
        String SQL = "INSERT INTO `events`(`name`, `base_price`, `rating`) VALUES (?,?,?)";
        jdbcTemplate.update(SQL, object.getName(), object.getBasePrice(), String.valueOf(object.getRating()));

        return object;
    }

    @Override
    public void remove(Event object) {
        String SQL = "delete from events where id = ?";
        jdbcTemplate.update(SQL, object.getId());
    }

    @Override
    public Event getById(Long id) {
        String sql = "SELECT * FROM `events` WHERE `id` = ?";
        Event event = jdbcTemplate.queryForObject(sql, new Object[]{id},
                new EventMapper());

        return event;
    }

    @Override
    public Collection<Event> getAll() {
        String sql = "SELECT * FROM events";
        Collection<Event> events = jdbcTemplate.query(sql, new EventMapper());

        return events;
    }
}
