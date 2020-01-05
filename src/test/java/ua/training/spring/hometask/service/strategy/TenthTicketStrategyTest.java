package ua.training.spring.hometask.service.strategy;


import com.google.common.collect.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.domain.User;

import java.time.LocalDateTime;

import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class TenthTicketStrategyTest {


    private DiscountStrategy discountStrategy;

    private User testUserWithTenTickets;

    private User testUserWithoutTenTickets;

    private Event testEvent;

    private NavigableSet<Ticket> tickets;

    private static final double TENTH_TICKET_DISCOUNT = 5;

    private static final Integer ZERO_DISCOUNT = 0;


    @BeforeEach()
    void setUp() {
        discountStrategy = new TenthTicketStrategy();
        testUserWithTenTickets = new User();
        testUserWithoutTenTickets = new User();
        testEvent = new Event("testname");
        tickets = new TreeSet<>();

        addTickets(10, testUserWithTenTickets);


    }

    @Test
    void calculateDiscountWithTenTickets() {
        assertEquals(testUserWithTenTickets.getTickets().size(), 10);
        assertEquals(discountStrategy.calculateDiscount(testUserWithTenTickets), TENTH_TICKET_DISCOUNT);
    }

    @Test
    void calculateDiscountWithoutTenTickers() {
        assertEquals(testUserWithoutTenTickets.getTickets().size(), ZERO_DISCOUNT);
        assertEquals(testUserWithoutTenTickets.getTickets().size(), ZERO_DISCOUNT);
    }

    private void addTickets(int amount, User user) {
        Set<Ticket> tickets = Sets.newTreeSet();
        for (int i = 1; i <= amount; i++) {
            tickets.add(new Ticket(user, testEvent, LocalDateTime.now(), i, 100));
        }
        user.getTickets().addAll(tickets);

    }
}
