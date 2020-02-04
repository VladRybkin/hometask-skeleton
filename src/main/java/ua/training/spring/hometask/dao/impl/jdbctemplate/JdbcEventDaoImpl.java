package ua.training.spring.hometask.dao.impl.jdbctemplate;

import com.google.common.collect.Sets;
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

    @Autowired
    private EventMapper eventMapper;

    @Override
    public Event getByName(String name) {
        String sql = "SELECT * FROM `events` WHERE `name` = ?";
        Event event = jdbcTemplate.queryForObject(sql, new Object[]{name},
                eventMapper);

        return event;
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
        Collection<Event> events = jdbcTemplate.query(sql, eventMapper);

        return events;
    }

    @Override
    public Set<Event> getForDateRange(LocalDateTime from, LocalDateTime to) {
        String sql = "SELECT * FROM events ev left JOIN event_dates ed ON ev.id=ed.event_id JOIN air_dates ai ON ed.air_date_id=ai.id WHERE ai.event_date between ? and ?";
        Object parameters[] = new Object[]{String.valueOf(from), String.valueOf(to)};
        Collection<Event> events = jdbcTemplate.query(sql, parameters, eventMapper);
        return Sets.newHashSet(events);
    }

    @Override
    public Set<Event> getNextEvents(LocalDateTime to) {
        String sql = "SELECT * FROM events ev left JOIN event_dates ed ON ev.id=ed.event_id JOIN air_dates ai ON ed.air_date_id=ai.id WHERE ai.event_date < ?";
        Object parameters[] = new Object[]{String.valueOf(to)};
        Collection<Event> events = jdbcTemplate.query(sql,parameters, eventMapper);
        return Sets.newHashSet(events);
    }
}
