package ua.training.spring.hometask.service;

import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.domain.User;

import java.time.LocalDateTime;
import java.util.Set;


public interface BookingService {

    double getTicketsPrice(Event event, User user, Set<Long> seats);

    void bookTickets(Set<Ticket> tickets, User user);

    Set<Ticket> getPurchasedTicketsForEvent(Event event, LocalDateTime dateTime);

    Ticket bookTicket(Ticket ticket, User user);
}
