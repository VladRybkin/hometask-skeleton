package ua.training.spring.hometask.dao.impl.jdbctemplate.mapper;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.EventRating;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

@Component
@Profile("JDBC_TEMPLATE")
public class EventMapper implements RowMapper<Event> {

    @Override
    public Event mapRow(final ResultSet resultSet, final int i) throws SQLException {
        final Event event = new Event();
        event.setId(resultSet.getLong("id"));
        event.setName(resultSet.getString("name"));
        event.setBasePrice(resultSet.getDouble("base_price"));
        String eventRating = resultSet.getString("rating");
        setRatingToEvent(event, eventRating);

        return event;
    }

    private void setRatingToEvent(final Event event, final String rating) {
        if (!Objects.equals(rating, "null")) {
            event.setRating(EventRating.valueOf(rating));
        }
    }
}
