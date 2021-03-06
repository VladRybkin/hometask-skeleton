package ua.training.spring.hometask.dao.impl.hibernate;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ua.training.spring.hometask.config.BeansConfiguration;
import ua.training.spring.hometask.domain.EventCount;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ua.training.spring.hometask.utills.BuildTestEntityUtill.buildTestEventCount;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BeansConfiguration.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles({"HIBERNATE", "TEST"})
class HibernateEventCountDaoImplIntegrationTest {

    @Autowired
    private HibernateEventCountDaoImpl hibernateEventCountDao;

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    void getByName() {
        EventCount eventCount = buildTestEventCount();
        hibernateEventCountDao.save(eventCount);
        EventCount foundByName = hibernateEventCountDao.getByName(eventCount.getEventName());

        assertThat(foundByName, is(eventCount));
    }

    @Test
    void update() {
        EventCount eventCount = buildTestEventCount();

        hibernateEventCountDao.save(eventCount);

        eventCount.setEventName("updated");
        eventCount.setCountGetByName(600);
        eventCount.setCountGetPrice(600);
        eventCount.setCountBookTickets(600);

        boolean updated = hibernateEventCountDao.update(eventCount);

        assertThat(updated, is(true));
        assertThat(hibernateEventCountDao.getById(1L), is(eventCount));
    }

    @Test
    void shouldGetByIdPersistedEventCount() {
        EventCount eventCount = buildTestEventCount();

        hibernateEventCountDao.save(eventCount);
        EventCount foundEvent = hibernateEventCountDao.getById(1L);

        assertThat(foundEvent, is(eventCount));
    }

    @Test
    void remove() {
        EventCount eventCount = buildTestEventCount();

        hibernateEventCountDao.save(eventCount);
        assertThat(hibernateEventCountDao.getAll(), hasSize(1));

        hibernateEventCountDao.remove(eventCount);
        assertThat(hibernateEventCountDao.getAll(), is(empty()));
    }

    @Test
    void getAll() {
        EventCount testEventCount = buildTestEventCount();
        hibernateEventCountDao.save(testEventCount);

        Collection<EventCount> eventCounts = hibernateEventCountDao.getAll();

        assertThat(eventCounts, hasItems(testEventCount));
        assertThat(eventCounts, hasSize(1));
    }

    @Test
    void shouldThrowNotNullConstraintException() {
        EventCount EventCountWithNullEventName = new EventCount();

        assertThrows(ConstraintViolationException.class, () -> {
            hibernateEventCountDao.save(EventCountWithNullEventName);
        });
    }

    @Test
    void shouldThrowUniqueConstraintException() {
        EventCount eventCount = buildTestEventCount();
        EventCount eventCountWithDuplicatedEventName = buildTestEventCount();
        hibernateEventCountDao.save(eventCount);
        assertThrows(PersistenceException.class, () -> {
            hibernateEventCountDao.save(eventCountWithDuplicatedEventName);
        });
    }

    @Test
    void shouldCashingGetByName() {
        EventCount eventCount = buildTestEventCount();
        hibernateEventCountDao.save(eventCount);

        hibernateEventCountDao.getByName(eventCount.getEventName());
        assertThat(sessionFactory.getStatistics().getSecondLevelCachePutCount(), is(1L));
        assertThat(sessionFactory.getStatistics().getSecondLevelCacheHitCount(), is(0L));

        hibernateEventCountDao.getByName(eventCount.getEventName());
        assertThat(sessionFactory.getStatistics().getSecondLevelCachePutCount(), is(1L));
        assertThat(sessionFactory.getStatistics().getSecondLevelCacheHitCount(), is(1L));
    }

    @Test
    void shouldCashingGetById() {
        EventCount eventCount = buildTestEventCount();
        hibernateEventCountDao.save(eventCount);

        hibernateEventCountDao.getById(1L);
        assertThat(sessionFactory.getStatistics().getSecondLevelCachePutCount(), is(1L));
        assertThat(sessionFactory.getStatistics().getSecondLevelCacheHitCount(), is(0L));

        hibernateEventCountDao.getById(1L);
        assertThat(sessionFactory.getStatistics().getSecondLevelCachePutCount(), is(1L));
        assertThat(sessionFactory.getStatistics().getSecondLevelCacheHitCount(), is(1L));
    }

    @Test
    void shouldCashingGetAll() {
        EventCount testEventCount1 = buildTestEventCount();
        EventCount testEventCount2 = buildTestEventCount();
        testEventCount2.setEventName("testEventName2");

        hibernateEventCountDao.save(testEventCount1);
        hibernateEventCountDao.save(testEventCount2);

        hibernateEventCountDao.getAll();
        assertThat(sessionFactory.getStatistics().getSecondLevelCachePutCount(), is(2L));
        assertThat(sessionFactory.getStatistics().getSecondLevelCacheHitCount(), is(0L));

        hibernateEventCountDao.getAll();
        assertThat(sessionFactory.getStatistics().getSecondLevelCachePutCount(), is(2L));
        assertThat(sessionFactory.getStatistics().getSecondLevelCacheHitCount(), is(2L));
    }

    @Test
    void shouldRemoveFromCache() {
        EventCount eventCount = buildTestEventCount();
        hibernateEventCountDao.save(eventCount);
        hibernateEventCountDao.getByName(eventCount.getEventName());

        assertThat(sessionFactory.getStatistics().getSecondLevelCachePutCount(), is(1L));
        hibernateEventCountDao.remove(eventCount);
        assertThat(hibernateEventCountDao.getByName(eventCount.getEventName()), is(nullValue()));
    }
}