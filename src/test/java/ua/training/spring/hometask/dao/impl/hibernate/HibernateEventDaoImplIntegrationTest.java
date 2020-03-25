package ua.training.spring.hometask.dao.impl.hibernate;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ua.training.spring.hometask.config.BeansConfiguration;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.testconfig.TestsSessionFactoryBeans;

import javax.persistence.PersistenceException;
import java.time.LocalDateTime;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ua.training.spring.hometask.utills.BuildTestEntityUtill.buildTestEvent;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BeansConfiguration.class, TestsSessionFactoryBeans.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class HibernateEventDaoImplIntegrationTest {

    @Autowired
    private HibernateEventDaoImpl hibernateEventDao;

    @Autowired
    @Qualifier("testSessionFactory")
    private SessionFactory sessionFactory;

    @BeforeEach
    void setUp() {
        hibernateEventDao.setSessionFactory(sessionFactory);
    }

    @Test
    void getByName() {
        Event event = buildTestEvent();

        hibernateEventDao.save(event);
        Event foundByName = hibernateEventDao.getByName(event.getName());

        assertThat(foundByName, is(event));
    }

    @Test
    void shouldGetByIdPersistedEvent() {
        Event event = buildTestEvent();

        hibernateEventDao.save(event);
        Event foundById = hibernateEventDao.getById(1L);

        assertThat(foundById, is(event));
    }

    @Test
    void remove() {
        Event event = buildTestEvent();
        event.addAirDateTime(LocalDateTime.now().plusDays(5));

        hibernateEventDao.save(event);
        assertThat(hibernateEventDao.getAll(), hasSize(1));

        hibernateEventDao.remove(event);
        assertThat(hibernateEventDao.getAll(), is(empty()));
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

        hibernateEventDao.save(testEvent1);
        hibernateEventDao.save(testEvent2);

        Collection<Event> persistedEvents = hibernateEventDao.getAll();

        assertThat(persistedEvents, hasItems(testEvent1, testEvent2));
        assertThat(persistedEvents, hasSize(2));
    }

    @Test
    void shouldReturnEventsThatMatchDateRange() {
        Event event = buildTestEvent();
        event.addAirDateTime(LocalDateTime.now().minusDays(3));

        hibernateEventDao.save(event);

        Collection<Event> persistedEvents = hibernateEventDao
                .getForDateRange(LocalDateTime.now().minusDays(6), LocalDateTime.now());

        assertThat(persistedEvents, hasSize(1));
        assertThat(persistedEvents, hasItems(event));
    }

    @Test
    void shouldReturnEmptyDateRangeEvents() {
        Event event = buildTestEvent();
        event.addAirDateTime(LocalDateTime.now().minusDays(6));

        hibernateEventDao.save(event);

        Collection<Event> persistedEvents = hibernateEventDao
                .getForDateRange(LocalDateTime.now().minusDays(3), LocalDateTime.now());

        assertThat(persistedEvents, empty());
    }

    @Test
    void getNextEvents() {
        Event event = buildTestEvent();
        event.addAirDateTime(LocalDateTime.now().minusDays(3));

        hibernateEventDao.save(event);

        Collection<Event> persistedEvents = hibernateEventDao
                .getNextEvents(LocalDateTime.now());

        assertThat(persistedEvents, hasSize(1));
    }

    @Test
    void shouldReturnEmptyNextEvents() {
        Event event = buildTestEvent();
        event.addAirDateTime(LocalDateTime.now().minusDays(3));

        hibernateEventDao.save(event);

        Collection<Event> persistedEvents = hibernateEventDao
                .getNextEvents(LocalDateTime.now().minusDays(5));

        assertThat(persistedEvents, empty());
    }

    @Test
    void shouldReturnNullWhenGetByName() {
        Event event = buildTestEvent();

        Event foundByName = hibernateEventDao.getByName(event.getName());

        assertThat(foundByName, is(nullValue()));
    }

    @Test
    void shouldReturnNullWhenGetById() {
        Event foundById = hibernateEventDao.getById(1L);

        assertThat(foundById, is(nullValue()));
    }

    @Test
    void shouldThrowUniqueConstraintException() {
        Event event = buildTestEvent();
        Event eventWithDuplicatedEventName = buildTestEvent();
        hibernateEventDao.save(event);

        assertThrows(PersistenceException.class, () -> {
            hibernateEventDao.save(eventWithDuplicatedEventName);
        });
    }
}