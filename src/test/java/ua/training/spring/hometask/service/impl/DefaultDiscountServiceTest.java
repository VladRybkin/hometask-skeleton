package ua.training.spring.hometask.service.impl;


import com.google.common.collect.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.EventRating;
import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.service.strategy.BirthdayDiscountStrategy;
import ua.training.spring.hometask.service.strategy.DiscountStrategy;
import ua.training.spring.hometask.service.strategy.TenthTicketDiscountStrategy;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DefaultDiscountServiceTest {

    @InjectMocks
    private DefaultDiscountService discountService;

    @Mock
    private BirthdayDiscountStrategy birthdayStrategy;

    @Mock
    private TenthTicketDiscountStrategy tenthTicketStrategy;

    private Event testEvent;

    private static final double BIRTHDAY_DISCOUNT = 10;

    private static final double TENTH_TICKET_DISCOUNT = 5;

    private static final int ENOUGH_FOR_TENTH_TICKET_STRATEGY_AMOUNT = 20;

    private static final int NOT_ENOUGH_FOR_TENTH_TICKET_STRATEGY_AMOUNT = 9;

    private static final double ZERO_DISCOUNT = 0;


    @BeforeEach
    public void setUp() {
        testEvent = buildTestEvent(EventRating.LOW);
        Set<DiscountStrategy> discountStrategies = Sets.newHashSet(birthdayStrategy, tenthTicketStrategy);
        discountService = new DefaultDiscountService(discountStrategies);
    }

    @Test
    public void shouldChooseBirthdayStrategyDiscount() {
        User user = new User();
        user.setDateOfBirth(LocalDateTime.now());
        Set<Ticket> tickets = buildTickets(ENOUGH_FOR_TENTH_TICKET_STRATEGY_AMOUNT);
        when(birthdayStrategy.calculateDiscount(user, tickets)).thenReturn(BIRTHDAY_DISCOUNT);
        when(tenthTicketStrategy.calculateDiscount(user, tickets)).thenReturn(TENTH_TICKET_DISCOUNT);
        assertThat(discountService.getDiscount(user, tickets), is(BIRTHDAY_DISCOUNT));
        verify(birthdayStrategy).calculateDiscount(user, tickets);
        verify(tenthTicketStrategy).calculateDiscount(user, tickets);
    }

    @Test
    public void shouldChooseTenthTicketStrategyDiscount() {
        User user = new User();
        Set<Ticket> tickets = buildTickets(ENOUGH_FOR_TENTH_TICKET_STRATEGY_AMOUNT);
        when(birthdayStrategy.calculateDiscount(user, tickets)).thenReturn(ZERO_DISCOUNT);
        when(tenthTicketStrategy.calculateDiscount(user, tickets)).thenReturn(TENTH_TICKET_DISCOUNT);
        assertThat(discountService.getDiscount(user, tickets), is(TENTH_TICKET_DISCOUNT));
        verify(birthdayStrategy).calculateDiscount(user, tickets);
        verify(tenthTicketStrategy).calculateDiscount(user, tickets);
    }


    @Test
    public void shouldReturnZeroDiscountAsNotMatchAnyStrategy() {
        User user = new User();
        Set<Ticket> tickets = buildTickets(NOT_ENOUGH_FOR_TENTH_TICKET_STRATEGY_AMOUNT);
        when(birthdayStrategy.calculateDiscount(user, tickets)).thenReturn(ZERO_DISCOUNT);
        when(tenthTicketStrategy.calculateDiscount(user, tickets)).thenReturn(ZERO_DISCOUNT);
        assertThat(discountService.getDiscount(user, tickets), is(ZERO_DISCOUNT));
        verify(birthdayStrategy).calculateDiscount(user, tickets);
        verify(tenthTicketStrategy).calculateDiscount(user, tickets);
    }


    private Event buildTestEvent(EventRating eventRating) {
        Event event = new Event();
        double eventBasePrice = 100;
        event.setName("testEvent");
        event.setRating(eventRating);
        event.setBasePrice(eventBasePrice);
        Set<LocalDateTime> airDates = Sets.newTreeSet();
        LocalDateTime firstDate = LocalDateTime.now().plusMonths(1).plusDays(4);
        LocalDateTime secondDate = LocalDateTime.now().plusMonths(2).plusDays(5);
        airDates.add(firstDate);
        airDates.add(secondDate);
        event.getAirDates().addAll(airDates);
        return event;
    }


    private Set<Ticket> buildTickets(int amount) {
        Set<Ticket> tickets = Sets.newTreeSet();
        IntStream.rangeClosed(1, amount).forEach(addTicket(tickets));
        return tickets;

    }

    private IntConsumer addTicket(Set<Ticket> tickets) {
        return i -> tickets.add(new Ticket(null, testEvent, testEvent.getAirDates().first(), i, 100));
    }
}