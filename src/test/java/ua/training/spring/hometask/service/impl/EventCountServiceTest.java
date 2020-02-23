package ua.training.spring.hometask.service.impl;


import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.training.spring.hometask.dao.EventCountDao;
import ua.training.spring.hometask.domain.EventCount;

import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class EventCountServiceTest {

    private static final String TEST_NAME = "testname";

    @InjectMocks
    private DefaultEventCountService eventCountService;

    @Mock
    private EventCountDao eventCounterDao;

    private EventCount testEventCount;


    @BeforeEach
    void setUp() {
        testEventCount = new EventCount.Builder()
                .withEventName(TEST_NAME)
                .withCountBookTicket(1)
                .withCountGetByName(1)
                .withCountGetPrice(1)
                .build();
    }


    @Test
    void getByNameCountIncrement() {
        long expectedAfterIncrement = 2;
        when(eventCounterDao.getByName(TEST_NAME)).thenReturn(testEventCount);
        eventCountService.getByNameCountIncrement(TEST_NAME);
        assertThat(testEventCount.getCountGetByName(), is(expectedAfterIncrement));
        verify(eventCounterDao).update(testEventCount);
    }

    @Test
    void bookTicketsCountIncrement() {
        long expectedAfterIncrement = 2;
        when(eventCounterDao.getByName(TEST_NAME)).thenReturn(testEventCount);
        eventCountService.bookTicketsCountIncrement(TEST_NAME);
        assertThat(testEventCount.getCountBookTickets(), is(expectedAfterIncrement));
        verify(eventCounterDao).update(testEventCount);
    }

    @Test
    void getPriceCountIncrement() {
        long expectedAfterIncrement = 2;
        when(eventCounterDao.getByName(TEST_NAME)).thenReturn(testEventCount);
        eventCountService.getPriceCountIncrement(TEST_NAME);
        assertThat(testEventCount.getCountGetPrice(), is(expectedAfterIncrement));
        verify(eventCounterDao).update(testEventCount);
    }

    @Test
    void save() {
        when(eventCounterDao.save(testEventCount)).thenReturn(testEventCount);
        EventCount persistedEventCount = eventCountService.save(testEventCount);
        assertThat(persistedEventCount, is(testEventCount));
        verify(eventCounterDao).save(testEventCount);
    }

    @Test
    void getByName() {
        when(eventCounterDao.getByName(TEST_NAME)).thenReturn(testEventCount);
        EventCount persistedEventCount = eventCountService.getByName(TEST_NAME);
        assertThat(persistedEventCount, is(testEventCount));
        verify(eventCounterDao).getByName(TEST_NAME);
    }

    @Test
    void remove() {
        eventCountService.remove(testEventCount);
        verify(eventCounterDao).remove(testEventCount);
    }

    @Test
    void getById() {
        long testId = 666;
        when(eventCounterDao.getById(testId)).thenReturn(testEventCount);
        EventCount persistedEventCount = eventCountService.getById(testId);
        assertThat(persistedEventCount, is(testEventCount));
        verify(eventCounterDao).getById(testId);
    }

    @Test
    void getAll() {
        List<EventCount> givenEventCounts = Lists.newArrayList(testEventCount);
        when(eventCounterDao.getAll()).thenReturn(givenEventCounts);
        Collection<EventCount> persistedEvents = eventCountService.getAll();
        assertThat(persistedEvents, is(givenEventCounts));
        verify(eventCounterDao).getAll();
    }
}
