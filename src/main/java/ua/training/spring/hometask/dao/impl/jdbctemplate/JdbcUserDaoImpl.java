package ua.training.spring.hometask.dao.impl.jdbctemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.training.spring.hometask.dao.UserDao;
import ua.training.spring.hometask.dao.impl.jdbctemplate.mapper.UserMapper;
import ua.training.spring.hometask.domain.User;

import java.util.Collection;


@Repository
@Primary
public class JdbcUserDaoImpl implements UserDao {

    private static final String USER_GET_BY_EMAIL_QUERY = "SELECT * FROM `users` WHERE `email` = ?";

    private static final String USERS_INSERT_QUERY =
            "INSERT INTO `users`(`first_name`, `last_name`, `email`, `date_of_birth`) VALUES (?,?,?,?)";

    private static final String USERS_DELETE_QUERY = "delete from users where id = ?";

    private static final String USERS_GET_BY_ID_QUERY = "SELECT * FROM `users` WHERE `id` = ?";

    private static final String USERS_GET_ALL_QUERY = "select * from users";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByEmail(String email) {
        User user;
        try {
            user = jdbcTemplate.queryForObject(USER_GET_BY_EMAIL_QUERY, new Object[]{email}, userMapper);
        } catch (EmptyResultDataAccessException e) {
            user = null;
        }

        return user;
    }

    @Override
    public User save(User object) {
        jdbcTemplate.update(USERS_INSERT_QUERY,
                object.getFirstName(),
                object.getLastName(),
                object.getEmail(),
                object.getDateOfBirth());

        return object;
    }

    @Override
    public void remove(User object) {
        jdbcTemplate.update(USERS_DELETE_QUERY, object.getId());
    }

    @Override
    public User getById(Long id) {
        User user;
        try {
            user = jdbcTemplate.queryForObject(USERS_GET_BY_ID_QUERY, new Object[]{id}, userMapper);
        } catch (EmptyResultDataAccessException e) {
            user = null;
        }

        return user;
    }

    @Override
    public Collection<User> getAll() {
        return jdbcTemplate.query(USERS_GET_ALL_QUERY, userMapper);
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

}
