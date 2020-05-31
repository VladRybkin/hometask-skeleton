package ua.training.spring.hometask.dao.impl.jdbctemplate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.jdbc.JdbcTestUtils;
import ua.training.spring.hometask.config.BeansConfiguration;
import ua.training.spring.hometask.domain.UserDiscountCount;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static ua.training.spring.hometask.utills.BuildTestEntityUtill.buildUserDiscountCount;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BeansConfiguration.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles({"JDBC_TEMPLATE", "TEST"})
class JdbcUserDiscountCountDaoImplIntegrationTest {

    private static final String TABLE_NAME = "user_discount_counts";

    @Autowired
    private JdbcUserDiscountCountDaoImpl jdbcUserDiscountCountDao;

    @Autowired
    private JdbcTemplate testJdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcUserDiscountCountDao.setJdbcTemplate(testJdbcTemplate);
    }

    @Test
    void getByName() {
        UserDiscountCount discountCount = buildUserDiscountCount();

        jdbcUserDiscountCountDao.save(discountCount);
        discountCount.setId(1L);

        UserDiscountCount foundByName = jdbcUserDiscountCountDao.getByName(discountCount.getName());

        assertThat(foundByName, is(discountCount));
    }

    @Test
    void shouldGetByIdPersistedUserDiscountCount() {
        UserDiscountCount discountCount = buildUserDiscountCount();

        jdbcUserDiscountCountDao.save(discountCount);
        discountCount.setId(1L);

        UserDiscountCount foundDiscount = jdbcUserDiscountCountDao.getById(1L);

        assertThat(foundDiscount, is(discountCount));
    }

    @Test
    void remove() {
        UserDiscountCount discountCount = buildUserDiscountCount();

        jdbcUserDiscountCountDao.save(discountCount);
        assertThat(JdbcTestUtils.countRowsInTable(testJdbcTemplate, TABLE_NAME), is(1));

        discountCount.setId(1L);

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
        discountCount.setId(1L);

        boolean updated = jdbcUserDiscountCountDao.update(discountCount);

        assertThat(updated, is(true));
        assertThat(jdbcUserDiscountCountDao.getById(1L), is(discountCount));
        assertThat(JdbcTestUtils.countRowsInTable(testJdbcTemplate, TABLE_NAME), is(1));

    }

    @Test
    void getAll() {
        UserDiscountCount discountCount = buildUserDiscountCount();
        jdbcUserDiscountCountDao.save(discountCount);
        discountCount.setId(1L);

        Collection<UserDiscountCount> persistedDiscounts = jdbcUserDiscountCountDao.getAll();

        assertThat(JdbcTestUtils.countRowsInTable(testJdbcTemplate, TABLE_NAME), is(1));
        assertThat(persistedDiscounts, hasItems(discountCount));
        assertThat(persistedDiscounts, hasSize(1));
    }

    @Test
    void shouldReturnNullWhenGetByEmail() {
        UserDiscountCount discountCount = buildUserDiscountCount();

        assertThat(JdbcTestUtils.countRowsInTable(testJdbcTemplate, TABLE_NAME), is(0));
        UserDiscountCount foundByName = jdbcUserDiscountCountDao.getByName(discountCount.getName());

        assertThat(foundByName, is(nullValue()));
    }

    @Test
    void shouldReturnNullWhenGetById() {
        assertThat(JdbcTestUtils.countRowsInTable(testJdbcTemplate, TABLE_NAME), is(0));

        UserDiscountCount foundById = jdbcUserDiscountCountDao.getById(1L);

        assertThat(foundById, is(nullValue()));
    }
}