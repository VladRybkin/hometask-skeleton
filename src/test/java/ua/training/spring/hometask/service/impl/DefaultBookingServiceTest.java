package ua.training.spring.hometask.service.impl;


import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.training.spring.hometask.domain.*;
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


@ExtendWith(MockitoExtension.class)
class DefaultBookingServiceTest {

    private DefaultBookingService bookingService;

    private Event testLowRatingEvent;

    private Event testMidRatingEvent;

    private Event testHighRatingEvent;

    private static final int BIRTHDAY_STRATEGY_DISCOUNT = 10;

    private static final int TENTH_TICKET_STRATEGY_DISCOUNT = 50;

    private static final double TICKET_BASE_PRICE = 100;


    @BeforeEach()
    void setUp() {
        DiscountStrategy birthdayStrategy = buildBirthdayTicketStrategy(BIRTHDAY_STRATEGY_DISCOUNT);
        DiscountStrategy tenthTicketStrategy = buildTenthTicketStrategy(TENTH_TICKET_STRATEGY_DISCOUNT);

        DiscountService discountService = new DefaultDiscountService(Sets.newHashSet(birthdayStrategy, tenthTicketStrategy));

        bookingService = new DefaultBookingService();
        bookingService.setDiscountService(discountService);

        testLowRatingEvent = buildTestEvent(EventRating.LOW);
        testMidRatingEvent = buildTestEvent(EventRating.MID);
        testHighRatingEvent = buildTestEvent(EventRating.HIGH);

    }

    @Test
    void shouldCalculateTotalPrizeWithoutDiscount() {
        Set<Long> seats = Sets.newHashSet(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L);
        double expectedPriceWithoutDiscount = 900;
        User testUserWithTenTickets = buildUserWithTicketAmountWithOneHundredPrice(10);
        double price = bookingService.getTicketsPrice(testLowRatingEvent, testUserWithTenTickets, seats);
        assertThat(price, is(expectedPriceWithoutDiscount));

    }

    @Test
    void shouldCalculateTotalPrizeWithTenTicketsDiscount() {
        Set<Long> seats = Sets.newHashSet(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L);
        double expectedPriceWithTenthTicketsDiscount = 950;
        User user = buildUserWithTicketAmountWithOneHundredPrice(10);
        double price = bookingService.getTicketsPrice(testLowRatingEvent, user, seats);
        assertThat(price, is(expectedPriceWithTenthTicketsDiscount));
    }

    @Test
    void shouldCalculateZeroTotalPriceForZeroTicketsInParameter() {
        Set<Long> seats = Sets.newHashSet();
        double expectedPriceWithTenthTicketsDiscount = 0;
        User user = buildUserWithTicketAmountWithOneHundredPrice(10);
        double price = bookingService.getTicketsPrice(testLowRatingEvent, user, seats);
        assertThat(price, is(expectedPriceWithTenthTicketsDiscount));
    }


    @Test
    void shouldCalculateZeroTotalPriceForUserWithoutTickets() {
        Set<Long> seats = Sets.newHashSet();
        User user = buildUserWithTicketAmountWithOneHundredPrice(0);
        double expectedPriceWithTenthTicketsDiscount = 0;
        double price = bookingService.getTicketsPrice(testLowRatingEvent, user, seats);
        assertThat(price, is(expectedPriceWithTenthTicketsDiscount));
    }


    @Test
    void shouldCalculateZeroTotalPriceForUserWithBirthdayWithoutTickets() {
        Set<Long> seats = Sets.newHashSet();
        User user = buildUserWithTicketAmountWithOneHundredPrice(0);
        user.setDateOfBirth(LocalDateTime.now());
        double expectedPriceWithTenthTicketsDiscount = 0;
        double price = bookingService.getTicketsPrice(testLowRatingEvent, user, seats);
        assertThat(price, is(expectedPriceWithTenthTicketsDiscount));
    }


    private Event buildTestEvent(EventRating eventRating) {
        Event event = new Event();
        event.setName("testEvent");
        event.setRating(eventRating);
        event.setBasePrice(TICKET_BASE_PRICE);
        Set<LocalDateTime> airDates = Sets.newTreeSet();
        LocalDateTime firstDate = LocalDateTime.now().plusMonths(1).plusDays(4);
        LocalDateTime secondDate = LocalDateTime.now().plusMonths(2).plusDays(5);
        airDates.add(firstDate);
        airDates.add(secondDate);
        event.getAirDates().addAll(airDates);
        return event;
    }

    private Map<LocalDateTime, Auditorium> initAuditoriums() {

        Auditorium auditorium1 = new Auditorium();
        auditorium1.setName("auditorium1");
        auditorium1.setNumberOfSeats(60);
        auditorium1.setVipSeats(Sets.newHashSet("1", "2", "3"));

        Auditorium auditorium2 = new Auditorium();
        auditorium2.setName("auditorium2");
        auditorium2.setNumberOfSeats(50);
        auditorium2.setVipSeats(Sets.newHashSet("4", "5", "6"));
        Map<LocalDateTime, Auditorium> auditoriumMap = Maps.newTreeMap();
        NavigableSet<LocalDateTime> airDates = initLocalDateTimes();
        airDates.forEach(airDate -> auditoriumMap.put(airDate, auditorium1));
        airDates.forEach(airDate -> auditoriumMap.put(airDate, auditorium2));


        return auditoriumMap;

    }

    private NavigableSet<LocalDateTime> initLocalDateTimes() {
        LocalDateTime nextMonth = LocalDateTime.now().plusMonths(1);
        LocalDateTime fiveDaysLater = LocalDateTime.now().plusDays(5);
        Set<LocalDateTime> localDateTimes = Sets.newHashSet(nextMonth, fiveDaysLater);

        return Sets.newTreeSet(localDateTimes);

    }

    private User buildUserWithTicketAmountWithOneHundredPrice(int amount) {
        User user = new User();
        user.setFirstName("TestUser");
        addTickets(amount, user, testLowRatingEvent);

        return user;
    }


    private void addTickets(int amount, User user, Event event) {
        Set<Ticket> tickets = Sets.newHashSet();
        IntStream.rangeClosed(1, amount).forEach(addTicket(user, tickets, event));
        user.getTickets().addAll(tickets);

    }

    private IntConsumer addTicket(User user, Set<Ticket> tickets, Event event) {
        return seat -> tickets.add(new Ticket(user, event, event.getAirDates().first(), seat, TICKET_BASE_PRICE));
    }


    private DiscountStrategy buildBirthdayTicketStrategy(int discount) {
        BirthdayDiscountStrategy discountStrategy = new BirthdayDiscountStrategy();
        discountStrategy.setBirthdayDiscount(discount);
        return discountStrategy;
    }

    private DiscountStrategy buildTenthTicketStrategy(int discount) {
        TenthTicketDiscountStrategy discountStrategy = new TenthTicketDiscountStrategy();
        discountStrategy.setTenthTicketDiscount(discount);
        return discountStrategy;
    }
}