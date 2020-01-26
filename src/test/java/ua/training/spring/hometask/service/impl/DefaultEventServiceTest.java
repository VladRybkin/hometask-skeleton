package ua.training.spring.hometask.service.impl;


import com.google.common.collect.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.training.spring.hometask.dao.EventDao;
import ua.training.spring.hometask.domain.Event;


import java.time.LocalDateTime;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DefaultEventServiceTest {

    @InjectMocks
    private DefaultEventService eventService;

    @Mock
    private EventDao eventDao;

    private Event testEvent;

    private static final Long ID = 666L;

    private LocalDateTime localDateTimeNext;

    private static final String TEST_EVENT_NAME = "TEST_EVENT_NAME";

    private static Set<Event> testEventsSet = Sets.newHashSet();

    @BeforeEach
    public void setUp() {
        testEvent = new Event();
        testEvent.setName(TEST_EVENT_NAME);
        testEvent.setId(ID);
        localDateTimeNext = LocalDateTime.now().plusDays(5);
        testEventsSet.add(testEvent);
    }

    @Test
    public void getByName() {
        when(eventDao.getByName(TEST_EVENT_NAME)).thenReturn(testEvent);
        assertThat(eventService.getByName(TEST_EVENT_NAME), is(testEvent));
        verify(eventDao).getByName(TEST_EVENT_NAME);
    }

    @Test
    public void getForDateRange() {

        LocalDateTime minusFiveDaysTime = LocalDateTime.now().minusDays(5);
        LocalDateTime timeNow = LocalDateTime.now();
        when(eventDao.getForDateRange(minusFiveDaysTime, timeNow)).thenReturn(testEventsSet);

        assertThat(eventService.getForDateRange(minusFiveDaysTime, timeNow), is(testEventsSet));
        verify(eventDao).getForDateRange(minusFiveDaysTime, timeNow);
    }

    @Test
    public void getNextEvents() {
        when(eventDao.getNextEvents(localDateTimeNext)).thenReturn(testEventsSet);
        assertThat(eventService.getNextEvents(localDateTimeNext), is(testEventsSet));
        verify(eventDao).getNextEvents(localDateTimeNext);
    }

    @Test
    public void save() {
        when(eventDao.save(testEvent)).thenReturn(testEvent);
        assertThat(eventService.save(testEvent), is(testEvent));
        verify(eventDao).save(testEvent);
    }

    @Test
    public  void remove() {
        eventService.remove(testEvent);
        verify(eventDao).remove(testEvent);
    }

    @Test
    public  void getById() {
        when(eventDao.getById(ID)).thenReturn(testEvent);
        assertThat(eventService.getById(ID), is(testEvent));
        verify(eventDao).getById(ID);
    }

    @Test
    public void getAll() {
        when(eventDao.getAll()).thenReturn(testEventsSet);
        assertThat(eventService.getAll(), is(testEventsSet));
        verify(eventDao).getAll();
    }
}