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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

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
    }

    @Test
    void update() {
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
    }

    @Test
    void getById() {
    }

    @Test
    void getAll() {
        System.out.println(hibernateEventCountDao.getAll());
    }

    private EventCount buildTestEventCount() {
        EventCount eventCount = new EventCount();
        eventCount.setEventName("test event name");
        eventCount.setCountBookTickets(0);
        eventCount.setCountGetPrice(0);
        eventCount.setCountGetByName(0);

        return eventCount;
    }
}