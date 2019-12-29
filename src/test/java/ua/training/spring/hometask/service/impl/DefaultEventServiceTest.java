package ua.training.spring.hometask.service.impl;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.training.spring.hometask.dao.EventDao;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.service.EventService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DefaultEventServiceTest {

    @InjectMocks
    private EventService eventService = new DefaultEventService();

    @Mock
    private EventDao eventDao;

    private Event testEvent;

    private static Long ID;

    private static LocalDate localDateFrom;

    private static LocalDate localDateTo;

    private static final String TEST_EVENT_NAME = "TEST_EVENT_NAME";

    @BeforeEach
    void setUp() {
        ID = 666L;
        testEvent = new Event(TEST_EVENT_NAME);
        localDateFrom = LocalDate.now().minusDays(5);
        localDateTo = LocalDate.now();
    }

    @Test
    void getByName() {
        lenient().when(eventService.getByName(TEST_EVENT_NAME)).thenReturn(testEvent);
        eventService.getByName(TEST_EVENT_NAME);
        verify(eventDao).getByName(TEST_EVENT_NAME);
    }

    @Test
    void getForDateRange() {
        lenient().when(eventService.getForDateRange(LocalDate.now().minusDays(5), LocalDate.now()))
                .thenReturn(new HashSet<Event>());
        eventService.getForDateRange(LocalDate.now().minusDays(5), LocalDate.now());
        verify(eventDao).getForDateRange(LocalDate.now().minusDays(5), LocalDate.now());
    }

    @Test
    void getNextEvents() {
    }

    @Test
    void save() {
        lenient().when(eventService.save(testEvent)).thenReturn(testEvent);
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
        eventService.getById(ID);
        verify(eventDao).getById(ID);
    }

    @Test
    void getAll() {
        lenient().when(eventService.getAll()).thenReturn(new ArrayList<Event>());
        eventService.getAll();
        verify(eventDao).getAll();
    }
}