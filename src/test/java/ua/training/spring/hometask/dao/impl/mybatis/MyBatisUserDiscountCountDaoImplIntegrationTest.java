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
import ua.training.spring.hometask.domain.UserDiscountCount;
import ua.training.spring.hometask.testconfig.TestsSessionFactoryBeans;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static ua.training.spring.hometask.utills.BuildTestEntityUtill.buildUserDiscountCount;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BeansConfiguration.class, TestsSessionFactoryBeans.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class MyBatisUserDiscountCountDaoImplIntegrationTest {

    @Autowired
    private MyBatisUserDiscountCountDaoImpl myBatisUserDiscountCountDao;

    @Autowired
    @Qualifier("testSqlSessionFactory")
    private SqlSessionFactory sessionFactory;

    @BeforeEach
    void setUp() {
        myBatisUserDiscountCountDao.setSqlSessionFactory(sessionFactory);
    }

    @Test
    void getByName() {
        UserDiscountCount discountCount = buildUserDiscountCount();
        myBatisUserDiscountCountDao.save(discountCount);
        UserDiscountCount foundByName = myBatisUserDiscountCountDao.getByName(discountCount.getName());

        assertThat(foundByName, is(discountCount));
    }

    @Test
    void shouldGetByIdPersistedUserDiscountCount() {
        UserDiscountCount discountCount = buildUserDiscountCount();

        myBatisUserDiscountCountDao.save(discountCount);
        UserDiscountCount foundDiscount = myBatisUserDiscountCountDao.getById(1L);

        assertThat(foundDiscount, is(discountCount));
    }

    @Test
    void remove() {
        UserDiscountCount discountCount = buildUserDiscountCount();

        myBatisUserDiscountCountDao.save(discountCount);
        assertThat(myBatisUserDiscountCountDao.getAll(), hasSize(1));

        myBatisUserDiscountCountDao.remove(discountCount);
        assertThat(myBatisUserDiscountCountDao.getAll(), is(empty()));
    }

    @Test
    void update() {
        UserDiscountCount discountCount = buildUserDiscountCount();

        myBatisUserDiscountCountDao.save(discountCount);

        discountCount.setName("updated");
        discountCount.setCountTenthTicketDiscount(600);
        discountCount.setCountBirthdayDiscount(600);

        boolean updated = myBatisUserDiscountCountDao.update(discountCount);

        assertThat(updated, is(true));
        assertThat(myBatisUserDiscountCountDao.getById(1L), is(discountCount));
    }

    @Test
    void getAll() {
        UserDiscountCount userDiscountCount = buildUserDiscountCount();
        myBatisUserDiscountCountDao.save(userDiscountCount);

        Collection<UserDiscountCount> persistedDiscounts = myBatisUserDiscountCountDao.getAll();

        assertThat(persistedDiscounts, hasItems(userDiscountCount));
        assertThat(persistedDiscounts, hasSize(1));
    }

    @Test
    void shouldReturnNullWhenGetByEmail() {
        UserDiscountCount discountCount = buildUserDiscountCount();

        UserDiscountCount foundByName = myBatisUserDiscountCountDao.getByName(discountCount.getName());

        assertThat(foundByName, is(nullValue()));
    }

    @Test
    void shouldReturnNullWhenGetById() {
        UserDiscountCount foundById = myBatisUserDiscountCountDao.getById(1L);

        assertThat(foundById, is(nullValue()));
    }

}