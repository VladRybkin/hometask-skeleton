package ua.training.spring.hometask.facade.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.service.BookingService;
import ua.training.spring.hometask.service.TicketService;
import ua.training.spring.hometask.service.UserService;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultBookingFacadeTest {

    @InjectMocks
    private DefaultBookingFacade bookingFacade;

    @Mock
    private TicketService ticketService;

    @Mock
    private UserService userService;

    @Mock
    private BookingService bookingService;

    @Test
    void bookTicket() {
        Ticket ticket = new Ticket();
        User user = new User();

        when(ticketService.getById(1L)).thenReturn(ticket);
        when(userService.getById(1L)).thenReturn(user);

        bookingFacade.bookTicket(1L, 1L);

        verify(ticketService).getById(1L);
        verify(userService).getById(1L);
        verify(bookingService).bookTicket(ticket, user);
    }
}