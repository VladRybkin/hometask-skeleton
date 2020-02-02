package ua.training.spring.hometask.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.EventRating;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class EventMapper implements RowMapper<Event> {

    @Override
    public Event mapRow(ResultSet resultSet, int i) throws SQLException {
        Event event = new Event();
        event.setId(resultSet.getLong("id"));
        event.setName(resultSet.getString("name"));
        event.setBasePrice(resultSet.getDouble("base_price"));
        String eventRating = resultSet.getString("rating");
        setRatingToEvent(event, eventRating);
        return event;
    }

    private void setRatingToEvent(Event event, String rating) {
        if (!Objects.isNull(rating)) {
            event.setRating(EventRating.valueOf(rating));
        }
    }
}
