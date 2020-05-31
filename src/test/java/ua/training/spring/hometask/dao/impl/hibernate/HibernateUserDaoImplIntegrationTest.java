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
import ua.training.spring.hometask.domain.User;

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
import static ua.training.spring.hometask.utills.BuildTestEntityUtill.buildTestUser;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BeansConfiguration.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles({"HIBERNATE", "TEST"})
class HibernateUserDaoImplIntegrationTest {

    @Autowired
    private HibernateUserDaoImpl hibernateUserDao;

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    void getUserByEmail() {
        User user = buildTestUser();
        hibernateUserDao.save(user);
        User foundByEmail = hibernateUserDao.getUserByEmail(user.getEmail());

        assertThat(foundByEmail, is(user));
    }

    @Test
    void shouldGetByIdPersistedUser() {
        User user = buildTestUser();
        hibernateUserDao.save(user);
        User foundUser = hibernateUserDao.getById(1L);

        assertThat(foundUser, is(user));
    }

    @Test
    void remove() {
        User user = buildTestUser();

        hibernateUserDao.save(user);
        assertThat(hibernateUserDao.getAll(), hasSize(1));

        hibernateUserDao.remove(user);
        assertThat(hibernateUserDao.getAll(), is(empty()));
    }

    @Test
    void getAll() {
        User user = buildTestUser();
        hibernateUserDao.save(user);

        Collection<User> persistedUsers = hibernateUserDao.getAll();

        assertThat(persistedUsers, hasItems(user));
        assertThat(persistedUsers, hasSize(1));
    }

    @Test
    void shouldReturnNullWhenGetByEmail() {
        User user = buildTestUser();

        User foundByEmail = hibernateUserDao.getUserByEmail(user.getEmail());

        assertThat(foundByEmail, is(nullValue()));
    }

    @Test
    void shouldReturnNullWhenGetById() {
        User foundById = hibernateUserDao.getById(1L);

        assertThat(foundById, is(nullValue()));
    }

    @Test
    void shouldThrowNotNullConstraintException() {
        User userWithNullEventName = new User();

        assertThrows(ConstraintViolationException.class, () -> {
            hibernateUserDao.save(userWithNullEventName);
        });
    }

    @Test
    void shouldThrowUniqueConstraintException() {
        User user = buildTestUser();
        User userWithDuplicatedEventName = buildTestUser();
        hibernateUserDao.save(user);
        assertThrows(PersistenceException.class, () -> {
            hibernateUserDao.save(userWithDuplicatedEventName);
        });
    }

    @Test
    void shouldCashingGetByEmail() {
        User user = buildTestUser();
        hibernateUserDao.save(user);

        hibernateUserDao.getUserByEmail(user.getEmail());
        assertThat(sessionFactory.getStatistics().getSecondLevelCachePutCount(), is(1L));
        assertThat(sessionFactory.getStatistics().getSecondLevelCacheHitCount(), is(0L));

        hibernateUserDao.getUserByEmail(user.getEmail());
        assertThat(sessionFactory.getStatistics().getSecondLevelCachePutCount(), is(1L));
        assertThat(sessionFactory.getStatistics().getSecondLevelCacheHitCount(), is(1L));
    }

    @Test
    void shouldCashingGetById() {
        User user = buildTestUser();
        hibernateUserDao.save(user);

        hibernateUserDao.getById(1L);
        assertThat(sessionFactory.getStatistics().getSecondLevelCachePutCount(), is(1L));
        assertThat(sessionFactory.getStatistics().getSecondLevelCacheHitCount(), is(0L));

        hibernateUserDao.getById(1L);
        assertThat(sessionFactory.getStatistics().getSecondLevelCachePutCount(), is(1L));
        assertThat(sessionFactory.getStatistics().getSecondLevelCacheHitCount(), is(1L));
    }

    @Test
    void shouldCashingGetAll() {
        User user1 = buildTestUser();
        User user2 = buildTestUser();
        user2.setEmail("test@mail2.com");

        hibernateUserDao.save(user1);
        hibernateUserDao.save(user2);

        hibernateUserDao.getAll();
        assertThat(sessionFactory.getStatistics().getSecondLevelCachePutCount(), is(2L));
        assertThat(sessionFactory.getStatistics().getSecondLevelCacheHitCount(), is(0L));

        hibernateUserDao.getAll();
        assertThat(sessionFactory.getStatistics().getSecondLevelCachePutCount(), is(2L));
        assertThat(sessionFactory.getStatistics().getSecondLevelCacheHitCount(), is(2L));
    }

    @Test
    void shouldRemoveFromCache() {
        User user = buildTestUser();
        hibernateUserDao.save(user);
        hibernateUserDao.getById(user.getId());

        assertThat(sessionFactory.getStatistics().getSecondLevelCachePutCount(), is(1L));
        hibernateUserDao.remove(user);
        assertThat(hibernateUserDao.getById(user.getId()), is(nullValue()));
    }
}