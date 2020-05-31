package ua.training.spring.hometask.service;

import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.Set;


public interface BookingService {

    double getTicketsPrice(@Nonnull Event event, @Nullable User user, @Nonnull Set<Long> seats);

    void bookTickets(@Nonnull Set<Ticket> tickets, @Nonnull User user);

    Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime);

    Ticket bookTicket(@Nonnull Ticket ticket, @Nonnull User user);
}
