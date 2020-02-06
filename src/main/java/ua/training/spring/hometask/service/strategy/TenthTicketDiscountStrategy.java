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

        double totalPriсe = tickets.stream().mapToDouble(Ticket::getBasePrice).sum();
        applyDiscounts(tickets);
        double totalPriсeWithDiscount = tickets.stream().mapToDouble(Ticket::getBasePrice).sum();

        return calculatePercentDifference(totalPriсe, totalPriсeWithDiscount);
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
        double ticketPriсe = ticket.getBasePrice();
        double discount = ticketPriсe != 0 ? (ticketPriсe / 100) * tenthTicketDiscount : 0;
        double finalPriсe = ticketPriсe - discount;

        return finalPriсe;
    }

    private double calculatePercentDifference(double totalPriсe, double totalPriсeWithDiscount) {
        double percentDifference = 0;
        if (totalPriсe > totalPriсeWithDiscount) {
            percentDifference = ((totalPriсe - totalPriсeWithDiscount) / totalPriсe) * 100;
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
