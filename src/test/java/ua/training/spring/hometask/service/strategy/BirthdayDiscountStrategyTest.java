package ua.training.spring.hometask.service.strategy;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.training.spring.hometask.domain.User;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


class BirthdayDiscountStrategyTest {

    private BirthdayDiscountStrategy discountStrategy;

    private User testUserWithBirthday;

    private User testUserWithoutBirthday;

    private static final double BIRTHDAY_DISCOUNT = 10;

    private static final double ZERO_DISCOUNT = 0;

    @BeforeEach()
    void setUp() {
        discountStrategy = new BirthdayDiscountStrategy();
        discountStrategy.setBirthdayDiscount((int) BIRTHDAY_DISCOUNT);
        testUserWithBirthday = new User();
        testUserWithoutBirthday = new User();
        testUserWithBirthday.setDateOfBirth(LocalDateTime.now());
    }

    @Test
    void ShouldCalculateDiscountForUserWithBirthday() {
        assertThat(discountStrategy.calculateDiscount(testUserWithBirthday, testUserWithBirthday.getTickets()), is(BIRTHDAY_DISCOUNT));
    }

    @Test
    void ShouldCalculateDiscountForUserWithoutBirthday() {
        assertThat(discountStrategy.calculateDiscount(testUserWithoutBirthday, testUserWithoutBirthday.getTickets()), is(ZERO_DISCOUNT));
    }
}
