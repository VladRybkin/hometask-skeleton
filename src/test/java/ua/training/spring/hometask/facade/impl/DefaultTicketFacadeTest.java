package ua.training.spring.hometask.facade.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.service.EventService;
import ua.training.spring.hometask.service.TicketService;

import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.MINUTES;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultTicketFacadeTest {

    @InjectMocks
    private DefaultTicketFacade ticketFacade;

    @Mock
    private EventService eventService;

    @Mock
    private TicketService ticketService;

    private final String EVENT_NAME = "event name";

    @Test
    void saveTicketWithEvent() {
        Event event = new Event();
        event.getAirDates().add(LocalDateTime.now());
        Ticket ticket = new Ticket();

        when(eventService.getByName(EVENT_NAME)).thenReturn(event);

        ticketFacade.saveTicketWithEvent(EVENT_NAME, ticket);

        verify(eventService).getByName(EVENT_NAME);

        ticket.setEvent(event);
        verify(ticketService).save(ticket);
    }

    @Test
    void getPurchasedTicketsForEvent() {
        Event event = new Event();
        final LocalDateTime eventDate = LocalDateTime.now().truncatedTo(MINUTES);

        when(eventService.getByName(EVENT_NAME)).thenReturn(event);

        ticketFacade.getPurchasedTicketsForEvent(EVENT_NAME, eventDate.toString());

        verify(ticketService).getPurchasedTicketsForEvent(event, eventDate);
    }
}