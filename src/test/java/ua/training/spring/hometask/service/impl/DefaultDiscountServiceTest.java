package ua.training.spring.hometask.service.impl;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.service.DiscountService;

import java.time.LocalDateTime;


 class DefaultDiscountServiceTest {

    private DiscountService discountService;

    private User testUser;

    private Event testEvent;

    private LocalDateTime testLocalDateTime;

    private Long numberOfTickets;

    @BeforeEach
    void setUp() {
        discountService = new DefaultDiscountService();
    }

    @Test
    void getDiscount() {

    }
}