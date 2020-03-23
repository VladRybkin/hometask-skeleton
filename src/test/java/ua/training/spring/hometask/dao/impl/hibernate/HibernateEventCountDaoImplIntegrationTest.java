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
import ua.training.spring.hometask.domain.EventCount;
import ua.training.spring.hometask.testconfig.TestsSessionFactoryBeans;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static ua.training.spring.hometask.utills.BuildTestEntityUtill.buildTestEventCount;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BeansConfiguration.class, TestsSessionFactoryBeans.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class HibernateEventCountDaoImplIntegrationTest {

    @Autowired
    private HibernateEventCountDaoImpl hibernateEventCountDao;

    @Autowired
    @Qualifier("testSessionFactory")
    private SessionFactory sessionFactory;

    @BeforeEach
    void setUp() {
        hibernateEventCountDao.setSessionFactory(sessionFactory);
    }

    @Test
    void getByName() {
        EventCount eventCount =buildTestEventCount();
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
}