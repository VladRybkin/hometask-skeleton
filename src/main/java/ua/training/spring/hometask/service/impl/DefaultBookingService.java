package ua.training.spring.hometask.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
import java.util.NavigableSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Predicate;
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
    public double getTicketsPrice(@Nonnull Event event, @Nonnull User user, @Nonnull Set<Long> seats) {
        Set<Ticket> tickets = seats.stream().map(seat -> createTicket(event, seat)).collect(Collectors.toSet());

        double totalPrize = getTotalPrize(tickets);
        double discount = discountService.getDiscount(user, tickets);

        return applyDiscounts(totalPrize, discount);
    }


    @Override
    public void bookTickets(@Nonnull Set<Ticket> tickets, User user) {
        user.getTickets().addAll(tickets);
        tickets.forEach(setUserToTicket(user));
        tickets.forEach(ticket -> ticketService.save(ticket));
        userService.save(user);
    }


    @Nonnull
    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime) {
        return ticketService.getPurchasedTicketsForEvent(event, dateTime);
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

    private double applyDiscounts(double totalPrize, double discount) {
        double finalPrize;
        if (discount != 0) {
            finalPrize = totalPrize - ((totalPrize / 100) * discount);
        } else {
            finalPrize = totalPrize;
        }

        return finalPrize;
    }


    private double getTotalPrize(Set<Ticket> tickets) {
        return tickets.stream().mapToDouble(Ticket::getBasePrice).sum();
    }

    private Consumer<Ticket> setUserToTicket(User user) {
        return ticket -> ticket.setUser(user);
    }

    public void setDiscountService(DiscountService discountService) {
        this.discountService = discountService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }


}
