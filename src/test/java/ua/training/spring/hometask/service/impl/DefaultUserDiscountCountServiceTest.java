package ua.training.spring.hometask.service.impl;


import com.google.common.collect.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.training.spring.hometask.dao.UserDiscountDao;
import ua.training.spring.hometask.domain.UserDiscountCountInfo;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DefaultUserDiscountCountServiceTest {

    @InjectMocks
    private DefaultUserDiscountCountService userDiscountCountService;

    @Mock
    private UserDiscountDao discountDao;

    private UserDiscountCountInfo testUserDiscountInfo;

    private static String TEST_NAME = "testname";

    @BeforeEach
    public void setUp() {
        testUserDiscountInfo = new UserDiscountCountInfo.Builder()
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
        verify(discountDao).save(testUserDiscountInfo);
    }

    @Test
    public void countBirthdayDiscountIncrement() {
        long expectedAfterIncrement = 2;
        when(discountDao.getByName(TEST_NAME)).thenReturn(testUserDiscountInfo);
        userDiscountCountService.countBirthdayDiscountIncrement(TEST_NAME);
        assertThat(testUserDiscountInfo.getCountBirthdayDiscount(), is(expectedAfterIncrement));
        verify(discountDao).save(testUserDiscountInfo);
    }

    @Test
    public void getByName() {
        when(discountDao.getByName(TEST_NAME)).thenReturn(testUserDiscountInfo);
        assertThat(userDiscountCountService.getByName(TEST_NAME), is(testUserDiscountInfo));
        verify(discountDao, times(1)).getByName(TEST_NAME);
    }

    @Test
    public void save() {
        when(discountDao.save(testUserDiscountInfo)).thenReturn(testUserDiscountInfo);
        assertThat(userDiscountCountService.save(testUserDiscountInfo), is(testUserDiscountInfo));
        verify(discountDao, times(1)).save(testUserDiscountInfo);
    }

    @Test
    public void remove() {
        userDiscountCountService.remove(testUserDiscountInfo);
        verify(discountDao, times(1)).remove(testUserDiscountInfo);
    }

    @Test
    public void getById() {
        long testId = 666;
        when(discountDao.getById(testId)).thenReturn(testUserDiscountInfo);
        assertThat(userDiscountCountService.getById(testId), is(testUserDiscountInfo));
        verify(discountDao, times(1)).getById(testId);
    }

    @Test
    public void getAll() {
        Set<UserDiscountCountInfo> userDiscountCountInfos = Sets.newHashSet();
        userDiscountCountInfos.add(testUserDiscountInfo);
        when(discountDao.getAll()).thenReturn(userDiscountCountInfos);
        assertThat(userDiscountCountService.getAll(), is(userDiscountCountInfos));
        verify(discountDao, times(1)).getAll();
    }

}
