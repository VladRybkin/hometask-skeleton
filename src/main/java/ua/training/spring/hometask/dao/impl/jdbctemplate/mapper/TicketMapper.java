package ua.training.spring.hometask.dao.impl.jdbctemplate.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.service.EventService;
import ua.training.spring.hometask.service.UserService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Objects;

@Component
public class TicketMapper implements RowMapper<Ticket> {

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Override
    public Ticket mapRow(ResultSet resultSet, int i) throws SQLException {
        Ticket ticket = new Ticket();
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

    private void setDateTimeToTicket(Ticket ticket, String dateTime) {
        if (!Objects.equals(dateTime, "null")) {
            ticket.setDateTime(LocalDateTime.parse(dateTime));
        }
    }

    private void setUserToTicket(Ticket ticket, long userId) {
        if (userId != 0) {
            User user = userService.getById(userId);

            if (!Objects.isNull(user)) {
                ticket.setUser(user);
            }
        }
    }

    private void setEventToTicket(Ticket ticket, long eventId) {
        if (eventId != 0) {
            Event event = eventService.getById(eventId);

            if (!Objects.isNull(event)) {
                ticket.setEvent(event);
            }
        }
    }
}
