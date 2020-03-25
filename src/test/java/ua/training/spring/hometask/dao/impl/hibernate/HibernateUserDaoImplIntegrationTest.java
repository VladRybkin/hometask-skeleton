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
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.testconfig.TestsSessionFactoryBeans;

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
@ContextConfiguration(classes = {BeansConfiguration.class, TestsSessionFactoryBeans.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class HibernateUserDaoImplIntegrationTest {

    @Autowired
    private HibernateUserDaoImpl hibernateUserDao;

    @Autowired
    @Qualifier("testSessionFactory")
    private SessionFactory sessionFactory;

    @BeforeEach
    void setUp() {
        hibernateUserDao.setSessionFactory(sessionFactory);
    }

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
}