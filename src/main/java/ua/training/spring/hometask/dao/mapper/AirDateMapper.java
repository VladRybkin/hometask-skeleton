package ua.training.spring.hometask.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Objects;

@Component
public class AirDateMapper implements RowMapper<LocalDateTime> {
    @Override
    public LocalDateTime mapRow(ResultSet resultSet, int i) throws SQLException {
        String airDateString = resultSet.getString("event_date");
        LocalDateTime localDateTime = convertDate(airDateString);

        return localDateTime;
    }


    private LocalDateTime convertDate(String date) {
        LocalDateTime localDateTime = null;

        if (!Objects.equals(date, "null")) {
            localDateTime = LocalDateTime.parse(date);
        }
        return localDateTime;
    }
}
