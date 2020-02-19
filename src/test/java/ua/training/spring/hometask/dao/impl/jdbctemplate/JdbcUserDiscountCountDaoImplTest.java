package ua.training.spring.hometask.dao.impl.jdbctemplate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.jdbc.JdbcTestUtils;
import ua.training.spring.hometask.config.BeansConfiguration;
import ua.training.spring.hometask.domain.UserDiscountCount;
import ua.training.spring.hometask.testconfig.TestJdbcTemplateBeans;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BeansConfiguration.class, TestJdbcTemplateBeans.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class JdbcUserDiscountCountDaoImplTest {

    private static final String TABLE_NAME = "user_discount_counts";

    @Autowired
    private JdbcUserDiscountCountDaoImpl jdbcUserDiscountCountDao;

    @Autowired
    @Qualifier("testJdbcTemplate")
    private JdbcTemplate testJdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcUserDiscountCountDao.setJdbcTemplate(testJdbcTemplate);
    }

    @Test
    void getByName() {
        UserDiscountCount discountCount = buildUserDiscountCount();
        jdbcUserDiscountCountDao.save(discountCount);
        UserDiscountCount foundByName = jdbcUserDiscountCountDao.getByName(discountCount.getName());

        assertThat(foundByName, is(discountCount));
    }

    @Test
    void shouldGetByIdPersistedUserDiscountCount() {
        UserDiscountCount discountCount = buildUserDiscountCount();

        jdbcUserDiscountCountDao.save(discountCount);
        UserDiscountCount foundDiscount = jdbcUserDiscountCountDao.getById(1L);

        assertThat(JdbcTestUtils.countRowsInTable(testJdbcTemplate, TABLE_NAME), is(1));
        assertThat(foundDiscount, is(discountCount));
    }

    @Test
    void remove() {
        UserDiscountCount discountCount = buildUserDiscountCount();

        jdbcUserDiscountCountDao.save(discountCount);
        jdbcUserDiscountCountDao.remove(discountCount);

        assertThat(JdbcTestUtils.countRowsInTable(testJdbcTemplate, TABLE_NAME), is(0));

    }

    @Test
    void update() {
        UserDiscountCount discountCount = buildUserDiscountCount();

        jdbcUserDiscountCountDao.save(discountCount);

        discountCount.setName("updated");
        discountCount.setCountTenthTicketDiscount(600);
        discountCount.setCountBirthdayDiscount(600);

        boolean updated = jdbcUserDiscountCountDao.update(discountCount);

        assertThat(updated, is(true));
        assertThat(jdbcUserDiscountCountDao.getById(1L), is(discountCount));
        assertThat(JdbcTestUtils.countRowsInTable(testJdbcTemplate, TABLE_NAME), is(1));

    }

    @Test
    void getAll() {
        UserDiscountCount user = buildUserDiscountCount();
        jdbcUserDiscountCountDao.save(user);

        Collection<UserDiscountCount> persistedDiscounts = jdbcUserDiscountCountDao.getAll();

        assertThat(persistedDiscounts, hasItems(user));
        assertThat(persistedDiscounts, hasSize(1));
    }

    @Test
    void shouldReturnNullWhenGetByEmail() {
        UserDiscountCount discountCount = buildUserDiscountCount();

        assertThat(JdbcTestUtils.countRowsInTable(testJdbcTemplate, TABLE_NAME), is(0));
        UserDiscountCount foundByName = jdbcUserDiscountCountDao.getByName(discountCount.getName());

        assertThat(foundByName, nullValue());
    }

    @Test
    void shouldReturnNullWhenGetById() {
        assertThat(JdbcTestUtils.countRowsInTable(testJdbcTemplate, TABLE_NAME), is(0));

        UserDiscountCount foundById = jdbcUserDiscountCountDao.getById(1L);

        assertThat(foundById, nullValue());
    }

    private UserDiscountCount buildUserDiscountCount() {
        UserDiscountCount userDiscountCount = new UserDiscountCount();
        userDiscountCount.setId(1L);
        userDiscountCount.setName("test discount name");
        userDiscountCount.setCountBirthdayDiscount(0);
        userDiscountCount.setCountTenthTicketDiscount(0);

        return userDiscountCount;
    }
}