package ua.training.spring.hometask.facade;

import ua.training.spring.hometask.domain.Ticket;

import java.util.Collection;

public interface TicketFacade {

    void saveTicketWithEvent(String eventName, Ticket ticket);

    Collection<Ticket> getPurchasedTicketsForEvent(String eventName, String eventDate);
}
