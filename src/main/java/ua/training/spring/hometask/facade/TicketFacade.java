package ua.training.spring.hometask.facade;

import ua.training.spring.hometask.domain.Ticket;

public interface TicketFacade {

    void saveTicketWithEvent(String eventName, Ticket ticket);
}
