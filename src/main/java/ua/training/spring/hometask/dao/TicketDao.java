package ua.training.spring.hometask.dao;

import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.Ticket;

import java.time.LocalDateTime;
import java.util.Set;

public interface TicketDao extends AbstractDomainObjectDao<Ticket> {

    Set<Ticket> getPurchasedTicketsForEvent(Event event, LocalDateTime dateTime);

}
