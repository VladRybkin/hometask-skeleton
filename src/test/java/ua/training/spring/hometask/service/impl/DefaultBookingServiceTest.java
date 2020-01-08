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

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class DefaultBookingServiceTest {

    private DefaultBookingService bookingService;

    private Event testEvent;


    @BeforeEach()
    void setUp() {
        DiscountStrategy birthdayStrategy = buildBirthdayTicketStrategy(10);
        DiscountStrategy tenthTicketStrategy = buildTenthTicketStrategy(50);

        DiscountService discountService = new DefaultDiscountService(Sets.newHashSet(birthdayStrategy, tenthTicketStrategy));

        bookingService = new DefaultBookingService();
        bookingService.setDiscountService(discountService);

        testEvent = buildTestEventWithHighRating();


    }

    @Test
    void shouldCalculateTotalPrizeWithoutDiscount() {
        Set<Long> seats = Sets.newHashSet(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L);
        double expectedPriceWithoutDiscount = 900;
        User testUserWithTenTickets = buildUserWithTicketAmount(10);
        double price = bookingService.getTicketsPrice(testEvent, testUserWithTenTickets, seats);
        assertEquals(price, expectedPriceWithoutDiscount);

    }

    @Test
    void shouldCalculateTotalPrizeWithTenTicketsDiscount() {
        Set<Long> seats = Sets.newHashSet(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L);
        double expectedPriceWithTenthTicketsDiscount = 950;
        User testUserWithTenTickets = buildUserWithTicketAmount(10);
        double price = bookingService.getTicketsPrice(testEvent, testUserWithTenTickets, seats);
        assertEquals(price, expectedPriceWithTenthTicketsDiscount);
    }

    @Test
    void shouldCalculateZeroTotalPriceForZeroTicketsInParameter() {
        Set<Long> seats = Sets.newHashSet();
        double expectedPriceWithTenthTicketsDiscount = 0;
        User testUserWithTenTickets = buildUserWithTicketAmount(10);
        double price = bookingService.getTicketsPrice(testEvent, testUserWithTenTickets, seats);
        assertEquals(price, expectedPriceWithTenthTicketsDiscount);
    }


    @Test
    void shouldCalculateZeroTotalPriceForUserWithoutTickets() {
        Set<Long> seats = Sets.newHashSet(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L);
        double expectedPriceWithTenthTicketsDiscount = 0;
        User testUserWithTenTickets = buildUserWithTicketAmount(0);
        double price = bookingService.getTicketsPrice(testEvent, testUserWithTenTickets, seats);
        assertEquals(price, expectedPriceWithTenthTicketsDiscount);
    }


    @Test
    void bookTickets() {

    }

    @Test
    void getPurchasedTicketsForEvent() {
    }


    private Event buildTestEventWithHighRating() {
        Event event = new Event();
        NavigableSet<LocalDateTime> airDates = initLocalDateTimes();
        NavigableMap<LocalDateTime, Auditorium> auditoriums = new TreeMap<>();

        event.setId(1L);
        event.setName("testEvent");
        event.setBasePrice(100);
        event.setRating(EventRating.HIGH);

        return event;
    }

    private NavigableMap<LocalDateTime, Auditorium> initAuditoriums() {

        Auditorium auditorium1 = new Auditorium();
        auditorium1.setName("auditorium1");
        auditorium1.setNumberOfSeats(60);
        auditorium1.setVipSeats(Sets.newHashSet(1L, 2L, 3L));

        Auditorium auditorium2 = new Auditorium();
        auditorium2.setName("auditorium2");
        auditorium2.setNumberOfSeats(50);
        auditorium2.setVipSeats(Sets.newHashSet(4L, 5L, 6L));
        Map<LocalDateTime, Auditorium> auditoriumMap = Maps.newTreeMap();
        NavigableSet<LocalDateTime> airDates = initLocalDateTimes();
        airDates.forEach(airDate -> auditoriumMap.put(airDate, auditorium1));
        airDates.forEach(airDate -> auditoriumMap.put(airDate, auditorium2));

        return Maps.newTreeMap();

    }

    private NavigableSet<LocalDateTime> initLocalDateTimes() {
        LocalDateTime nextMonth = LocalDateTime.now().plusMonths(1);
        LocalDateTime fiveDaysLater = LocalDateTime.now().plusDays(5);
        Set<LocalDateTime> localDateTimes = Sets.newHashSet(nextMonth, fiveDaysLater);

        return Sets.newTreeSet(localDateTimes);

    }

    private User buildUserWithTicketAmount(int amount) {
        User user = new User();
        user.setFirstName("TestUser");
        LocalDateTime testDate = LocalDateTime.now();
        Set<Ticket> tickets = Sets.newTreeSet();
        for (int i = 1; i <= amount; i++) {
            tickets.add(new Ticket(user, testEvent, testDate, i, 100));
        }
        user.getTickets().addAll(tickets);
        return user;
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