package ua.training.spring.hometask.service.impl;


import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.training.spring.hometask.dao.UserDiscountCountDao;
import ua.training.spring.hometask.domain.UserDiscountCount;

import java.util.Collection;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class DefaultUserDiscountCountServiceTest {

    @InjectMocks
    private DefaultUserDiscountCountService userDiscountCountService;

    @Mock
    private UserDiscountCountDao discountDao;

    private UserDiscountCount testUserDiscountInfo;

    private static String TEST_NAME = "testname";

    @BeforeEach
    public void setUp() {
        testUserDiscountInfo = new UserDiscountCount.Builder()
                .withUserName(TEST_NAME)
                .withCountBirthdayDiscount(1)
                .withCountTenthTicketDiscount(1)
                .build();
    }

    @Test
    public void countTenthTicketDiscountIncrement() {
        long expectedAfterIncrement = 2;
        when(discountDao.getByName(TEST_NAME)).thenReturn(testUserDiscountInfo);
        userDiscountCountService.countTenthTicketDiscountIncrement(TEST_NAME);
        assertThat(testUserDiscountInfo.getCountTenthTicketDiscount(), is(expectedAfterIncrement));
        verify(discountDao).update(testUserDiscountInfo);
    }

    @Test
    public void countBirthdayDiscountIncrement() {
        long expectedAfterIncrement = 2;
        when(discountDao.getByName(TEST_NAME)).thenReturn(testUserDiscountInfo);
        userDiscountCountService.countBirthdayDiscountIncrement(TEST_NAME);
        assertThat(testUserDiscountInfo.getCountBirthdayDiscount(), is(expectedAfterIncrement));
        verify(discountDao).update(testUserDiscountInfo);
    }

    @Test
    public void getByName() {
        when(discountDao.getByName(TEST_NAME)).thenReturn(testUserDiscountInfo);

        UserDiscountCount persistedUserDiscountCount = userDiscountCountService.getByName(TEST_NAME);

        assertThat(persistedUserDiscountCount, is(testUserDiscountInfo));
        verify(discountDao).getByName(TEST_NAME);
    }

    @Test
    public void save() {
        when(discountDao.save(testUserDiscountInfo)).thenReturn(testUserDiscountInfo);

        UserDiscountCount persistedUserDiscountCount = userDiscountCountService.save(testUserDiscountInfo);

        assertThat(persistedUserDiscountCount, is(testUserDiscountInfo));
        verify(discountDao).save(testUserDiscountInfo);
    }

    @Test
    public void remove() {
        userDiscountCountService.remove(testUserDiscountInfo);
        verify(discountDao).remove(testUserDiscountInfo);
    }

    @Test
    public void getById() {
        long testId = 666;
        when(discountDao.getById(testId)).thenReturn(testUserDiscountInfo);

        UserDiscountCount persistedDiscountCount = userDiscountCountService.getById(testId);

        assertThat(persistedDiscountCount, is(testUserDiscountInfo));
        verify(discountDao).getById(testId);
    }

    @Test
    public void getAll() {
        List<UserDiscountCount> givenUserDiscountCounts = Lists.newArrayList(testUserDiscountInfo);
        when(discountDao.getAll()).thenReturn(givenUserDiscountCounts);

        Collection<UserDiscountCount> persistedDiscounts = userDiscountCountService.getAll();

        assertThat(persistedDiscounts, is(givenUserDiscountCounts));
        verify(discountDao).getAll();
    }

}
