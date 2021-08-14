package ua.training.spring.hometask.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.dto.rest.request.BookTicketParameter;
import ua.training.spring.hometask.dto.rest.response.BookTicketResult;
import ua.training.spring.hometask.facade.BookingFacade;
import ua.training.spring.hometask.security.SecurityService;
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

    @Autowired
    private SecurityService securityService;

    @Transactional
    @Override
    public Ticket bookTicket(Long ticketId) {
        Ticket ticket = ticketService.getById(ticketId);
        User user = securityService.getLoggedUser();

        return bookingService.bookTicket(ticket, user);
    }

    @Transactional
    @Override
    public BookTicketResult bookTicket(BookTicketParameter bookTicketParameter) {
        Ticket ticket = ticketService.getById(bookTicketParameter.getTicketId());
        User user = userService.getUserByEmail(bookTicketParameter.getUserEmail());

        Ticket bookedTicket = bookingService.bookTicket(ticket, user);

        return prepareBookTicketResultFromBookedTicket(bookedTicket);
    }

    private BookTicketResult prepareBookTicketResultFromBookedTicket(Ticket ticket) {
        BookTicketResult bookTicketResult = new BookTicketResult();
        bookTicketResult.setBasePrice(ticket.getBasePrice());
        bookTicketResult.setEventName(ticket.getEvent().getName());
        bookTicketResult.setSeat(ticket.getSeat());

        return bookTicketResult;
    }
}
