package ua.training.spring.hometask.service;

import java.time.LocalDateTime;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.domain.User;

/**
 * @author Yuriy_Tkach
 */
public interface BookingService {


    double getTicketsPrice(@Nonnull Event event, @Nonnull User user, @Nonnull Set<Long> seats);

    void bookTickets(@Nonnull Set<Ticket> tickets, User user);

    @Nonnull
    Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime);

    @Nonnull
    Ticket bookTicket(Ticket ticket, User user);
}
