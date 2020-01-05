package ua.training.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.training.spring.hometask.dao.TicketDao;
import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.service.TicketService;

import javax.annotation.Nonnull;
import java.util.Collection;

@Component
public class DefaultTicketService implements TicketService {

    @Autowired
    private TicketDao ticketDao;

    @Override
    public Ticket save(@Nonnull Ticket object) {
        return ticketDao.save(object);
    }

    @Override
    public void remove(@Nonnull Ticket object) {
        ticketDao.remove(object);
    }

    @Override
    public Ticket getById(@Nonnull Long id) {
        return ticketDao.getById(id);
    }

    @Nonnull
    @Override
    public Collection<Ticket> getAll() {
        return ticketDao.getAll();
    }
}
