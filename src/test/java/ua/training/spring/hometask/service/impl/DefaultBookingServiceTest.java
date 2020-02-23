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
import ua.training.spring.hometask.service.TicketService;
import ua.training.spring.hometask.service.UserService;

import java.time.LocalDateTime;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class DefaultBookingServiceTest {

    private static final double TICKET_BASE_PRICE = 100;

    @InjectMocks
    private DefaultBookingService bookingService;

    @Mock
    private DefaultDiscountService discountService;

    @Mock
    private UserService userService;

    @Mock
    private TicketService ticketService;

    private Event testLowRatingEvent;


    @BeforeEach()
    void setUp() {
        testLowRatingEvent = buildTestEvent(EventRating.LOW);
    }

    @Test
    void shouldCalculateTotalPriсeWithoutDiscount() {
        Set<Long> seats = Sets.newHashSet(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L);
        double expectedPriceWithoutDiscount = 900;
        double expectedDiscountWithTenthTickets = 0;
        User testUser = buildUser();

        when(discountService.getDiscount(any(User.class), anySet())).thenReturn(expectedDiscountWithTenthTickets);
        double price = bookingService.getTicketsPrice(testLowRatingEvent, testUser, seats);
        assertThat(price, is(expectedPriceWithoutDiscount));
        verify(discountService).getDiscount(any(User.class), anySet());
    }

    @Test
    void shouldCalculateTotalPriсeWithTenTicketsDiscount() {
        Set<Long> seats = Sets.newHashSet(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L);
        double expectedPriceWithTenthTicketsDiscount = 950;
        double expectedDiscountWithTenthTickets = 5;
        User user = buildUser();

        when(discountService.getDiscount(any(User.class), anySet())).thenReturn(expectedDiscountWithTenthTickets);
        double price = bookingService.getTicketsPrice(testLowRatingEvent, user, seats);
        assertThat(price, is(expectedPriceWithTenthTicketsDiscount));
        verify(discountService).getDiscount(any(User.class), anySet());
    }

    @Test
    void shouldCalculateZeroTotalPriceForZeroTicketsInParameter() {
        Set<Long> seats = Sets.newHashSet();
        double expectedPriceWithTenthTicketsDiscount = 0;
        double expectedDiscountWithTenthTickets = 0;
        User user = buildUser();

        when(discountService.getDiscount(any(User.class), anySet())).thenReturn(expectedDiscountWithTenthTickets);
        double price = bookingService.getTicketsPrice(testLowRatingEvent, user, seats);
        assertThat(price, is(expectedPriceWithTenthTicketsDiscount));
        verify(discountService).getDiscount(any(User.class), anySet());
    }


    @Test
    void shouldCalculateTotalPriceForUserWithBirthday() {
        Set<Long> seats = Sets.newHashSet(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L);
        User user = buildUser();
        user.setDateOfBirth(LocalDateTime.now());
        double expectedPriceWithTenthTicketsDiscount = 900;
        double expectedDiscountWithBirthday = 10;

        when(discountService.getDiscount(any(User.class), anySet())).thenReturn(expectedDiscountWithBirthday);
        double price = bookingService.getTicketsPrice(testLowRatingEvent, user, seats);
        assertThat(price, is(expectedPriceWithTenthTicketsDiscount));
        verify(discountService).getDiscount(any(User.class), anySet());
    }

    @Test
    void bookTicket() {
        User user = buildUser();
        Ticket ticket = buildTicket();
        bookingService.bookTicket(ticket, user);

        assertThat(ticket.getUser(), is(user));
        assertThat(user.getTickets(), containsInAnyOrder(ticket));
        verify(ticketService).save(ticket);
        verify(userService).save(user);
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

    private User buildUser() {
        User user = new User();
        user.setFirstName("TestUser");
        return user;
    }

    private Ticket buildTicket() {
        Ticket ticket = new Ticket();
        ticket.setBasePrice(100);
        ticket.setEvent(testLowRatingEvent);
        ticket.setDateTime(LocalDateTime.now());
        ticket.setSeat(1);
        return ticket;
    }
}