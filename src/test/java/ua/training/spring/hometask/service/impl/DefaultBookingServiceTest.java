package ua.training.spring.hometask.service.impl;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.training.spring.hometask.service.BookingService;


@ExtendWith(MockitoExtension.class)
class DefaultBookingServiceTest {

    BookingService bookingService;


    @BeforeEach()
    void setUp() {
        bookingService = new DefaultBookingService();
    }

    @Test
    void getTicketsPrice() {

    }

    @Test
    void bookTickets() {
    }

    @Test
    void getPurchasedTicketsForEvent() {
    }
}