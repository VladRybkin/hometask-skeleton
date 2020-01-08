package ua.training.spring.hometask.service.impl;


import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.training.spring.hometask.domain.*;
import ua.training.spring.hometask.service.BookingService;
import ua.training.spring.hometask.service.DiscountService;
import ua.training.spring.hometask.service.strategy.BirthdayDiscountStrategy;
import ua.training.spring.hometask.service.strategy.DiscountStrategy;
import ua.training.spring.hometask.service.strategy.TenthTicketStrategy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


@ExtendWith(MockitoExtension.class)
class DefaultBookingServiceTest {

    private DefaultBookingService bookingService;

    private Event testEvent;

    private User testUserWithoutTenTickets;

    private Set<DiscountStrategy>strategies;

    private DiscountStrategy birthdayStrategy;

    private DiscountStrategy tenthTicketStrategy;

    private DiscountService discountService;


    @BeforeEach()
    void setUp() {
        birthdayStrategy=buildBirthdayTicketStrategy();
        tenthTicketStrategy=buildTenthTicketStrategy();

        discountService=new DefaultDiscountService(Sets.newHashSet(birthdayStrategy, tenthTicketStrategy));

        bookingService = new DefaultBookingService();
        bookingService.setDiscountService(discountService);

        testEvent = buildTestEventWithHighRating();


    }

    @Test
    void getTicketsPrice() {
        Set<Long> seats = Sets.newHashSet(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L);
        User testUserWithTenTickets = buildUserWithTicketAmount(10);
        System.out.println(bookingService.getTicketsPrice(testEvent, testUserWithTenTickets, seats));

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

    private DiscountStrategy buildBirthdayTicketStrategy(){
        BirthdayDiscountStrategy discountStrategy=new BirthdayDiscountStrategy();
        discountStrategy.setBirthdayDiscount(10);
        return discountStrategy;
    }

    private DiscountStrategy buildTenthTicketStrategy(){
        TenthTicketStrategy discountStrategy=new TenthTicketStrategy();
        discountStrategy.setTenthTicketDiscount(50);
        return discountStrategy;
    }
}