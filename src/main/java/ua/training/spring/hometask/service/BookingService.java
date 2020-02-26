package ua.training.spring.hometask.service;

import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
import java.util.Set;


public interface BookingService {

    double getTicketsPrice(@Nonnull Event event, @Nonnull User user, @Nonnull Set<Long> seats);

    void bookTickets(@Nonnull Set<Ticket> tickets, @Nonnull User user);

    @Nonnull
    Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime);

    @Nonnull
    Ticket bookTicket(@Nonnull Ticket ticket, @Nonnull User user);
}
