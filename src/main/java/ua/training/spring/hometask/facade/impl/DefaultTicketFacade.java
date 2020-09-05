package ua.training.spring.hometask.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.facade.TicketFacade;
import ua.training.spring.hometask.service.EventService;
import ua.training.spring.hometask.service.TicketService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

@Component
public class DefaultTicketFacade implements TicketFacade {

    @Autowired
    private EventService eventService;

    @Autowired
    private TicketService ticketService;

    @Transactional
    @Override
    public void saveTicketWithEvent(String eventName, Ticket ticket) {
        Event event = eventService.getByName(eventName);
        ticket.setDateTime(event.getAirDates().stream().findFirst().get());
        ticket.setEvent(event);
        ticketService.save(ticket);
    }

    @Override
    public Collection<Ticket> getPurchasedTicketsForEvent(String eventName, String eventDate) {
        Event event = eventService.getByName(eventName);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime parsedTicketDate = LocalDateTime.parse(eventDate, formatter);

        return ticketService.getPurchasedTicketsForEvent(event, parsedTicketDate);
    }
}
