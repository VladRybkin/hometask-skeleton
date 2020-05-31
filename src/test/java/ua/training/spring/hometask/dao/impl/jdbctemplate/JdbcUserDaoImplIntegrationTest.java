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
import ua.training.spring.hometask.domain.User;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static ua.training.spring.hometask.utills.BuildTestEntityUtill.buildTestUser;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BeansConfiguration.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles({"JDBC_TEMPLATE", "TEST"})
class JdbcUserDaoImplIntegrationTest {

    private static final String TABLE_NAME = "users";

    @Autowired
    private JdbcUserDaoImpl jdbcUserDao;

    @Autowired
    private JdbcTemplate testJdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcUserDao.setJdbcTemplate(testJdbcTemplate);
    }

    @Test
    void getUserByEmail() {
        User user = buildTestUser();
        jdbcUserDao.save(user);
        user.setId(1L);

        User foundByEmail = jdbcUserDao.getUserByEmail(user.getEmail());

        assertThat(foundByEmail, is(user));
    }

    @Test
    void shouldGetByIdPersistedUser() {
        User user = buildTestUser();
        jdbcUserDao.save(user);
        user.setId(1L);

        User foundUser = jdbcUserDao.getById(1L);

        assertThat(foundUser, is(user));

    }

    @Test
    void remove() {
        User user = buildTestUser();

        jdbcUserDao.save(user);
        assertThat(JdbcTestUtils.countRowsInTable(testJdbcTemplate, TABLE_NAME), is(1));

        user.setId(1L);

        jdbcUserDao.remove(user);
        assertThat(JdbcTestUtils.countRowsInTable(testJdbcTemplate, TABLE_NAME), is(0));
    }

    @Test
    void getAll() {
        User user = buildTestUser();
        jdbcUserDao.save(user);
        user.setId(1L);

        Collection<User> persistedUsers = jdbcUserDao.getAll();

        assertThat(JdbcTestUtils.countRowsInTable(testJdbcTemplate, TABLE_NAME), is(1));
        assertThat(persistedUsers, hasItems(user));
        assertThat(persistedUsers, hasSize(1));
    }

    @Test
    void shouldReturnNullWhenGetByEmail() {
        User user = buildTestUser();

        assertThat(JdbcTestUtils.countRowsInTable(testJdbcTemplate, TABLE_NAME), is(0));
        User foundByEmail = jdbcUserDao.getUserByEmail(user.getEmail());

        assertThat(foundByEmail, is(nullValue()));
    }

    @Test
    void shouldReturnNullWhenGetById() {
        assertThat(JdbcTestUtils.countRowsInTable(testJdbcTemplate, TABLE_NAME), is(0));

        User foundById = jdbcUserDao.getById(1L);

        assertThat(foundById, is(nullValue()));
    }
}