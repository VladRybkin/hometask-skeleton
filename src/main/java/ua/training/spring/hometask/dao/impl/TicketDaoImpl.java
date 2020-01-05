package ua.training.spring.hometask.dao.impl;

import org.springframework.stereotype.Component;
import ua.training.spring.hometask.dao.TicketDao;
import ua.training.spring.hometask.domain.Ticket;


import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class TicketDaoImpl implements TicketDao {

    private static final Map<Long, Ticket> tickets = new HashMap<>();

    @Override
    public Ticket save(@Nonnull Ticket object) {
        object.setId((long) (tickets.size() + 1));
        tickets.put(object.getId(), object);
        return object;
    }

    @Override
    public void remove(@Nonnull Ticket object) {
        tickets.remove(object.getId());
    }

    @Override
    public Ticket getById(@Nonnull Long id) {

        return tickets.get(id);
    }

    @Nonnull
    @Override
    public Collection<Ticket> getAll() {
        return tickets.values();
    }


}
