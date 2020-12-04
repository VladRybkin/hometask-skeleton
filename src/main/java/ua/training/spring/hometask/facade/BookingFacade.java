package ua.training.spring.hometask.facade;

import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.dto.rest.request.BookTicketParameter;
import ua.training.spring.hometask.dto.rest.response.BookTicketResult;

public interface BookingFacade {

    Ticket bookTicket(Long ticketId);

    BookTicketResult bookTicket(BookTicketParameter bookTicketParameter);
}
