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
import java.util.Collection;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DefaultEventServiceTest {

    private static final Long ID = 666L;

    private static final String TEST_EVENT_NAME = "TEST_EVENT_NAME";

    @InjectMocks
    private DefaultEventService eventService;

    @Mock
    private EventDao eventDao;

    private Event testEvent;

    private LocalDateTime localDateTimeNext;

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

        Event persistedEvent = eventService.getByName(TEST_EVENT_NAME);

        assertThat(persistedEvent, is(testEvent));
        verify(eventDao).getByName(TEST_EVENT_NAME);
    }

    @Test
    public void getForDateRange() {
        LocalDateTime minusFiveDaysTime = LocalDateTime.now().minusDays(5);
        LocalDateTime timeNow = LocalDateTime.now();
        when(eventDao.getForDateRange(minusFiveDaysTime, timeNow)).thenReturn(testEventsSet);

        Set<Event> persistedEvents = eventService.getForDateRange(minusFiveDaysTime, timeNow);

        assertThat(persistedEvents, is(testEventsSet));
        verify(eventDao).getForDateRange(minusFiveDaysTime, timeNow);
    }

    @Test
    public void getNextEvents() {
        when(eventDao.getNextEvents(localDateTimeNext)).thenReturn(testEventsSet);

        Set<Event> persistedEvents = eventService.getNextEvents(localDateTimeNext);

        assertThat(persistedEvents, is(testEventsSet));
        verify(eventDao).getNextEvents(localDateTimeNext);
    }

    @Test
    public void save() {
        when(eventDao.save(testEvent)).thenReturn(testEvent);

        Event persistedEvent = eventService.save(testEvent);

        assertThat(persistedEvent, is(testEvent));
        verify(eventDao).save(testEvent);
    }

    @Test
    public void remove() {
        eventService.remove(testEvent);
        verify(eventDao).remove(testEvent);
    }

    @Test
    public void getById() {
        when(eventDao.getById(ID)).thenReturn(testEvent);

        Event persistedEvent = eventService.getById(ID);

        assertThat(persistedEvent, is(testEvent));
        verify(eventDao).getById(ID);
    }

    @Test
    public void getAll() {
        when(eventDao.getAll()).thenReturn(testEventsSet);

        Collection persistedEvents = eventService.getAll();

        assertThat(persistedEvents, is(testEventsSet));
        verify(eventDao).getAll();
    }
}