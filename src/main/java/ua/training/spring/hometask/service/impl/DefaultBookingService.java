package ua.training.spring.hometask.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.EventRating;
import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.service.BookingService;
import ua.training.spring.hometask.service.DiscountService;
import ua.training.spring.hometask.service.TicketService;
import ua.training.spring.hometask.service.UserService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DefaultBookingService implements BookingService {

    @Autowired
    private DiscountService discountService;

    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;

    @Override
    public double getTicketsPrice(@Nonnull Event event, @Nullable User user, @Nonnull Set<Long> seats) {
        Set<Ticket> tickets = seats.stream().map(seat -> createTicket(event, seat)).collect(Collectors.toSet());
        double totalPrice = getTotalPrice(tickets);
        double discount = discountService.getDiscount(user, tickets);

        return applyDiscounts(totalPrice, discount);
    }

    @Transactional
    @Override
    public void bookTickets(@Nonnull Set<Ticket> tickets, @Nonnull User user) {
        tickets.forEach(ticket -> bookTicket(ticket, user));
    }

    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime) {
        return ticketService.getPurchasedTicketsForEvent(event, dateTime);
    }

    @Transactional
    @Override
    public Ticket bookTicket(@Nonnull Ticket ticket, @Nonnull User user) {
        user.getTickets().add(ticket);
        ticket.setUser(user);
        userService.save(user);
        ticketService.save(ticket);

        return ticket;
    }

    private Ticket createTicket(Event event, Long seat) {
        Ticket ticket = new Ticket();
        double eventBonus = getBonusForEventRating(event.getRating());
        ticket.setEvent(event);
        ticket.setBasePrice(event.getBasePrice() * eventBonus);
        ticket.setSeat(seat);

        return ticket;
    }

    private double getBonusForEventRating(EventRating eventRating) {
        if (Objects.equals(eventRating, EventRating.LOW)) {
            return 1;
        }
        if (Objects.equals(eventRating, EventRating.MID)) {
            return 1.5;
        }
        if (Objects.equals(eventRating, EventRating.HIGH)) {
            return 2;
        } else {
            return 1;
        }
    }

    private double applyDiscounts(double totalPrice, double discount) {
        double finalPrice;
        if (discount != 0) {
            finalPrice = totalPrice - ((totalPrice / 100) * discount);
        } else {
            finalPrice = totalPrice;
        }

        return finalPrice;
    }

    private double getTotalPrice(Set<Ticket> tickets) {
        return tickets.stream().mapToDouble(Ticket::getBasePrice).sum();
    }
}
