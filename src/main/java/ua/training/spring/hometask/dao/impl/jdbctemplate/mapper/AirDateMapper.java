package ua.training.spring.hometask.dao.impl.jdbctemplate.mapper;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Objects;

@Component
@Profile("JDBC_TEMPLATE")
public class AirDateMapper implements RowMapper<LocalDateTime> {

    @Override
    public LocalDateTime mapRow(final ResultSet resultSet, final int i) throws SQLException {
        final String airDateString = resultSet.getString("event_date");

        return convertDate(airDateString);
    }

    private LocalDateTime convertDate(String date) {
        LocalDateTime localDateTime = null;

        if (!Objects.isNull(date)) {
            localDateTime = LocalDateTime.parse(date);
        }

        return localDateTime;
    }
}
