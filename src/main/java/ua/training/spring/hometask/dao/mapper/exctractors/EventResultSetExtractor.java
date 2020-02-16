package ua.training.spring.hometask.dao.mapper.exctractors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import ua.training.spring.hometask.dao.mapper.AirDateMapper;
import ua.training.spring.hometask.dao.mapper.EventMapper;
import ua.training.spring.hometask.domain.Event;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class EventResultSetExtractor implements ResultSetExtractor<Collection<Event>> {

    @Autowired
    private EventMapper eventMapper;

    @Autowired
    private AirDateMapper airDateMapper;

    @Override
    public Collection<Event> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Map<Long, Event> events = new HashMap<>();
        int row = 0;
        Event event;

        while (resultSet.next()) {
            event = eventMapper.mapRow(resultSet, row);
            LocalDateTime airDate = airDateMapper.mapRow(resultSet, row);

            if (events.containsKey(event.getId())) {
                addAirDateToEvent(airDate, event);
                events.get(event.getId()).addAirDateTime(airDate);

            } else {
                addAirDateToEvent(airDate, event);
                events.put(event.getId(), event);

            }

            row++;
        }

        return events.values();
    }

    private void addAirDateToEvent(LocalDateTime airDate, Event event) {
        if (!Objects.isNull(airDate)) {
            event.getAirDates().add(airDate);
        }
    }

}
