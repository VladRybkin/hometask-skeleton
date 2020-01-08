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
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.service.TicketService;

import java.util.List;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DefaultTicketServiceTest {


    @InjectMocks
    private TicketService ticketService = new DefaultTicketService();

    @Mock
    private TicketDao ticketDao;


    private static final Long ID = 666L;

    private Ticket testTicket;

    @BeforeEach
    void setUp() {
        testTicket = new Ticket();
    }


    @Test
    void save() {
        when(ticketService.save(testTicket)).thenReturn(testTicket);
        ticketService.save(testTicket);
        verify(ticketDao).save(testTicket);
    }

    @Test
    void remove() {
        ticketService.remove(testTicket);
        verify(ticketDao).remove(testTicket);
    }

    @Test
    void getById() {
        when(ticketService.getById(ID)).thenReturn(testTicket);
        ticketService.getById(ID);
        verify(ticketDao).getById(ID);
    }

    @Test
    void getAll() {
        List<Ticket> tickets = Lists.newArrayList();
        when(ticketService.getAll()).thenReturn(tickets);
        ticketService.getAll();
        verify(ticketDao).getAll();
    }

}
