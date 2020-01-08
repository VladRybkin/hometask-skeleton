package ua.training.spring.hometask.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.EventRating;
import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.service.BookingService;
import ua.training.spring.hometask.service.DiscountService;
import ua.training.spring.hometask.service.UserService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class DefaultBookingService implements BookingService {

    @Autowired
    DiscountService discountService;

    @Autowired
    UserService userService;


    @Override
    public double getTicketsPrice(@Nonnull Event event, @Nullable User user, @Nonnull Set<Long> seats) {
        NavigableSet<Ticket> filteredTickets = user.getTickets()
                .stream()
                .filter(eventFilter(event)
                        .and(seatsFilter(seats)))
                .collect(Collectors.toCollection(TreeSet::new));

        double totalPrize = getTotalPrize(filteredTickets);
        double discount = discountService.getDiscount(user, filteredTickets);

        return applyDiscounts(totalPrize, discount);
    }


    @Override
    public void bookTickets(@Nonnull Set<Ticket> tickets, User user) {
        user.getTickets().addAll(tickets);
        tickets.forEach(setUserToTicket(user));
        userService.save(user);

    }


    @Nonnull
    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime) {

        return null;
    }

    private double getBonusForEventRating(EventRating eventRating) {
        if (eventRating.equals(EventRating.LOW)) {
            return 1;
        }
        if (eventRating.equals(EventRating.MID)) {
            return 1.5;
        }
        if (eventRating.equals(EventRating.HIGH)) {
            return 2;
        } else {
            return 0;
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


    private double getTotalPrize(NavigableSet<Ticket> tickets) {

        return tickets.stream().mapToDouble(Ticket::getBasePrice).sum();
    }

    private Predicate<Ticket> eventFilter(@Nonnull Event event) {
        return ticket -> ticket.getEvent().equals(event);
    }

    private Predicate<Ticket> seatsFilter(@Nonnull Set<Long> seats) {
        return ticket -> seats.contains(ticket.getSeat());
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
