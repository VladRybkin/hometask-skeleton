package ua.training.spring.hometask.dao.impl.jdbctemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.training.spring.hometask.dao.TicketDao;
import ua.training.spring.hometask.dao.mapper.TicketMapper;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.domain.User;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Repository
@Primary
public class JdbcTicketDaoImpl implements TicketDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(Event event, LocalDateTime dateTime) {
        return null;
    }

    @Override
    public Ticket save(Ticket object) {
        String SQL = "INSERT INTO `tickets`(`user_id`, `event_id`, `date_time`, `seat`, `base_price`) VALUES (?,?,?,?, ?)";
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
                new TicketMapper());

        return ticket;
    }

    @Override
    public Collection<Ticket> getAll() {
        String sql = "select * from tickets";
        Collection<Ticket> tickets = jdbcTemplate.query(sql, new TicketMapper());

        return tickets;
    }

    private String prepareSqlQuery() {
        return null;
    }
}
