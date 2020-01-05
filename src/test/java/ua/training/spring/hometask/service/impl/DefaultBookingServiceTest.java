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
    public void getTicketsPrice() {

    }

    @Test
    public void bookTickets() {
    }

    @Test
    public void getPurchasedTicketsForEvent() {
    }
}