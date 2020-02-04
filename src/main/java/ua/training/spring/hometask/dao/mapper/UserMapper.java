package ua.training.spring.hometask.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.training.spring.hometask.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Objects;

@Component
public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setEmail(resultSet.getString("email"));

        String dateOfBirth = resultSet.getString("date_of_birth");
        setDateOfBirthToUser(user, dateOfBirth);

        return user;
    }

    private void setDateOfBirthToUser(User user, String dateOfBirth) {
        if (!Objects.isNull(dateOfBirth)) {
            user.setDateOfBirth(LocalDateTime.parse(dateOfBirth));
        }
    }
}
