package ua.training.spring.hometask.facade.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.training.spring.hometask.domain.Auditorium;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.service.AuditoriumService;
import ua.training.spring.hometask.service.EventService;

import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.MINUTES;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultEventFacadeTest {

    @InjectMocks
    private DefaultEventFacade eventFacade;

    @Mock
    private EventService eventService;

    @Mock
    private AuditoriumService auditoriumService;

    private final String AUDITORIUM_NAME = "test auditorium";

    @Test
    void saveEvent() {
        Event event = new Event();
        Auditorium auditorium = new Auditorium();
        auditorium.setName(AUDITORIUM_NAME);
        final LocalDateTime eventDate = LocalDateTime.now().truncatedTo(MINUTES);

        when(auditoriumService.getByName(AUDITORIUM_NAME)).thenReturn(auditorium);

        eventFacade.saveEvent(event, eventDate.toString(), AUDITORIUM_NAME);
        event.getAirDates().add(eventDate);

        verify(eventService).save(event);
    }
}