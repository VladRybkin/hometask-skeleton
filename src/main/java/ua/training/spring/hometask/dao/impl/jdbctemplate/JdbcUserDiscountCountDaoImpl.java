package ua.training.spring.hometask.dao.impl.jdbctemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.training.spring.hometask.dao.UserDiscountCountDao;
import ua.training.spring.hometask.dao.mapper.UserDiscountCountMapper;
import ua.training.spring.hometask.domain.UserDiscountCount;

import java.util.Collection;

@Repository
@Primary
public class JdbcUserDiscountCountDaoImpl implements UserDiscountCountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserDiscountCountMapper discountCountMapper;

    @Override
    public UserDiscountCount getByName(String name) {
        String sql = "SELECT * FROM `user_discount_counts` WHERE `name` = ?";
        UserDiscountCount discountCount = jdbcTemplate.queryForObject(sql, new Object[]{name},
                discountCountMapper);

        return discountCount;
    }

    @Override
    public UserDiscountCount save(UserDiscountCount object) {
        String SQL =
                "INSERT INTO `user_discount_counts`(`name`, `count_birthday_discount`, `count_tenth_ticket_discount`) VALUES "
                        + "(?,?,?)";
        jdbcTemplate.update(SQL, object.getName(), object.getCountBirthdayDiscount(), object.getCountTenthTicketDiscount());

        return object;
    }

    @Override
    public void remove(UserDiscountCount object) {
        String SQL = "delete from user_discount_counts where id = ?";
        jdbcTemplate.update(SQL, object.getId());
    }

    @Override
    public UserDiscountCount getById(Long id) {
        String sql = "SELECT * FROM `user_discount_counts` WHERE `id` = ?";
        UserDiscountCount discountCount = jdbcTemplate.queryForObject(sql, new Object[]{id},
                discountCountMapper);

        return discountCount;
    }

    @Override
    public Collection<UserDiscountCount> getAll() {
        String sql = "select * from user_discount_counts";
        Collection<UserDiscountCount> discountCounts = jdbcTemplate.query(sql, discountCountMapper);

        return discountCounts;
    }
}
