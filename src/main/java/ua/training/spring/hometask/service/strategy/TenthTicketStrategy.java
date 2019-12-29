package ua.training.spring.hometask.service.strategy;

import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.User;

public class TenthTicketStrategy implements DiscountStrategy {

    @Override
    public int calculateDiscount(User user, Event event) {
        int discount = 0;
        int ticketCount = user.getTickets().size();
        for (int i = 0; i < ticketCount; i++) {
            if (i % 10 == 0) {
                discount = discount + 5;
            }
        }

        return discount;
    }
}
