package ua.training.spring.hometask.service.impl;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.training.spring.hometask.dao.TicketDao;
import ua.training.spring.hometask.domain.Ticket;

import java.util.Collection;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultTicketServiceTest {

    private static final Long ID = 666L;

    @InjectMocks
    private DefaultTicketService ticketService;

    @Mock
    private TicketDao ticketDao;

    private Ticket testTicket;


    @BeforeEach
    void setUp() {
        testTicket = new Ticket();
    }

    @Test
    void save() {
        when(ticketDao.save(testTicket)).thenReturn(testTicket);

        Ticket persistedTicket = ticketService.save(testTicket);

        assertThat(persistedTicket, is(testTicket));
        verify(ticketDao).save(testTicket);
    }

    @Test
    void remove() {
        ticketService.remove(testTicket);
        verify(ticketDao).remove(testTicket);
    }

    @Test
    void getById() {
        when(ticketDao.getById(ID)).thenReturn(testTicket);

        Ticket persistedTicket = ticketService.getById(ID);

        assertThat(persistedTicket, is(testTicket));
        verify(ticketDao).getById(ID);
    }

    @Test
    void getAll() {
        List<Ticket> givenTickets = Lists.newArrayList(testTicket);
        when(ticketDao.getAll()).thenReturn(givenTickets);

        Collection persistedTickets = ticketService.getAll();

        assertThat(persistedTickets, is(givenTickets));
        verify(ticketDao).getAll();
    }
}
