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

        double totalPrize = tickets.stream().mapToDouble(Ticket::getBasePrice).sum();
        applyDiscounts(tickets);
        double totalPrizeWithDiscount = tickets.stream().mapToDouble(Ticket::getBasePrice).sum();

        return calculatePercentDifference(totalPrize, totalPrizeWithDiscount);
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
        double ticketPrize = ticket.getBasePrice();
        double discount = ticketPrize != 0 ? (ticketPrize / 100) * tenthTicketDiscount : 0;
        double finalPrize = ticketPrize - discount;

        return finalPrize;
    }

    private double calculatePercentDifference(double totalPrize, double totalPrizeWithDiscount) {
        double percentDifference = 0;
        if (totalPrize > totalPrizeWithDiscount) {
            percentDifference = ((totalPrize - totalPrizeWithDiscount) / totalPrize) * 100;
        }

        return percentDifference;
    }

    public int getTenthTicketDiscount() {
        return tenthTicketDiscount;
    }

    public void setTenthTicketDiscount(int tenthTicketDiscount) {
        this.tenthTicketDiscount = tenthTicketDiscount;
    }
}
