package ua.training.spring.hometask.dao.impl.jdbctemplate;

import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.training.spring.hometask.dao.TicketDao;
import ua.training.spring.hometask.dao.mapper.TicketMapper;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.Ticket;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Repository
@Primary
public class JdbcTicketDaoImpl implements TicketDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TicketMapper ticketMapper;

    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(Event event, LocalDateTime dateTime) {
        Object parameters[] = new Object[]{event.getId(), String.valueOf(dateTime)};
        String sql = "select * from tickets t where t.event_id=? AND t.date_time=?";
        Collection<Ticket> tickets = jdbcTemplate.query(sql, parameters, ticketMapper);

        return Sets.newHashSet(tickets);
    }

    @Override
    public Ticket save(Ticket object) {
        String SQL = "INSERT INTO `tickets`(`date_time`, `seat`, `base_price`) VALUES (?,?,?)";
        jdbcTemplate.update(SQL, String.valueOf(object.getDateTime()),
                object.getSeat(), object.getBasePrice());

        return object;
    }

    @Override
    public void remove(Ticket object) {
        String SQL = "delete from tickets where id = ?";
        jdbcTemplate.update(SQL, object.getId());
    }

    @Override
    public Ticket getById(Long id) {
        String sql = "SELECT * FROM `tickets` WHERE `id` = ?";
        Ticket ticket = jdbcTemplate.queryForObject(sql, new Object[]{id},
                ticketMapper);

        return ticket;
    }

    @Override
    public Collection<Ticket> getAll() {
        String sql = "select * from tickets";
        Collection<Ticket> tickets = jdbcTemplate.query(sql, ticketMapper);

        return tickets;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setTicketMapper(TicketMapper ticketMapper) {
        this.ticketMapper = ticketMapper;
    }
}
