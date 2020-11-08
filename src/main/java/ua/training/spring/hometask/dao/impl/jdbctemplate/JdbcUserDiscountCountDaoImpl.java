package ua.training.spring.hometask.dao.impl.jdbctemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.training.spring.hometask.dao.UserDiscountCountDao;
import ua.training.spring.hometask.dao.impl.jdbctemplate.mapper.UserDiscountCountMapper;
import ua.training.spring.hometask.domain.UserDiscountCount;

import java.util.Collection;

@Repository
@Profile("JDBC_TEMPLATE")
public class JdbcUserDiscountCountDaoImpl implements UserDiscountCountDao {

    private static final String USER_DISCOUNT_COUNT_GET_BY_NAME_QUERY =
            "SELECT * FROM `user_discount_counts` WHERE `name` = ?";

    private static final String USER_DISCOUNT_COUNT_INSERT_QUERY =
            "INSERT INTO `user_discount_counts`(`name`, `count_birthday_discount`, `count_tenth_ticket_discount`) " +
                    "VALUES "
                    + "(?,?,?)";

    private static final String USER_DISCOUNT_COUNT_REMOVE_QUERY = "delete from user_discount_counts where id = ?";

    private static final String USER_DISCOUNT_COUNT_GET_ALL_QUERY = "select * from user_discount_counts";

    private static final String USER_DISCOUNT_COUNT_GET_BY_ID_QUERY =
            "SELECT * FROM `user_discount_counts` WHERE `id` = ?";

    private static final String USER_DISCOUNT_COUNT_UPDATE_QUERY =
            "UPDATE user_discount_counts u SET name=?, count_tenth_ticket_discount=?, u.count_birthday_discount=? " +
                    "WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserDiscountCountMapper discountCountMapper;

    @Override
    public UserDiscountCount getByName(String name) {
        UserDiscountCount userDiscountCount;
        try {
            userDiscountCount = jdbcTemplate.queryForObject(USER_DISCOUNT_COUNT_GET_BY_NAME_QUERY,
                    new Object[]{name}, discountCountMapper);
        } catch (EmptyResultDataAccessException e) {
            userDiscountCount = null;
        }

        return userDiscountCount;
    }

    @Override
    public UserDiscountCount save(UserDiscountCount userDiscountCount) {
        jdbcTemplate.update(USER_DISCOUNT_COUNT_INSERT_QUERY,
                userDiscountCount.getName(),
                userDiscountCount.getCountBirthdayDiscount(),
                userDiscountCount.getCountTenthTicketDiscount());

        return userDiscountCount;
    }

    @Override
    public void remove(UserDiscountCount userDiscountCount) {
        jdbcTemplate.update(USER_DISCOUNT_COUNT_REMOVE_QUERY, userDiscountCount.getId());
    }

    @Override
    public UserDiscountCount getById(Long id) {
        UserDiscountCount userDiscountCount;
        try {
            userDiscountCount = jdbcTemplate.queryForObject(USER_DISCOUNT_COUNT_GET_BY_ID_QUERY,
                    new Object[]{id}, discountCountMapper);
        } catch (EmptyResultDataAccessException e) {
            userDiscountCount = null;
        }

        return userDiscountCount;
    }

    @Override
    public Collection<UserDiscountCount> getAll() {
        return jdbcTemplate.query(USER_DISCOUNT_COUNT_GET_ALL_QUERY, discountCountMapper);
    }

    @Override
    public boolean update(UserDiscountCount userDiscountCount) {
        boolean updated = false;
        int affectedRows = jdbcTemplate.update(USER_DISCOUNT_COUNT_UPDATE_QUERY,
                userDiscountCount.getName(),
                userDiscountCount.getCountTenthTicketDiscount(),
                userDiscountCount.getCountBirthdayDiscount(),
                userDiscountCount.getId());
        if (affectedRows > 0) {
            updated = true;
        }

        return updated;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
