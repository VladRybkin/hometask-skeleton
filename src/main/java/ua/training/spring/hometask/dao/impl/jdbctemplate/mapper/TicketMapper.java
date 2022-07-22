package ua.training.spring.hometask.dao.impl.jdbctemplate.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.training.spring.hometask.dao.EventDao;
import ua.training.spring.hometask.dao.UserDao;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Objects;

@Component
@Profile("JDBC_TEMPLATE")
public class TicketMapper implements RowMapper<Ticket> {

    @Autowired
    @Qualifier("jdbcUserDaoImpl")
    private UserDao userDao;

    @Autowired
    @Qualifier("jdbcEventDaoImpl")
    private EventDao eventDao;

    @Override
    public Ticket mapRow(final ResultSet resultSet, final  int i) throws SQLException {
        final Ticket ticket = new Ticket();
        long userId = resultSet.getLong("user_id");
        long eventId = resultSet.getLong("event_id");

        ticket.setId(resultSet.getLong("id"));
        ticket.setSeat(resultSet.getLong("seat"));
        ticket.setBasePrice(resultSet.getDouble("base_price"));
        String dateTime = resultSet.getString("date_time");

        setDateTimeToTicket(ticket, dateTime);
        setEventToTicket(ticket, eventId);
        setUserToTicket(ticket, userId);

        return ticket;
    }

    private void setDateTimeToTicket(final Ticket ticket, final String dateTime) {
        if (!Objects.equals(dateTime, "null")) {
            ticket.setDateTime(LocalDateTime.parse(dateTime));
        }
    }

    private void setUserToTicket(final Ticket ticket, final long userId) {
        if (userId != 0) {
            final User user = userDao.getById(userId);

            if (!Objects.isNull(user)) {
                ticket.setUser(user);
            }
        }
    }

    private void setEventToTicket(final Ticket ticket, final  Long eventId) {
        if (eventId != 0) {
            Event event = eventDao.getById(eventId);

            if (!Objects.isNull(event)) {
                ticket.setEvent(event);
            }
        }
    }
}
