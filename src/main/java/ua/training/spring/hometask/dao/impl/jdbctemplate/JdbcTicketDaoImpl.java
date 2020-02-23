package ua.training.spring.hometask.dao.impl.jdbctemplate;

import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.training.spring.hometask.dao.TicketDao;
import ua.training.spring.hometask.dao.impl.jdbctemplate.mapper.TicketMapper;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.Ticket;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Repository
@Primary
public class JdbcTicketDaoImpl implements TicketDao {

    private static final String GET_PURCHASED_TICKET_FOR_EVENT_QUERY = "select * from tickets t where t.event_id=?" +
            " AND t.date_time=? AND t.user_id IS NOT null";

    private static final String TICKETS_INSERT_QUERY =
            "INSERT INTO `tickets`(`date_time`, `seat`, `base_price`, `user_id`, `event_id`) VALUES (?,?,?,?,?)";

    private static final String TICKETS_REMOVE_QUERY = "delete from tickets where id = ?";

    private static final String TICKETS_GET_BY_ID_QUERY = "SELECT * FROM `tickets` WHERE `id` = ?";

    private static final String TICKETS_GET_ALL_QUERY = "select * from tickets";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TicketMapper ticketMapper;

    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(Event event, LocalDateTime dateTime) {
        Long eventId = event == null ? null : event.getId();
        Object parameters[] = new Object[]{eventId, String.valueOf(dateTime)};

        Collection<Ticket> tickets = jdbcTemplate
                .query(GET_PURCHASED_TICKET_FOR_EVENT_QUERY, parameters, ticketMapper);

        return Sets.newHashSet(tickets);
    }

    @Override
    public Ticket save(Ticket object) {
        Long eventId = object.getEvent() == null ? null : object.getEvent().getId();
        Long userId = object.getUser() == null ? null : object.getUser().getId();

        jdbcTemplate.update(TICKETS_INSERT_QUERY,
                String.valueOf(object.getDateTime()),
                object.getSeat(),
                object.getBasePrice(),
                userId, eventId);

        return object;
    }

    @Override
    public void remove(Ticket object) {
        jdbcTemplate.update(TICKETS_REMOVE_QUERY, object.getId());
    }

    @Override
    public Ticket getById(Long id) {
        Ticket ticket;
        try {
            ticket = jdbcTemplate.queryForObject(TICKETS_GET_BY_ID_QUERY, new Object[]{id}, ticketMapper);
        } catch (EmptyResultDataAccessException e) {
            ticket = null;
        }

        return ticket;
    }

    @Override
    public Collection<Ticket> getAll() {
        return jdbcTemplate.query(TICKETS_GET_ALL_QUERY, ticketMapper);
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
