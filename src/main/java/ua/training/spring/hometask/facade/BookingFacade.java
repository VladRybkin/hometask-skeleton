package ua.training.spring.hometask.facade;

import ua.training.spring.hometask.domain.Ticket;

public interface BookingFacade {

    Ticket bookTicket(Long ticketId);
}
