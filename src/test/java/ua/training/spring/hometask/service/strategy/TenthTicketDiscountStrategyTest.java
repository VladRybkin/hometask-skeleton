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

import java.util.Set;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class TenthTicketDiscountStrategyTest {


    private DiscountStrategy discountStrategy;


    private Event testEvent;

    private static final int TICKET_BASE_PRICE = 100;

    private static final int TENTH_TICKET_DISCOUNT = 50;


    @BeforeEach()
    void setUp() {
        testEvent=buildTestEvent();
        discountStrategy = new TenthTicketDiscountStrategy();
        ((TenthTicketDiscountStrategy) discountStrategy).setTenthTicketDiscount(TENTH_TICKET_DISCOUNT);

    }

    @Test
    void calculateDiscountWithTenTickets() {
        User user = new User();
        int ticketAmount = 10;
        double expected_discountForAtLeastTenthTickets = 5;
        addTickets(ticketAmount, user);

        assertThat(user.getTickets().size(), is(ticketAmount));
        assertThat(discountStrategy.calculateDiscount(user, user.getTickets()), is(expected_discountForAtLeastTenthTickets));

    }

    @Test
    void calculateDiscountWithManyTickets() {
        User user = new User();
        int ticketAmount = 100;
        double expected_discountForAtHundredTickets = 5;
        addTickets(ticketAmount, user);

        assertThat(user.getTickets().size(), is(ticketAmount));
        assertThat(discountStrategy.calculateDiscount(user, user.getTickets()), is(expected_discountForAtHundredTickets));

    }

    @Test
    void calculateDiscountWitZeroTickers() {
        User user = new User();
        int ticketAmount = 0;
        double expected_discountForAtLeastTenthTickets = 0;
        assertThat(user.getTickets().size(), is(ticketAmount));
        assertThat(discountStrategy.calculateDiscount(user, user.getTickets()), is(expected_discountForAtLeastTenthTickets));
    }

    private Event buildTestEvent() {
        Event event = new Event();
        event.setName("testEvent");
        Set<LocalDateTime> airDates = Sets.newTreeSet();
        LocalDateTime firstDate = LocalDateTime.now().plusMonths(1).plusDays(4);
        LocalDateTime secondDate = LocalDateTime.now().plusMonths(2).plusDays(5);
        airDates.add(firstDate);
        airDates.add(secondDate);
        event.getAirDates().addAll(airDates);
        return event;
    }

    private void addTickets(int amount, User user) {
        Set<Ticket> tickets = Sets.newHashSet();
        IntStream.rangeClosed(1, amount).forEach(addTicket(user, tickets));
        user.getTickets().addAll(tickets);

    }

    private IntConsumer addTicket(User user, Set<Ticket> tickets) {
        return i -> tickets.add(new Ticket(user, testEvent, testEvent.getAirDates().first(), i, TICKET_BASE_PRICE));
    }
}
