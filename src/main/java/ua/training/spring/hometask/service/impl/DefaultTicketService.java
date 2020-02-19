package ua.training.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.training.spring.hometask.dao.TicketDao;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.service.TicketService;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Service
public class DefaultTicketService implements TicketService {

    @Autowired
    private TicketDao ticketDao;

    @Transactional
    @Override
    public Ticket save(@Nonnull Ticket object) {
        return ticketDao.save(object);
    }

    @Transactional
    @Override
    public void remove(@Nonnull Ticket object) {
        ticketDao.remove(object);
    }

    @Transactional(readOnly = true)
    @Override
    public Ticket getById(@Nonnull Long id) {
        return ticketDao.getById(id);
    }

    @Nonnull
    @Transactional(readOnly = true)
    @Override
    public Collection<Ticket> getAll() {
        return ticketDao.getAll();
    }

    @Nonnull
    @Transactional(readOnly = true)
    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime) {
        return ticketDao.getPurchasedTicketsForEvent(event, dateTime);
    }
}
