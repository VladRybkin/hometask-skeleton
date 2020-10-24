package ua.training.spring.hometask.facade.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.security.SecurityService;
import ua.training.spring.hometask.service.BookingService;
import ua.training.spring.hometask.service.TicketService;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultBookingFacadeTest {

    @InjectMocks
    private DefaultBookingFacade bookingFacade;

    @Mock
    private TicketService ticketService;

    @Mock
    private SecurityService securityService;

    @Mock
    private BookingService bookingService;

    @Test
    void bookTicket() {
        Ticket ticket = new Ticket();
        User user = new User();

        when(ticketService.getById(1L)).thenReturn(ticket);
        when(securityService.getLoggedUser()).thenReturn(user);

        bookingFacade.bookTicket(1L);

        verify(ticketService).getById(1L);
        verify(securityService).getLoggedUser();
        verify(bookingService).bookTicket(ticket, user);
    }
}