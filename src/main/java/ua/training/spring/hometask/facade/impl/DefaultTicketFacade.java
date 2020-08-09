package ua.training.spring.hometask.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.facade.TicketFacade;
import ua.training.spring.hometask.service.EventService;
import ua.training.spring.hometask.service.TicketService;

@Component
public class DefaultTicketFacade implements TicketFacade {

    @Autowired
    private EventService eventService;

    @Autowired
    private TicketService ticketService;

    @Override
    public void saveTicketWithEvent(String eventName, Ticket ticket) {
        Event event = eventService.getByName(eventName);
        ticket.setEvent(event);
        ticketService.save(ticket);
    }
}
