package ua.training.spring.hometask.dao.impl.jdbctemplate;

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
import ua.training.spring.hometask.domain.Event;

import java.time.LocalDateTime;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static ua.training.spring.hometask.utills.BuildTestEntityUtill.buildTestEvent;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BeansConfiguration.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles({"JDBC_TEMPLATE", "TEST"})
class JdbcEventDaoImplIntegrationTest {

    private static final String TABLE_NAME = "events";

    @Autowired
    private JdbcEventDaoImpl jdbcEventDao;

    @Autowired
    private JdbcTemplate testJdbcTemplate;

    @Test
    void getByName() {
        Event event = buildTestEvent();

        jdbcEventDao.save(event);
        event.setId(1L);

        Event foundByName = jdbcEventDao.getByName(event.getName());

        assertThat(foundByName, is(event));
    }

    @Test
    void shouldGetByIdPersistedEvent() {
        Event event = buildTestEvent();

        jdbcEventDao.save(event);
        event.setId(1L);

        Event foundById = jdbcEventDao.getById(1L);

        assertThat(foundById, is(event));
    }

    @Test
    void remove() {
        Event event = buildTestEvent();
        event.addAirDateTime(LocalDateTime.now().plusDays(5));

        jdbcEventDao.save(event);
        event.setId(1L);
        assertThat(JdbcTestUtils.countRowsInTable(testJdbcTemplate, TABLE_NAME), is(1));

        jdbcEventDao.remove(event);
        assertThat(JdbcTestUtils.countRowsInTable(testJdbcTemplate, TABLE_NAME), is(0));
    }

    @Test
    void getAll() {
        Event testEvent1 = buildTestEvent();
        testEvent1.getAirDates().add(LocalDateTime.now().minusDays(3));
        testEvent1.getAirDates().add(LocalDateTime.MAX);

        Event testEvent2 = buildTestEvent();
        testEvent2.setName("testEvent2");
        testEvent2.getAirDates().add(LocalDateTime.now().minusDays(6));
        testEvent2.getAirDates().add(LocalDateTime.MIN);

        jdbcEventDao.save(testEvent1);
        jdbcEventDao.save(testEvent2);
        testEvent1.setId(1L);
        testEvent2.setId(2L);

        Collection<Event> persistedEvents = jdbcEventDao.getAll();

        assertThat(JdbcTestUtils.countRowsInTable(testJdbcTemplate, TABLE_NAME), is(2));
        assertThat(persistedEvents, hasItems(testEvent1, testEvent2));
        assertThat(persistedEvents, hasSize(2));
    }

    @Test
    void shouldReturnEventsThatMatchDateRange() {
        Event event = buildTestEvent();
        event.addAirDateTime(LocalDateTime.now().minusDays(3));

        jdbcEventDao.save(event);
        event.setId(1L);

        Collection<Event> persistedEvents = jdbcEventDao
                .getForDateRange(LocalDateTime.now().minusDays(6), LocalDateTime.now());

        assertThat(persistedEvents, hasSize(1));
        assertThat(persistedEvents, hasItems(event));
    }

    @Test
    void shouldReturnEmptyDateRangeEvents() {
        Event event = buildTestEvent();
        event.addAirDateTime(LocalDateTime.now().minusDays(6));

        jdbcEventDao.save(event);
        event.setId(1L);

        Collection<Event> persistedEvents = jdbcEventDao
                .getForDateRange(LocalDateTime.now().minusDays(3), LocalDateTime.now());

        assertThat(persistedEvents, empty());
    }

    @Test
    void getNextEvents() {
        Event event = buildTestEvent();
        event.addAirDateTime(LocalDateTime.now().minusDays(3));

        jdbcEventDao.save(event);
        event.setId(1L);

        Collection<Event> persistedEvents = jdbcEventDao
                .getNextEvents(LocalDateTime.now());

        assertThat(persistedEvents, hasSize(1));
    }

    @Test
    void shouldReturnEmptyNextEvents() {
        Event event = buildTestEvent();
        event.addAirDateTime(LocalDateTime.now().minusDays(3));

        jdbcEventDao.save(event);
        event.setId(1L);

        Collection<Event> persistedEvents = jdbcEventDao
                .getNextEvents(LocalDateTime.now().minusDays(5));

        assertThat(persistedEvents, empty());
    }

    @Test
    void shouldReturnNullWhenGetByName() {
        Event event = buildTestEvent();

        assertThat(JdbcTestUtils.countRowsInTable(testJdbcTemplate, TABLE_NAME), is(0));
        Event foundByName = jdbcEventDao.getByName(event.getName());

        assertThat(foundByName, is(nullValue()));
    }

    @Test
    void shouldReturnNullWhenGetById() {
        assertThat(JdbcTestUtils.countRowsInTable(testJdbcTemplate, TABLE_NAME), is(0));

        Event foundById = jdbcEventDao.getById(1L);

        assertThat(foundById, is(nullValue()));
    }
}