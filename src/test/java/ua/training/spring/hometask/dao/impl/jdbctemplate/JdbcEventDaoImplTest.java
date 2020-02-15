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
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.EventRating;
import ua.training.spring.hometask.testconfig.TestJdbcTemplateBeans;

import java.time.LocalDateTime;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BeansConfiguration.class, TestJdbcTemplateBeans.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class JdbcEventDaoImplTest {

    private static final String TABLE_NAME = "events";

    @Autowired
    private JdbcEventDaoImpl jdbcEventDao;

    @Autowired
    @Qualifier("testJdbcTemplate")
    private JdbcTemplate testJdbcTemplate;


    @BeforeEach
    void setUp() {
        jdbcEventDao.setJdbcTemplate(testJdbcTemplate);
    }

    @Test
    void getByName() {
        Event event = buildTestEvent();
        jdbcEventDao.save(event);
        Event foundByName = jdbcEventDao.getByName(event.getName());

        assertThat(foundByName, is(event));
    }

    @Test
    void shouldGetByIdPersistedEvent() {
        Event event = buildTestEvent();
        jdbcEventDao.save(event);
        Event foundById = jdbcEventDao.getById(1L);

        assertThat(JdbcTestUtils.countRowsInTable(testJdbcTemplate, TABLE_NAME), is(1));
        assertThat(foundById, is(event));
    }

    @Test
    void remove() {
        Event event = buildTestEvent();
        event.addAirDateTime(LocalDateTime.now().plusDays(5));

        jdbcEventDao.save(event);
        jdbcEventDao.remove(event);

//        assertThat(JdbcTestUtils.countRowsInTable(testJdbcTemplate, TABLE_NAME), is(0));
    }

    @Test
    void getAll() {
        Event event = buildTestEvent();
        jdbcEventDao.save(event);

        Collection<Event>persistedEvents=jdbcEventDao.getAll();

        assertThat(persistedEvents, hasItems(event));
        assertThat(persistedEvents, hasSize(1));
    }

    @Test
    void getForDateRange() {
        Event event=buildTestEvent();
        event.addAirDateTime(LocalDateTime.now().minusDays(3));

        jdbcEventDao.save(event);

        Collection<Event>persistedEvents=jdbcEventDao
                .getForDateRange(LocalDateTime.now().minusDays(6), LocalDateTime.now());

        assertThat(persistedEvents, hasSize(1));
//        assertThat(persistedEvents, hasItems(event));

    }

    @Test
    void getNextEvents() {
        Event event=buildTestEvent();
        event.addAirDateTime(LocalDateTime.now().minusDays(3));

        jdbcEventDao.save(event);

        Collection<Event>persistedEvents=jdbcEventDao
                .getNextEvents(LocalDateTime.now());

        assertThat(persistedEvents, hasSize(1));

    }

    private Event buildTestEvent() {
        Event event = new Event();
        event.setId(1L);
        event.setName("testEvent");
        event.setRating(EventRating.HIGH);
        event.setBasePrice(100);

        return event;
    }
}