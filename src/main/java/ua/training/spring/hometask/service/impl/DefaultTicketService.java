package ua.training.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.training.spring.hometask.dao.TicketDao;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.service.TicketService;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Service
public class DefaultTicketService implements TicketService {

    @Autowired
    private TicketDao ticketDao;

    @Transactional
    @Override
    public Ticket save(final Ticket ticket) {
        return ticketDao.save(ticket);
    }

    @Transactional
    @Override
    public void remove(final Ticket ticket) {
        ticketDao.remove(ticket);
    }

    @Override
    public Ticket getById(final Long id) {
        return ticketDao.getById(id);
    }

    @Override
    public Collection<Ticket> getAll() {
        return ticketDao.getAll();
    }

    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(final Event event, final LocalDateTime dateTime) {
        return ticketDao.getPurchasedTicketsForEvent(event, dateTime);
    }

    @Override
    public boolean update(final Ticket ticket) {
        return ticketDao.update(ticket);
    }

    @Transactional
    @Override
    public Collection<Ticket> saveAll(final Collection<Ticket> tickets) {
        tickets.forEach(ticketDao::save);

        return tickets;
    }
}
