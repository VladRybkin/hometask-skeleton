package ua.training.spring.hometask.service.impl;


import com.google.common.collect.Lists;
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

import java.util.HashSet;
import java.util.Set;

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

    @BeforeEach
    void setUp() {

        testEvent = new Event(TEST_EVENT_NAME);
        localDateTimeNext = LocalDateTime.now().plusDays(5);
    }

    @Test
    void getByName() {
        when(eventService.getByName(TEST_EVENT_NAME)).thenReturn(testEvent);
        eventService.getByName(TEST_EVENT_NAME);
        verify(eventDao).getByName(TEST_EVENT_NAME);
    }

    @Test
    void getForDateRange() {
        Set<Event> events = new HashSet<>();
        LocalDateTime minusFiveDaysTime = LocalDateTime.now().minusDays(5);
        LocalDateTime timeNow = LocalDateTime.now();
        when(eventService.getForDateRange(minusFiveDaysTime, timeNow))
                .thenReturn(events);
        eventService.getForDateRange(minusFiveDaysTime, timeNow);
        verify(eventDao).getForDateRange(minusFiveDaysTime, timeNow);
    }

    @Test
    void getNextEvents() {
        Set<Event> events = Sets.newHashSet();
        when(eventService.getNextEvents(localDateTimeNext)).thenReturn(events);
        eventService.getNextEvents(localDateTimeNext);
        verify(eventDao).getNextEvents(localDateTimeNext);
    }

    @Test
    void save() {
        when(eventService.save(testEvent)).thenReturn(testEvent);
        eventService.save(testEvent);
        verify(eventDao).save(testEvent);
    }

    @Test
    void remove() {
        eventService.remove(testEvent);
        verify(eventDao).remove(testEvent);
    }

    @Test
    void getById() {
        when(eventService.getById(ID)).thenReturn(testEvent);
        eventService.getById(ID);
        verify(eventDao).getById(ID);
    }

    @Test
    void getAll() {
        when(eventService.getAll()).thenReturn(Lists.newArrayList());
        eventService.getAll();
        verify(eventDao).getAll();
    }
}