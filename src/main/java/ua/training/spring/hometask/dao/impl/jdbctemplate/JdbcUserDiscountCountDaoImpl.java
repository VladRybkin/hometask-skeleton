package ua.training.spring.hometask.dao.impl.jdbctemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.training.spring.hometask.dao.UserDiscountCountDao;
import ua.training.spring.hometask.dao.impl.jdbctemplate.mapper.UserDiscountCountMapper;
import ua.training.spring.hometask.domain.UserDiscountCount;

import java.util.Collection;

@Repository
@Primary
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

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserDiscountCountMapper discountCountMapper;

    @Override
    public UserDiscountCount getByName(String name) {
        return jdbcTemplate.queryForObject(USER_DISCOUNT_COUNT_GET_BY_NAME_QUERY,
                new Object[]{name}, discountCountMapper);
    }

    @Override
    public UserDiscountCount save(UserDiscountCount object) {
        jdbcTemplate.update(USER_DISCOUNT_COUNT_INSERT_QUERY,
                object.getName(),
                object.getCountBirthdayDiscount(),
                object.getCountTenthTicketDiscount());

        return object;
    }

    @Override
    public void remove(UserDiscountCount object) {
        jdbcTemplate.update(USER_DISCOUNT_COUNT_REMOVE_QUERY, object.getId());
    }

    @Override
    public UserDiscountCount getById(Long id) {
        return jdbcTemplate.queryForObject(USER_DISCOUNT_COUNT_GET_BY_ID_QUERY, new Object[]{id}, discountCountMapper);
    }

    @Override
    public Collection<UserDiscountCount> getAll() {
        return jdbcTemplate.query(USER_DISCOUNT_COUNT_GET_ALL_QUERY, discountCountMapper);
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

}
