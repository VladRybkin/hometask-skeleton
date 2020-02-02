package ua.training.spring.hometask.dao.impl.jdbctemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.training.spring.hometask.dao.UserDao;
import ua.training.spring.hometask.dao.mapper.UserMapper;
import ua.training.spring.hometask.domain.User;

import java.util.Collection;


@Repository
@Primary
public class JdbcUserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM `users` WHERE `email` = ?";
        User user = jdbcTemplate.queryForObject(sql, new Object[]{email},
                new UserMapper());

        return user;
    }

    @Override
    public User save(User object) {
        String SQL = "INSERT INTO `users`(`first_name`, `last_name`, `email`, `date_of_birth`) VALUES (?,?,?,?)";
        jdbcTemplate.update(SQL, object.getFirstName(), object.getLastName(), object.getEmail(),
                object.getDateOfBirth());

        return object;
    }

    @Override
    public void remove(User object) {
        String SQL = "delete from users where id = ?";
        jdbcTemplate.update(SQL, object.getId());
    }

    @Override
    public User getById(Long id) {
        String sql = "SELECT * FROM `users` WHERE `id` = ?";
        User user = jdbcTemplate.queryForObject(sql, new Object[]{id},
                new UserMapper());

        return user;
    }

    @Override
    public Collection<User> getAll() {
        String sql="select * from users";
        Collection<User> users = jdbcTemplate.query(sql, new UserMapper());

        return users;
    }
}
