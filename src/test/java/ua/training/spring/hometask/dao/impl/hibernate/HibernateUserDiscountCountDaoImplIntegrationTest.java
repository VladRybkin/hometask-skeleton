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
import ua.training.spring.hometask.domain.UserDiscountCount;

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
import static ua.training.spring.hometask.utills.BuildTestEntityUtill.buildUserDiscountCount;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BeansConfiguration.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles({"HIBERNATE", "TEST"})
class HibernateUserDiscountCountDaoImplIntegrationTest {

    @Autowired
    private HibernateUserDiscountCountDaoImpl hibernateUserDiscountCountDao;

    @Autowired
    private SessionFactory sessionFactory;

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
        assertThat(hibernateUserDiscountCountDao.getAll(), hasSize(1));

        hibernateUserDiscountCountDao.remove(discountCount);
        assertThat(hibernateUserDiscountCountDao.getAll(), is(empty()));
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
        UserDiscountCount userDiscountCount = buildUserDiscountCount();
        hibernateUserDiscountCountDao.save(userDiscountCount);

        Collection<UserDiscountCount> persistedDiscounts = hibernateUserDiscountCountDao.getAll();

        assertThat(persistedDiscounts, hasItems(userDiscountCount));
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

    @Test
    void shouldThrowNotNullConstraintException() {
        UserDiscountCount userDiscountCountWithNullEventName = new UserDiscountCount();

        assertThrows(ConstraintViolationException.class, () -> {
            hibernateUserDiscountCountDao.save(userDiscountCountWithNullEventName);
        });
    }

    @Test
    void shouldThrowUniqueConstraintException() {
        UserDiscountCount userDiscountCount = buildUserDiscountCount();
        UserDiscountCount userDiscountCountWithDuplicatedEventName = buildUserDiscountCount();
        hibernateUserDiscountCountDao.save(userDiscountCount);

        assertThrows(PersistenceException.class, () -> {
            hibernateUserDiscountCountDao.save(userDiscountCountWithDuplicatedEventName);
        });
    }

    @Test
    void shouldCashingGetByName() {
        UserDiscountCount discountCount = buildUserDiscountCount();
        hibernateUserDiscountCountDao.save(discountCount);

        hibernateUserDiscountCountDao.getByName(discountCount.getName());
        assertThat(sessionFactory.getStatistics().getSecondLevelCachePutCount(), is(1L));
        assertThat(sessionFactory.getStatistics().getSecondLevelCacheHitCount(), is(0L));

        hibernateUserDiscountCountDao.getByName(discountCount.getName());
        assertThat(sessionFactory.getStatistics().getSecondLevelCachePutCount(), is(1L));
        assertThat(sessionFactory.getStatistics().getSecondLevelCacheHitCount(), is(1L));
    }

    @Test
    void shouldCashingGetById() {
        UserDiscountCount discountCount = buildUserDiscountCount();
        hibernateUserDiscountCountDao.save(discountCount);

        hibernateUserDiscountCountDao.getById(1L);
        assertThat(sessionFactory.getStatistics().getSecondLevelCachePutCount(), is(1L));
        assertThat(sessionFactory.getStatistics().getSecondLevelCacheHitCount(), is(0L));

        hibernateUserDiscountCountDao.getById(1L);
        assertThat(sessionFactory.getStatistics().getSecondLevelCachePutCount(), is(1L));
        assertThat(sessionFactory.getStatistics().getSecondLevelCacheHitCount(), is(1L));
    }

    @Test
    void shouldCashingGetAll() {
        UserDiscountCount discountCount1 = buildUserDiscountCount();
        UserDiscountCount discountCount2 = buildUserDiscountCount();
        discountCount2.setName("testEventName2");

        hibernateUserDiscountCountDao.save(discountCount1);
        hibernateUserDiscountCountDao.save(discountCount2);

        hibernateUserDiscountCountDao.getAll();
        assertThat(sessionFactory.getStatistics().getSecondLevelCachePutCount(), is(2L));
        assertThat(sessionFactory.getStatistics().getSecondLevelCacheHitCount(), is(0L));

        hibernateUserDiscountCountDao.getAll();
        assertThat(sessionFactory.getStatistics().getSecondLevelCachePutCount(), is(2L));
        assertThat(sessionFactory.getStatistics().getSecondLevelCacheHitCount(), is(2L));
    }

    @Test
    void shouldRemoveFromCache() {
        UserDiscountCount userDiscountCount = buildUserDiscountCount();
        hibernateUserDiscountCountDao.save(userDiscountCount);
        hibernateUserDiscountCountDao.getById(userDiscountCount.getId());

        assertThat(sessionFactory.getStatistics().getSecondLevelCachePutCount(), is(1L));
        hibernateUserDiscountCountDao.remove(userDiscountCount);
        assertThat(hibernateUserDiscountCountDao.getById(userDiscountCount.getId()), is(nullValue()));
    }
}