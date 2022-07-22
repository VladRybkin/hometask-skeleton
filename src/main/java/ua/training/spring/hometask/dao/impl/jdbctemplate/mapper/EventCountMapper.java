package ua.training.spring.hometask.dao.impl.jdbctemplate.mapper;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.training.spring.hometask.domain.EventCount;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@Profile("JDBC_TEMPLATE")
public class EventCountMapper implements RowMapper<EventCount> {

    @Override
    public EventCount mapRow(final ResultSet resultSet, final int i) throws SQLException {
        final EventCount eventCount = new EventCount();
        eventCount.setId(resultSet.getLong("id"));
        eventCount.setEventName(resultSet.getString("name"));
        eventCount.setCountBookTickets(resultSet.getLong("count_book_tickets"));
        eventCount.setCountGetByName(resultSet.getLong("count_get_by_name"));
        eventCount.setCountGetPrice(resultSet.getLong("count_get_price"));

        return eventCount;
    }
}
