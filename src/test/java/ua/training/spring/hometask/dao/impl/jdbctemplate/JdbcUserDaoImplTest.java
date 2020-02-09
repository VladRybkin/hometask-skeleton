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
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.testconfig.TestJdbcTemplateBeans;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BeansConfiguration.class, TestJdbcTemplateBeans.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class JdbcUserDaoImplTest {

    private static final String TABLE_NAME = "users";

    @Autowired
    private JdbcUserDaoImpl jdbcUserDao;

    @Autowired
    @Qualifier("testJdbcTemplate")
    private JdbcTemplate testJdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcUserDao.setJdbcTemplate(testJdbcTemplate);
    }

    @Test
    void getUserByEmail() {
        User user = buildTestUser();
        jdbcUserDao.save(user);
        User foundByEmail = jdbcUserDao.getUserByEmail(user.getEmail());

        assertThat(foundByEmail, is(user));
    }


    @Test
    void save() {
        User user = buildTestUser();
        User persistedUser = jdbcUserDao.save(user);

        assertThat(JdbcTestUtils.countRowsInTable(testJdbcTemplate, TABLE_NAME), is(1));
        assertThat(persistedUser, is(user));

    }


    @Test
    void remove() {
        User user = buildTestUser();

        jdbcUserDao.save(user);
        jdbcUserDao.remove(user);

        assertThat(JdbcTestUtils.countRowsInTable(testJdbcTemplate, TABLE_NAME), is(0));

    }

    @Test
    void getById() {
        User user = buildTestUser();
        jdbcUserDao.save(user);

        assertThat(jdbcUserDao.getById(1L), is(user));
    }

    @Test
    void getAll() {
        User user = buildTestUser();
        jdbcUserDao.save(user);

        assertThat(jdbcUserDao.getAll(), hasItems(user));
        assertThat(jdbcUserDao.getAll(), hasSize(1));

    }

    private User buildTestUser() {
        User user = new User();
        user.setId(1L);
        user.setEmail("testEmail");
        user.setFirstName("TestUser");
        user.setLastName("testLastName");

        return user;
    }

}