package ua.training.spring.hometask.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.facade.BookingFacade;
import ua.training.spring.hometask.security.SecurityService;
import ua.training.spring.hometask.service.BookingService;
import ua.training.spring.hometask.service.TicketService;

@Component
public class DefaultBookingFacade implements BookingFacade {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private SecurityService securityService;

    @Transactional
    @Override
    public Ticket bookTicket(Long ticketId) {
        Ticket ticket = ticketService.getById(ticketId);
        User user = securityService.getLoggedUser();

        return bookingService.bookTicket(ticket, user);
    }
}
