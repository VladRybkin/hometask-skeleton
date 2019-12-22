package ua.training.spring.hometask.service.strategy;

import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.domain.User;

public class TenthTicketStrategy implements DiscountStrategy {

    @Override
    public int calculateDiscount(User user) {
        int discount = 0;
        int ticketCount = user.getTickets().size();

        return 10;
    }
}
