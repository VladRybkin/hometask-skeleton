package ua.training.spring.hometask.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.EventRating;
import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.service.BookingService;
import ua.training.spring.hometask.service.DiscountService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.Set;

@Service
public class DefaultBookingService implements BookingService {

    @Autowired
    DiscountService discountService;

    @Override
    public double getTicketsPrice(@Nonnull Event event, @Nonnull LocalDateTime dateTime, @Nullable User user, @Nonnull Set<Long> seats) {
        double ratingBonus = getBonusForEventRating(event.getRating());
        int ticketsAmount=user.getTickets().size();
        double totalPrize = getTotalPrize(event.getBasePrice(), ratingBonus, ticketsAmount);
        double discount = discountService.getDiscount(user, event, dateTime, ticketsAmount);
        double finalPrize = applyDiscounts(totalPrize, discount);

        return finalPrize;
    }


    @Override
    public void bookTickets(@Nonnull Set<Ticket> tickets) {

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

    private double getTotalPrize(double basePrice, double ratingBonus, double size) {
        return (basePrice * ratingBonus) * size;
    }


}
