package ua.training.spring.hometask.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import ua.training.spring.hometask.domain.EventCount;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EventCountMapper implements RowMapper<EventCount> {

    @Override
    public EventCount mapRow(ResultSet resultSet, int i) throws SQLException {
        EventCount eventCount = new EventCount();
        eventCount.setId(resultSet.getLong("id"));
        eventCount.setCountBookTickets(resultSet.getLong("count_book_tickets"));
        eventCount.setCountGetByName(resultSet.getLong("count_get_by_name"));
        eventCount.setCountGetPrice(resultSet.getLong("count_get_price"));

        return eventCount;
    }
}
