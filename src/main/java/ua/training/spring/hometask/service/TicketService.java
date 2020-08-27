package ua.training.spring.hometask.service;

import ua.training.spring.hometask.dao.AbstractDomainObjectDao;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.Ticket;

import java.time.LocalDateTime;
import java.util.Set;

public interface TicketService extends AbstractDomainObjectService<Ticket> {


    Set<Ticket> getPurchasedTicketsForEvent(Event event, LocalDateTime dateTime);
}
