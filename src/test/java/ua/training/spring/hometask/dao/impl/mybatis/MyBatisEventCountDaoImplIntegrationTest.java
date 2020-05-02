package ua.training.spring.hometask.dao.impl.mybatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
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
@ActiveProfiles("MYBATIS")
class MyBatisEventCountDaoImplIntegrationTest {

    @Autowired
    private MyBatisEventCountDaoImpl myBatisEventCountDao;

    @Autowired
    @Qualifier("testSqlSessionFactory")
    private SqlSessionFactory sessionFactory;

    @BeforeEach
    void setUp() {
        myBatisEventCountDao.setSqlSessionFactory(sessionFactory);
    }

    @Test
    void getByName() {
        EventCount eventCount = buildTestEventCount();
        myBatisEventCountDao.save(eventCount);
        EventCount foundByName = myBatisEventCountDao.getByName(eventCount.getEventName());

        assertThat(foundByName, is(eventCount));
    }

    @Test
    void update() {
        EventCount eventCount = buildTestEventCount();

        myBatisEventCountDao.save(eventCount);

        eventCount.setEventName("updated");
        eventCount.setCountGetByName(600);
        eventCount.setCountGetPrice(600);
        eventCount.setCountBookTickets(600);

        boolean updated = myBatisEventCountDao.update(eventCount);

        assertThat(updated, is(true));
        assertThat(myBatisEventCountDao.getById(1L), is(eventCount));
    }

    @Test
    void shouldGetByIdPersistedEventCount() {
        EventCount eventCount = buildTestEventCount();

        myBatisEventCountDao.save(eventCount);
        EventCount foundEvent = myBatisEventCountDao.getById(1L);

        assertThat(foundEvent, is(eventCount));
    }

    @Test
    void remove() {
        EventCount eventCount = buildTestEventCount();

        myBatisEventCountDao.save(eventCount);
        assertThat(myBatisEventCountDao.getAll(), hasSize(1));

        myBatisEventCountDao.remove(eventCount);
        assertThat(myBatisEventCountDao.getAll(), is(empty()));
    }

    @Test
    void getAll() {
        EventCount testEventCount = buildTestEventCount();
        myBatisEventCountDao.save(testEventCount);

        Collection<EventCount> eventCounts = myBatisEventCountDao.getAll();

        assertThat(eventCounts, hasItems(testEventCount));
        assertThat(eventCounts, hasSize(1));
    }
}