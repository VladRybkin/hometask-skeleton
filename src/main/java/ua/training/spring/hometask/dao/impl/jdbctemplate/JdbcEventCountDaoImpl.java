package ua.training.spring.hometask.dao.impl.jdbctemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.training.spring.hometask.dao.EventCountDao;
import ua.training.spring.hometask.dao.mapper.EventCountMapper;
import ua.training.spring.hometask.domain.EventCount;

import java.util.Collection;

@Repository
@Primary
public class JdbcEventCountDaoImpl implements EventCountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EventCountMapper eventCountMapper;

    @Override
    public EventCount getByName(String name) {
        String sql = "SELECT * FROM `event_counts` WHERE `name` = ?";
        EventCount eventCount = jdbcTemplate.queryForObject(sql, new Object[]{name},
                eventCountMapper);

        return eventCount;
    }

    @Override
    public EventCount save(EventCount object) {
        String SQL = "INSERT INTO `event_counts`(`name`, `count_get_by_name`, `count_book_tickets`, `count_get_price`) VALUES (?,?,?, ?)";
        jdbcTemplate.update(SQL, object.getEventName(), object.getCountGetByName(), object.getCountBookTickets(), object.getCountGetPrice());

        return object;
    }

    @Override
    public void remove(EventCount object) {
        String SQL = "delete from event_counts where id = ?";
        jdbcTemplate.update(SQL, object.getId());
    }

    @Override
    public EventCount getById(Long id) {
        String sql = "SELECT * FROM `event_counts` WHERE `id` = ?";
        EventCount eventCount = jdbcTemplate.queryForObject(sql, new Object[]{id},
                eventCountMapper);

        return eventCount;
    }

    @Override
    public Collection<EventCount> getAll() {
        String sql = "select * from event_counts";
        Collection<EventCount> eventCounts = jdbcTemplate.query(sql, eventCountMapper);

        return eventCounts;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setEventCountMapper(EventCountMapper eventCountMapper) {
        this.eventCountMapper = eventCountMapper;
    }
}
