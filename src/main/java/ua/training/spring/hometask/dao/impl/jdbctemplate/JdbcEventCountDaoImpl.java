package ua.training.spring.hometask.dao.impl.jdbctemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.training.spring.hometask.dao.EventCountDao;
import ua.training.spring.hometask.dao.impl.jdbctemplate.mapper.EventCountMapper;
import ua.training.spring.hometask.domain.EventCount;

import java.util.Collection;

@Repository
@Primary
public class JdbcEventCountDaoImpl implements EventCountDao {

    private static final String EVENT_COUNT_SAVE_QUERY =
            "INSERT INTO `event_counts`(`name`, `count_get_by_name`, `count_book_tickets`, " +
                    "`count_get_price`) VALUES (?,?,?, ?)";

    private static final String EVENT_COUNT_GET_BY_NAME_QUERY = "SELECT * FROM `event_counts` WHERE `name` = ?";

    private static final String EVENT_COUNT_REMOVE_QUERY = "delete from event_counts where id = ?";

    private static final String EVENT_COUNT_GET_BY_ID_QUERY = "SELECT * FROM `event_counts` WHERE `id` = ?";

    private static final String EVEN_COUNT_UPDATE = "UPDATE event_counts SET name=?, count_get_by_name=?, " +
            "count_book_tickets=?, count_get_price=? WHERE id=?";

    private static final String EVENT_COUNT_GET_ALL_QUERY = "select * from event_counts";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EventCountMapper eventCountMapper;

    @Override
    public EventCount getByName(String name) {
        EventCount eventCount;
        try {
            eventCount = jdbcTemplate.queryForObject(EVENT_COUNT_GET_BY_NAME_QUERY, new Object[]{name}, eventCountMapper);
        } catch (EmptyResultDataAccessException e) {
            eventCount = null;
        }

        return eventCount;
    }

    @Override
    public EventCount save(EventCount object) {
        jdbcTemplate.update(EVENT_COUNT_SAVE_QUERY,
                object.getEventName(),
                object.getCountGetByName(),
                object.getCountBookTickets(),
                object.getCountGetPrice());

        return object;
    }

    @Override
    public void remove(EventCount object) {
        jdbcTemplate.update(EVENT_COUNT_REMOVE_QUERY, object.getId());
    }

    @Override
    public EventCount getById(Long id) {
        EventCount eventCount;
        try {
            eventCount = jdbcTemplate.queryForObject(EVENT_COUNT_GET_BY_ID_QUERY, new Object[]{id}, eventCountMapper);
        } catch (EmptyResultDataAccessException e) {
            eventCount = null;
        }

        return eventCount;
    }

    @Override
    public Collection<EventCount> getAll() {
        return jdbcTemplate.query(EVENT_COUNT_GET_ALL_QUERY, eventCountMapper);
    }

    @Override
    public boolean update(EventCount eventCount) {
        boolean updated = false;
        int affectedRows = jdbcTemplate.update
                (EVEN_COUNT_UPDATE,
                        eventCount.getEventName(),
                        eventCount.getCountGetByName(),
                        eventCount.getCountBookTickets(),
                        eventCount.getCountGetPrice(),
                        eventCount.getId());
        if (affectedRows > 0) updated = true;
        return updated;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

}
