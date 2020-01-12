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
import ua.training.spring.hometask.service.EventService;

import java.time.LocalDateTime;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DefaultEventServiceTest {

    @InjectMocks
    private EventService eventService = new DefaultEventService();

    @Mock
    private EventDao eventDao;

    private Event testEvent;

    private static final Long ID = 666L;

    private LocalDateTime localDateTimeNext;

    private static final String TEST_EVENT_NAME = "TEST_EVENT_NAME";

    private static Set<Event> testEventsSet = Sets.newHashSet();

    @BeforeEach
    void setUp() {

        testEvent = new Event();
        testEvent.setName(TEST_EVENT_NAME);
        testEvent.setId(ID);
        localDateTimeNext = LocalDateTime.now().plusDays(5);
    }

    @Test
    void getByName() {
        when(eventDao.getByName(TEST_EVENT_NAME)).thenReturn(testEvent);
        assertThat(eventService.getByName(TEST_EVENT_NAME), is(testEvent));
        verify(eventDao).getByName(TEST_EVENT_NAME);
    }

    @Test
    void getForDateRange() {

        LocalDateTime minusFiveDaysTime = LocalDateTime.now().minusDays(5);
        LocalDateTime timeNow = LocalDateTime.now();
        when(eventDao.getForDateRange(minusFiveDaysTime, timeNow)).thenReturn(testEventsSet);

        assertThat(eventService.getForDateRange(minusFiveDaysTime, timeNow), is(testEventsSet));
        verify(eventDao).getForDateRange(minusFiveDaysTime, timeNow);
    }

    @Test
    void getNextEvents() {
        when(eventDao.getNextEvents(localDateTimeNext)).thenReturn(testEventsSet);
        assertThat(eventService.getNextEvents(localDateTimeNext), is(testEventsSet));
        verify(eventDao).getNextEvents(localDateTimeNext);
    }

    @Test
    void save() {
        when(eventDao.save(testEvent)).thenReturn(testEvent);
        assertThat(eventService.save(testEvent), is(testEvent));
        verify(eventDao).save(testEvent);
    }

    @Test
    void remove() {
        eventService.remove(testEvent);
        verify(eventDao).remove(testEvent);
    }

    @Test
    void getById() {
        when(eventDao.getById(ID)).thenReturn(testEvent);
        assertThat(eventService.getById(ID), is(testEvent));
        verify(eventDao).getById(ID);
    }

    @Test
    void getAll() {
        when(eventDao.getAll()).thenReturn(testEventsSet);
        assertThat(eventService.getAll(), is(testEventsSet));
        verify(eventDao).getAll();
    }
}