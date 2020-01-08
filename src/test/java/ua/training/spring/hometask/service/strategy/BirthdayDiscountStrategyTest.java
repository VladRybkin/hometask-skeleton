package ua.training.spring.hometask.service.strategy;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;


@ExtendWith(MockitoExtension.class)
class BirthdayDiscountStrategyTest {


    private DiscountStrategy discountStrategy;

    private User testUserWithBirthday;

    private User testUserWithoutBirthday;

    private Event testEvent;

    private static final int BIRTHDAY_DISCOUNT = 10;

    private static final double ZERO_DISCOUNT = 0;


    @BeforeEach()
    void setUp() {
        discountStrategy = new BirthdayDiscountStrategy();
        ((BirthdayDiscountStrategy) discountStrategy).setBirthdayDiscount(BIRTHDAY_DISCOUNT);
        testUserWithBirthday = new User();
        testEvent = new Event();
        testUserWithoutBirthday = new User();
        testUserWithBirthday.setDateOfBirth(LocalDateTime.now());
    }


    @Test
    void ShouldCalculateDiscountForUserWithBirthday() {
        assertEquals(discountStrategy.calculateDiscount(testUserWithBirthday, testUserWithBirthday.getTickets()), BIRTHDAY_DISCOUNT);

    }

    @Test
    void ShouldCalculateDiscountForUserWithoutBirthday() {
        assertEquals(discountStrategy.calculateDiscount(testUserWithoutBirthday, testUserWithoutBirthday.getTickets()), ZERO_DISCOUNT);
    }
}
