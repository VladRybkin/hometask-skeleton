package ua.training.spring.hometask.dao.impl.jdbctemplate;

import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.training.spring.hometask.dao.EventDao;
import ua.training.spring.hometask.dao.mapper.AirDateMapper;
import ua.training.spring.hometask.dao.mapper.EventMapper;
import ua.training.spring.hometask.dao.mapper.exctractors.EventResultSetExtractor;
import ua.training.spring.hometask.domain.Event;


import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Repository
@Primary
public class JdbcEventDaoImpl implements EventDao {

    private static final String GET_BY_NAME_QUERY = "SELECT * FROM `events` WHERE `name` = ?";

    private static final String INSERT_INTO_EVENTS = "INSERT INTO `events`(`name`, `base_price`, `rating`) VALUES (?,?,?)";

    private static final String INSERT_INTO_AIR_DATES = "INSERT INTO air_dates (event_date) " +
            "VALUES (?)";

    private static final String FIND_AIR_DATE_BY_EVENT_DATE = "SELECT * FROM air_dates a WHERE a.event_date=?";

    private static final String INSERT_RELATIONS = "INSERT INTO event_dates (event_id, air_date_id)" +
            "(SELECT e.id, ed.id " +
            "FROM events e, air_dates ed " +
            "WHERE e.name = ? AND ed.event_date = ?)";


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EventMapper eventMapper;

    @Autowired
    private AirDateMapper airDateMapper;

    @Autowired
    private EventResultSetExtractor eventResultSetExtractor;

    @Override
    public Event getByName(String name) {

        Event event = jdbcTemplate.queryForObject(GET_BY_NAME_QUERY, new Object[]{name},
                eventMapper);

        return event;
    }

    @Override
    public Event save(Event object) {
        jdbcTemplate.update(INSERT_INTO_EVENTS, object.getName(), object.getBasePrice(), String.valueOf(object.getRating()));

        object.getAirDates().forEach(airDate -> {
            insertAirDateIfNotExist(airDate);
            jdbcTemplate.update(INSERT_RELATIONS, object.getName(), String.valueOf(airDate));
        });

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
        String sql = "SELECT * FROM events ev LEFT JOIN event_dates ed ON ed.event_id=ev.id LEFT JOIN air_dates ai ON ai.id=ed.air_date_id";

        Collection<Event> events = jdbcTemplate.query(sql, eventResultSetExtractor);

        return events;
    }

    @Override
    public Set<Event> getForDateRange(LocalDateTime from, LocalDateTime to) {
        String sql = "SELECT * FROM events ev left JOIN event_dates ed ON ev.id=ed.event_id JOIN air_dates ai ON ed.air_date_id=ai.id WHERE ai.event_date BETWEEN ? AND ?";
        Object parameters[] = new Object[]{String.valueOf(from), String.valueOf(to)};

        Collection<Event> events = jdbcTemplate.query(sql, parameters, eventResultSetExtractor);
        return Sets.newHashSet(events);
    }

    @Override
    public Set<Event> getNextEvents(LocalDateTime to) {
        String sql = "SELECT * FROM events ev left JOIN event_dates ed ON ev.id=ed.event_id JOIN air_dates ai ON ed.air_date_id=ai.id WHERE ai.event_date < ?";
        Object parameters[] = new Object[]{String.valueOf(to)};
        Collection<Event> events = jdbcTemplate.query(sql, parameters, eventResultSetExtractor);

        return Sets.newHashSet(events);
    }

    private void insertAirDateIfNotExist(LocalDateTime airDate) {
        if (jdbcTemplate.query(FIND_AIR_DATE_BY_EVENT_DATE, new Object[]{String.valueOf(airDate)}, airDateMapper).isEmpty()) {
            jdbcTemplate.update(INSERT_INTO_AIR_DATES, String.valueOf(airDate));
        }


    }


    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    

}
