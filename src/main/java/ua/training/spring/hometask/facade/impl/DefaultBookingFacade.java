package ua.training.spring.hometask.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.facade.BookingFacade;
import ua.training.spring.hometask.service.BookingService;
import ua.training.spring.hometask.service.TicketService;
import ua.training.spring.hometask.service.UserService;

@Component
public class DefaultBookingFacade implements BookingFacade {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    @Transactional
    @Override
    public Ticket bookTicket(Long ticketId, Long userId) {
        Ticket ticket = ticketService.getById(ticketId);
        User user = userService.getById(userId);
        return bookingService.bookTicket(ticket, user);
    }
}
