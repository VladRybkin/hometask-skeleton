package ua.training.spring.hometask.dao.impl.mybatis;

import org.apache.ibatis.session.SqlSessionFactory;
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

import java.util.Collection;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static ua.training.spring.hometask.utills.BuildTestEntityUtill.buildTestUser;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BeansConfiguration.class, TestsSessionFactoryBeans.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class MyBatisUserDaoImplIntegrationTest {

    @Autowired
    private MyBatisUserDaoImpl myBatisUserDao;

    @Autowired
    @Qualifier("testSqlSessionFactory")
    private SqlSessionFactory sessionFactory;

    @BeforeEach
    void setUp() {
        myBatisUserDao.setSqlSessionFactory(sessionFactory);
    }

    @Test
    void getUserByEmail() {
        User user = buildTestUser();
        myBatisUserDao.save(user);
        User foundByEmail = myBatisUserDao.getUserByEmail(user.getEmail());

        assertThat(foundByEmail, is(user));
    }

    @Test
    void shouldGetByIdPersistedUser() {
        User user = buildTestUser();
        myBatisUserDao.save(user);
        User foundUser = myBatisUserDao.getById(1L);

        assertThat(foundUser, is(user));
    }

    @Test
    void remove() {
        User user = buildTestUser();

        myBatisUserDao.save(user);
        assertThat(myBatisUserDao.getAll(), hasSize(1));

        myBatisUserDao.remove(user);
        assertThat(myBatisUserDao.getAll(), is(empty()));
    }

    @Test
    void getAll() {
        User user = buildTestUser();
        myBatisUserDao.save(user);

        Collection<User> persistedUsers = myBatisUserDao.getAll();

        assertThat(persistedUsers, hasItems(user));
        assertThat(persistedUsers, hasSize(1));
    }

    @Test
    void shouldReturnNullWhenGetByEmail() {
        User user = buildTestUser();

        User foundByEmail = myBatisUserDao.getUserByEmail(user.getEmail());

        assertThat(foundByEmail, is(nullValue()));
    }

    @Test
    void shouldReturnNullWhenGetById() {
        User foundById = myBatisUserDao.getById(1L);

        assertThat(foundById, is(nullValue()));
    }
}