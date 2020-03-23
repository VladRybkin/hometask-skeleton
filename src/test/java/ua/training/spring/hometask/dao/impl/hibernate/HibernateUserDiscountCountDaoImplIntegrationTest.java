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
import ua.training.spring.hometask.domain.UserDiscountCount;
import ua.training.spring.hometask.testconfig.TestsSessionFactoryBeans;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static ua.training.spring.hometask.utills.BuildTestEntityUtill.buildUserDiscountCount;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BeansConfiguration.class, TestsSessionFactoryBeans.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class HibernateUserDiscountCountDaoImplIntegrationTest {

    @Autowired
    private HibernateUserDiscountCountDaoImpl hibernateUserDiscountCountDao;

    @Autowired
    @Qualifier("testSessionFactory")
    private SessionFactory sessionFactory;

    @BeforeEach
    void setUp() {
        hibernateUserDiscountCountDao.setSessionFactory(sessionFactory);
    }

    @Test
    void getByName() {
        UserDiscountCount discountCount = buildUserDiscountCount();
        hibernateUserDiscountCountDao.save(discountCount);
        UserDiscountCount foundByName = hibernateUserDiscountCountDao.getByName(discountCount.getName());

        assertThat(foundByName, is(discountCount));
    }

    @Test
    void shouldGetByIdPersistedUserDiscountCount() {
        UserDiscountCount discountCount = buildUserDiscountCount();

        hibernateUserDiscountCountDao.save(discountCount);
        UserDiscountCount foundDiscount = hibernateUserDiscountCountDao.getById(1L);

        assertThat(foundDiscount, is(discountCount));
    }

    @Test
    void remove() {
        UserDiscountCount discountCount = buildUserDiscountCount();
        hibernateUserDiscountCountDao.save(discountCount);
        hibernateUserDiscountCountDao.remove(discountCount);
    }

    @Test
    void update() {
        UserDiscountCount discountCount = buildUserDiscountCount();

        hibernateUserDiscountCountDao.save(discountCount);

        discountCount.setName("updated");
        discountCount.setCountTenthTicketDiscount(600);
        discountCount.setCountBirthdayDiscount(600);

        boolean updated = hibernateUserDiscountCountDao.update(discountCount);

        assertThat(updated, is(true));
        assertThat(hibernateUserDiscountCountDao.getById(1L), is(discountCount));
    }

    @Test
    void getAll() {
        UserDiscountCount user = buildUserDiscountCount();
        hibernateUserDiscountCountDao.save(user);

        Collection<UserDiscountCount> persistedDiscounts = hibernateUserDiscountCountDao.getAll();

        assertThat(persistedDiscounts, hasItems(user));
        assertThat(persistedDiscounts, hasSize(1));
    }

    @Test
    void shouldReturnNullWhenGetByEmail() {
        UserDiscountCount discountCount = buildUserDiscountCount();

        UserDiscountCount foundByName = hibernateUserDiscountCountDao.getByName(discountCount.getName());

        assertThat(foundByName, is(nullValue()));
    }

    @Test
    void shouldReturnNullWhenGetById() {
        UserDiscountCount foundById = hibernateUserDiscountCountDao.getById(1L);

        assertThat(foundById, is(nullValue()));
    }
}