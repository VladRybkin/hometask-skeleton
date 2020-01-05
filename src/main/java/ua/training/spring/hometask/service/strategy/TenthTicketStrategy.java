package ua.training.spring.hometask.service.strategy;


import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.domain.User;

import java.util.ArrayList;
import java.util.List;


public class TenthTicketStrategy implements DiscountStrategy {

    private final static int TenthTicketDiscount=50;

    @Override
    public double calculateDiscount(User user) {

        List<Ticket> userTickets = new ArrayList<>(user.getTickets());
        double totalPrize = userTickets.stream().mapToDouble(Ticket::getBasePrice).sum();
        applyDiscounts(userTickets);
        double totalPrizeWithDiscount = userTickets.stream().mapToDouble(Ticket::getBasePrice).sum();

        return calculatePercentDifference(totalPrize, totalPrizeWithDiscount);
    }

    private void applyDiscounts(List<Ticket> userTickets) {
        int ticketCount = userTickets.size();

        if (ticketCount >= 10) {

            for (int i = 0; i < ticketCount; i++) {
                if (i % 10 == 0) {
                    userTickets.get(i).setBasePrice(calculateFiftyPercentDiscount(userTickets.get(i)));
                }
            }
        }
    }

    private double calculateFiftyPercentDiscount(Ticket ticket) {
        double ticketPrize = ticket.getBasePrice();
        double discount = ticketPrize != 0 ? (ticketPrize / 100) * TenthTicketDiscount : 0;
        double finalPrize = ticketPrize - discount;

        return finalPrize;
    }

    private double calculatePercentDifference(double totalPrize, double totalPrizeWithDiscount) {
        if (totalPrize > totalPrizeWithDiscount) {
            return ((totalPrize - totalPrizeWithDiscount) / totalPrize) * 100;
        } else {
            return 0;
        }

    }
}
