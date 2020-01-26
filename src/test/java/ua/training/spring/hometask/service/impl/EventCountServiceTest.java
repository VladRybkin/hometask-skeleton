package ua.training.spring.hometask.service.impl;


import com.google.common.collect.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.training.spring.hometask.dao.impl.EventCounterDaoImpl;
import ua.training.spring.hometask.domain.EventCountInfo;

import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventCountServiceTest {

    @InjectMocks
    private DefaultEventCountService eventCountService;

    @Mock
    private EventCounterDaoImpl eventCounterDao;

    private EventCountInfo testEventCountInfo;

    private static String TEST_NAME = "testname";

    @BeforeEach
    public void setUp() {
        testEventCountInfo = new EventCountInfo.Builder()
                .withEventName(TEST_NAME)
                .withCountBookTicket(1)
                .withCountGetByName(1)
                .withCountGetPrice(1)
                .build();
    }


    @Test
    public void getByNameCountIncrement() {
        long expectedAfterIncrement = 2;
        when(eventCounterDao.getByName(TEST_NAME)).thenReturn(testEventCountInfo);
        eventCountService.getByNameCountIncrement(TEST_NAME);
        assertThat(testEventCountInfo.getCountGetByName(), is(expectedAfterIncrement));
        verify(eventCounterDao).save(testEventCountInfo);
    }

    @Test
    public void bookTicketsCountIncrement() {
        long expectedAfterIncrement = 2;
        when(eventCounterDao.getByName(TEST_NAME)).thenReturn(testEventCountInfo);
        eventCountService.bookTicketsCountIncrement(TEST_NAME);
        assertThat(testEventCountInfo.getCountBookTickets(), is(expectedAfterIncrement));
        verify(eventCounterDao).save(testEventCountInfo);
    }

    @Test
    public void getPriceCountIncrement() {
        long expectedAfterIncrement = 2;
        when(eventCounterDao.getByName(TEST_NAME)).thenReturn(testEventCountInfo);
        eventCountService.getPriceCountIncrement(TEST_NAME);
        assertThat(testEventCountInfo.getCountGetPrice(), is(expectedAfterIncrement));
        verify(eventCounterDao).save(testEventCountInfo);
    }

    @Test
    public void save() {
        when(eventCounterDao.save(testEventCountInfo)).thenReturn(testEventCountInfo);
        assertThat(eventCountService.save(testEventCountInfo), is(testEventCountInfo));
        verify(eventCounterDao, times(1)).save(testEventCountInfo);
    }

    @Test
    public void getByName() {
        when(eventCounterDao.getByName(TEST_NAME)).thenReturn(testEventCountInfo);
        assertThat(eventCountService.getByName(TEST_NAME), is(testEventCountInfo));
        verify(eventCounterDao, times(1)).getByName(TEST_NAME);
    }

    @Test
    public void remove() {
        eventCountService.remove(testEventCountInfo);
        verify(eventCounterDao, times(1)).remove(testEventCountInfo);
    }

    @Test
    public void getById() {
        long testId = 666;
        when(eventCounterDao.getById(testId)).thenReturn(testEventCountInfo);
        assertThat(eventCountService.getById(testId), is(testEventCountInfo));
        verify(eventCounterDao, times(1)).getById(testId);
    }

    @Test
    public void getAll() {
        Set<EventCountInfo> userDiscountCountInfos = Sets.newHashSet();
        userDiscountCountInfos.add(testEventCountInfo);
        when(eventCounterDao.getAll()).thenReturn(userDiscountCountInfos);
        assertThat(eventCountService.getAll(), is(userDiscountCountInfos));
        verify(eventCounterDao, times(1)).getAll();
    }
}
