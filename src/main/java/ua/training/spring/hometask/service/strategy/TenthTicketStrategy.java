package ua.training.spring.hometask.service.strategy;


import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableSet;


public class TenthTicketStrategy implements DiscountStrategy {

    @Value("${tenthTicket.discount}")
    private int tenthTicketDiscount;


    @Override
    public double calculateDiscount(User user,NavigableSet<Ticket> tickets) {


        double totalPrize = tickets.stream().mapToDouble(Ticket::getBasePrice).sum();
        applyDiscounts(tickets);
        double totalPrizeWithDiscount = tickets.stream().mapToDouble(Ticket::getBasePrice).sum();

        return calculatePercentDifference(totalPrize, totalPrizeWithDiscount);
    }

    private void applyDiscounts(NavigableSet<Ticket> userTickets) {
        List<Ticket>ticketsList= Lists.newArrayList(userTickets);
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
        if (totalPrize > totalPrizeWithDiscount) {
            return ((totalPrize - totalPrizeWithDiscount) / totalPrize) * 100;
        } else {
            return 0;
        }

    }

    public int getTenthTicketDiscount() {
        return tenthTicketDiscount;
    }

    public void setTenthTicketDiscount(int tenthTicketDiscount) {
        this.tenthTicketDiscount = tenthTicketDiscount;
    }
}
