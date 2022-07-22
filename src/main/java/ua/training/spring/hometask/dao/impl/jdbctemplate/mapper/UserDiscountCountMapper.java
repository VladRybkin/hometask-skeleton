package ua.training.spring.hometask.dao.impl.jdbctemplate.mapper;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.training.spring.hometask.domain.UserDiscountCount;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@Profile("JDBC_TEMPLATE")
public class UserDiscountCountMapper implements RowMapper<UserDiscountCount> {

    @Override
    public UserDiscountCount mapRow(final ResultSet resultSet, final int i) throws SQLException {
        final UserDiscountCount userDiscountCount = new UserDiscountCount();
        userDiscountCount.setId(resultSet.getLong("id"));
        userDiscountCount.setName(resultSet.getString("name"));
        userDiscountCount.setCountBirthdayDiscount(resultSet.getLong("count_birthday_discount"));
        userDiscountCount.setCountTenthTicketDiscount(resultSet.getLong("count_tenth_ticket_discount"));

        return userDiscountCount;
    }
}
