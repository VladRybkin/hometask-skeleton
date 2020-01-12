package ua.training.spring.hometask.service.impl;


import com.google.common.collect.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.EventRating;
import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.service.DiscountService;
import ua.training.spring.hometask.service.strategy.BirthdayDiscountStrategy;
import ua.training.spring.hometask.service.strategy.DiscountStrategy;
import ua.training.spring.hometask.service.strategy.TenthTicketDiscountStrategy;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


class DefaultDiscountServiceTest {


    private DiscountStrategy birthdayStrategy = new BirthdayDiscountStrategy();

    private DiscountStrategy tenthTicketStrategy = new TenthTicketDiscountStrategy();

    private DiscountService discountService;

    private Event testEvent;

    private static final double BIRTHDAY_DISCOUNT = 10;

    private static final double TENTH_TICKET_DISCOUNT = 5;

    private static final int ENOUGH_FOR_TENTH_TICKET_STRATEGY_AMOUNT = 20;

    private static final int NOT_ENOUGH_FOR_TENTH_TICKET_STRATEGY_AMOUNT = 9;

    private static final int TENTH_TICKET_STRATEGY_DISCOUNT_VALUE = 50;

    private static final int BIRTHDAY_STRATEGY_DISCOUNT_VALUE = 10;

    private static final double ZERO_DISCOUNT = 0;


    @BeforeEach
    void setUp() {

        ((TenthTicketDiscountStrategy) tenthTicketStrategy).setTenthTicketDiscount(TENTH_TICKET_STRATEGY_DISCOUNT_VALUE);
        ((BirthdayDiscountStrategy) birthdayStrategy).setBirthdayDiscount(BIRTHDAY_STRATEGY_DISCOUNT_VALUE);

        testEvent = buildTestEvent(EventRating.LOW);

        Set<DiscountStrategy> discountStrategies = Sets.newHashSet(birthdayStrategy, tenthTicketStrategy);

        discountService = new DefaultDiscountService(discountStrategies);
    }


    @Test
    void shouldChooseBirthdayStrategyDiscount() {
        User user = new User();
        user.setDateOfBirth(LocalDateTime.now());
        assertThat(discountService.getDiscount(user, user.getTickets()), is(BIRTHDAY_DISCOUNT));
    }

    @Test
    void shouldChooseTenthTicketStrategyDiscount() {
        User user = new User();
        addTickets(ENOUGH_FOR_TENTH_TICKET_STRATEGY_AMOUNT, user);
        assertThat(discountService.getDiscount(user, user.getTickets()), is(TENTH_TICKET_DISCOUNT));
    }


    @Test
    void shouldReturnZeroDiscountAsNotMatchAnyStrategy() {
        User user = new User();
        user.setDateOfBirth(LocalDateTime.now().minusDays(10));
        addTickets(NOT_ENOUGH_FOR_TENTH_TICKET_STRATEGY_AMOUNT, user);
        assertThat(discountService.getDiscount(user, user.getTickets()), is(ZERO_DISCOUNT));
    }

    @Test
    void shouldReturnZeroDiscountIfBirthdayIsNull() {
        User user = new User();
        assertNull(user.getDateOfBirth());
        assertThat(discountService.getDiscount(user, user.getTickets()), is(ZERO_DISCOUNT));
    }

    @Test
    void shouldChooseBirthdayAsHigherDiscountStrategy() {
        User user = new User();
        user.setDateOfBirth(LocalDateTime.now());
        addTickets(ENOUGH_FOR_TENTH_TICKET_STRATEGY_AMOUNT, user);
        assertThat(discountService.getDiscount(user, user.getTickets()), is(BIRTHDAY_DISCOUNT));
    }

    @Test
    void shouldReturnZeroDiscountAsUserHasNoTickets() {
        User user = new User();
        user.setTickets(new TreeSet<>());
        assertThat(user.getTickets().size(), is(Collections.emptyList().size()));
        assertThat(discountService.getDiscount(user, user.getTickets()), is(ZERO_DISCOUNT));
    }


    private Event buildTestEvent(EventRating eventRating) {
        Event event = new Event();
        double eventBasePrice=100;
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


    private void addTickets(int amount, User user) {
        Set<Ticket> tickets = Sets.newHashSet();
        IntStream.rangeClosed(1, amount).forEach(addTicket(user, tickets));
        user.getTickets().addAll(tickets);

    }

    private IntConsumer addTicket(User user, Set<Ticket> tickets) {
        return i -> tickets.add(new Ticket(user, testEvent, testEvent.getAirDates().first(), i, 100));
    }
}