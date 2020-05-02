package ua.training.spring.hometask.dao.impl.mybatis;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
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
@ActiveProfiles({"MYBATIS", "TEST"})
class MyBatisEventDaoImplIntegrationTest {

    @Autowired
    private MyBatisEventDaoImpl mybatisEventDao;

    @Test
    void getByName() {
        Event event = buildTestEvent();

        mybatisEventDao.save(event);
        Event foundByName = mybatisEventDao.getByName(event.getName());

        assertThat(foundByName, is(event));
    }

    @Test
    void shouldGetByIdPersistedEvent() {
        Event event = buildTestEvent();

        mybatisEventDao.save(event);
        Event foundById = mybatisEventDao.getById(1L);

        assertThat(foundById, is(event));
    }

    @Test
    void remove() {
        Event event = buildTestEvent();
        event.addAirDateTime(LocalDateTime.now().plusDays(5));

        mybatisEventDao.save(event);
        assertThat(mybatisEventDao.getAll(), hasSize(1));

        mybatisEventDao.remove(event);
        assertThat(mybatisEventDao.getAll(), is(empty()));
    }

    @Test
    void getAll() {
        Event testEvent1 = buildTestEvent();
        testEvent1.getAirDates().add(LocalDateTime.now().minusDays(3));
        testEvent1.getAirDates().add(LocalDateTime.now().plusYears(10));

        Event testEvent2 = buildTestEvent();
        testEvent2.setName("testEvent2");
        testEvent2.getAirDates().add(LocalDateTime.now().minusDays(6));

        testEvent2.getAirDates().add(LocalDateTime.now().minusDays(300));

        mybatisEventDao.save(testEvent1);
        mybatisEventDao.save(testEvent2);

        Collection<Event> persistedEvents = mybatisEventDao.getAll();

        assertThat(persistedEvents, hasItems(testEvent1, testEvent2));
        assertThat(persistedEvents, hasSize(2));
    }

    @Test
    void shouldReturnEventsThatMatchDateRange() {
        Event event = buildTestEvent();
        event.addAirDateTime(LocalDateTime.now().minusDays(3));

        mybatisEventDao.save(event);

        Collection<Event> persistedEvents = mybatisEventDao
                .getForDateRange(LocalDateTime.now().minusDays(6), LocalDateTime.now());

        assertThat(persistedEvents, hasSize(1));
        assertThat(persistedEvents, hasItems(event));
    }

    @Test
    void shouldReturnEmptyDateRangeEvents() {
        Event event = buildTestEvent();
        event.addAirDateTime(LocalDateTime.now().minusDays(6));

        mybatisEventDao.save(event);

        Collection<Event> persistedEvents = mybatisEventDao
                .getForDateRange(LocalDateTime.now().minusDays(3), LocalDateTime.now());

        assertThat(persistedEvents, empty());
    }

    @Test
    void getNextEvents() {
        Event event = buildTestEvent();
        event.addAirDateTime(LocalDateTime.now().minusDays(3));

        mybatisEventDao.save(event);

        Collection<Event> persistedEvents = mybatisEventDao
                .getNextEvents(LocalDateTime.now());

        assertThat(persistedEvents, hasSize(1));
    }

    @Test
    void shouldReturnEmptyNextEvents() {
        Event event = buildTestEvent();
        event.addAirDateTime(LocalDateTime.now().minusDays(3));

        mybatisEventDao.save(event);

        Collection<Event> persistedEvents = mybatisEventDao
                .getNextEvents(LocalDateTime.now().minusDays(5));

        assertThat(persistedEvents, empty());
    }

    @Test
    void shouldReturnNullWhenGetByName() {
        Event event = buildTestEvent();

        Event foundByName = mybatisEventDao.getByName(event.getName());

        assertThat(foundByName, is(nullValue()));
    }

    @Test
    void shouldReturnNullWhenGetById() {
        Event foundById = mybatisEventDao.getById(1L);

        assertThat(foundById, is(nullValue()));
    }
}