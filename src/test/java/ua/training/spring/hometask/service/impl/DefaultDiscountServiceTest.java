package ua.training.spring.hometask.service.impl;


import com.google.common.collect.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.service.DiscountService;
import ua.training.spring.hometask.service.strategy.BirthdayDiscountStrategy;
import ua.training.spring.hometask.service.strategy.DiscountStrategy;
import ua.training.spring.hometask.service.strategy.TenthTicketStrategy;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


class DefaultDiscountServiceTest {

    private DiscountService discountService;

    private DiscountStrategy birthdayStrategy;

    private DiscountStrategy tenthTicketStrategy;

    private Event testEvent;

    private static final double BIRTHDAY_DISCOUNT = 10;

    private static final double TENTH_TICKET_DISCOUNT = 5;

    private static final int FOR_TENTH_TICKET_STRATEGY_AMOUNT = 20;

    private static final int NOT_ENOUGH_FOR_TENTH_TICKET_STRATEGY_AMOUNT = 9;

    private static final int TENTH_TICKET_STRATEGY_DISCOUNT_VALUE = 50;

    private static final int BIRTHDAY_STRATEGY_DISCOUNT_VALUE = 10;

    private static final double ZERO_DISCOUNT = 0;




    @BeforeEach
    void setUp() {

        birthdayStrategy = new BirthdayDiscountStrategy();
        tenthTicketStrategy = new TenthTicketStrategy();

        ((TenthTicketStrategy) tenthTicketStrategy).setTenthTicketDiscount(TENTH_TICKET_STRATEGY_DISCOUNT_VALUE);
        ((BirthdayDiscountStrategy) birthdayStrategy).setBirthdayDiscount(BIRTHDAY_STRATEGY_DISCOUNT_VALUE);

        testEvent = new Event("testname");

        Set<DiscountStrategy> discountStrategies = Sets.newHashSet(birthdayStrategy, tenthTicketStrategy);

        discountService = new DefaultDiscountService(discountStrategies);


    }

    @Test
    void shouldChooseBirthdayStrategyDiscount() {
        User user = new User();
        user.setDateOfBirth(LocalDateTime.now());
        assertEquals(discountService.getDiscount(user), BIRTHDAY_DISCOUNT);

    }

    @Test
    void shouldChooseTenthTicketStrategy() {
        User user = new User();
        addTickets(FOR_TENTH_TICKET_STRATEGY_AMOUNT, user);
        assertEquals(discountService.getDiscount(user), TENTH_TICKET_DISCOUNT);

    }

    @Test
    void shouldReturnZeroDiscount() {
        User user = new User();
        addTickets(NOT_ENOUGH_FOR_TENTH_TICKET_STRATEGY_AMOUNT, user);
        assertEquals(discountService.getDiscount(user), ZERO_DISCOUNT);

    }

    @Test
    void shouldChooseHigherDiscountStrategy() {
        User user = new User();
        user.setDateOfBirth(LocalDateTime.now());
        addTickets(FOR_TENTH_TICKET_STRATEGY_AMOUNT, user);
        assertEquals(discountService.getDiscount(user), BIRTHDAY_DISCOUNT);

    }


    private void addTickets(int amount, User user) {
        Set<Ticket> tickets = Sets.newTreeSet();
        for (int i = 1; i <= amount; i++) {
            tickets.add(new Ticket(user, testEvent, LocalDateTime.now(), i, 100));
        }
        user.getTickets().addAll(tickets);

    }
}