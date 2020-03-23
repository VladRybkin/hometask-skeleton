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
import ua.training.spring.hometask.domain.EventCount;
import ua.training.spring.hometask.testconfig.TestJdbcTemplateBeans;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static ua.training.spring.hometask.utills.BuildTestEntityUtill.buildTestEventCount;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BeansConfiguration.class, TestJdbcTemplateBeans.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class JdbcEventCountDaoImplIntegrationTest {

    private static final String TABLE_NAME = "event_counts";

    @Autowired
    private JdbcEventCountDaoImpl jdbcEventCountDao;

    @Autowired
    @Qualifier("testJdbcTemplate")
    private JdbcTemplate testJdbcTemplate;


    @BeforeEach
    void setUp() {
        jdbcEventCountDao.setJdbcTemplate(testJdbcTemplate);
    }

    @Test
    void getByName() {
        EventCount eventCount = buildTestEventCount();

        jdbcEventCountDao.save(eventCount);
        eventCount.setId(1L);

        EventCount foundByName = jdbcEventCountDao.getByName(eventCount.getEventName());

        assertThat(foundByName, is(eventCount));
    }

    @Test
    void shouldGetByIdPersistedEventCount() {
        EventCount eventCount = buildTestEventCount();

        jdbcEventCountDao.save(eventCount);
        eventCount.setId(1L);

        EventCount foundEvent = jdbcEventCountDao.getById(1L);

        assertThat(foundEvent, is(eventCount));
    }

    @Test
    void remove() {
        EventCount eventCount = buildTestEventCount();

        jdbcEventCountDao.save(eventCount);
        assertThat(JdbcTestUtils.countRowsInTable(testJdbcTemplate, TABLE_NAME), is(1));

        eventCount.setId(1L);

        jdbcEventCountDao.remove(eventCount);
        assertThat(JdbcTestUtils.countRowsInTable(testJdbcTemplate, TABLE_NAME), is(0));
    }

    @Test
    void update() {
        EventCount eventCount = buildTestEventCount();

        jdbcEventCountDao.save(eventCount);

        eventCount.setEventName("updated");
        eventCount.setCountGetByName(600);
        eventCount.setCountGetPrice(600);
        eventCount.setCountBookTickets(600);
        eventCount.setId(1L);

        boolean updated = jdbcEventCountDao.update(eventCount);

        assertThat(updated, is(true));
        assertThat(jdbcEventCountDao.getById(1L), is(eventCount));
        assertThat(JdbcTestUtils.countRowsInTable(testJdbcTemplate, TABLE_NAME), is(1));
    }

    @Test
    void getAll() {
        EventCount testEventCount = buildTestEventCount();
        jdbcEventCountDao.save(testEventCount);

        Collection<EventCount> eventCounts = jdbcEventCountDao.getAll();
        testEventCount.setId(1L);

        assertThat(JdbcTestUtils.countRowsInTable(testJdbcTemplate, TABLE_NAME), is(1));
        assertThat(eventCounts, hasItems(testEventCount));
        assertThat(eventCounts, hasSize(1));
    }

    @Test
    void shouldReturnNullWhenGetByName() {
        EventCount eventCount = buildTestEventCount();

        assertThat(JdbcTestUtils.countRowsInTable(testJdbcTemplate, TABLE_NAME), is(0));
        EventCount foundByName = jdbcEventCountDao.getByName(eventCount.getEventName());

        assertThat(foundByName, is(nullValue()));
    }

    @Test
    void shouldReturnNullWhenGetById() {
        assertThat(JdbcTestUtils.countRowsInTable(testJdbcTemplate, TABLE_NAME), is(0));

        EventCount foundById = jdbcEventCountDao.getById(1L);

        assertThat(foundById, is(nullValue()));
    }
}