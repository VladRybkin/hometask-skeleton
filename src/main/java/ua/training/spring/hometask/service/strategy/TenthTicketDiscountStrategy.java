package ua.training.spring.hometask.service.strategy;


import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.domain.User;

import java.util.List;
import java.util.Set;


public class TenthTicketDiscountStrategy implements DiscountStrategy {

    @Value("${tenthTicket.discount}")
    private int tenthTicketDiscount;

    @Override
    public double calculateDiscount(User user, Set<Ticket> tickets) {

        double totalPrice = tickets.stream().mapToDouble(Ticket::getBasePrice).sum();
        applyDiscounts(tickets);
        double totalPriceWithDiscount = tickets.stream().mapToDouble(Ticket::getBasePrice).sum();

        return calculatePercentDifference(totalPrice, totalPriceWithDiscount);
    }

    private void applyDiscounts(Set<Ticket> userTickets) {
        List<Ticket> ticketsList = Lists.newArrayList(userTickets);
        int ticketCount = userTickets.size();

        if (ticketCount >= 10) {
            for (int i = 0; i < ticketCount; i++) {
                if (i % 10 == 0) {
                    ticketsList.get(i).setBasePrice(calculateFiftyPercentDiscount(ticketsList.get(i)));
                }
            }
        }
    }

    private double calculateFiftyPercentDiscount(Ticket ticket) {
        double ticketPrice = ticket.getBasePrice();
        double discount = ticketPrice != 0 ? (ticketPrice / 100) * tenthTicketDiscount : 0;

        return ticketPrice - discount;
    }

    private double calculatePercentDifference(double totalPrice, double totalPriceWithDiscount) {
        double percentDifference = 0;
        if (totalPrice > totalPriceWithDiscount) {
            percentDifference = ((totalPrice - totalPriceWithDiscount) / totalPrice) * 100;
        }

        return percentDifference;
    }

    public void setTenthTicketDiscount(int tenthTicketDiscount) {
        this.tenthTicketDiscount = tenthTicketDiscount;
    }
}
