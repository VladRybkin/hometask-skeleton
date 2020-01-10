package ua.training.spring.hometask.service;

import ua.training.spring.hometask.dao.AbstractDomainObjectDao;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.Ticket;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
import java.util.Set;

public interface TicketService extends AbstractDomainObjectDao<Ticket> {

    @Nonnull
    Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime);

}
