package ua.training.spring.hometask.service.impl;


import com.google.common.collect.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.service.strategy.BirthdayDiscountStrategy;
import ua.training.spring.hometask.service.strategy.DiscountStrategy;
import ua.training.spring.hometask.service.strategy.TenthTicketDiscountStrategy;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultDiscountServiceTest {

    private static final double BIRTHDAY_DISCOUNT = 10;

    private static final double TENTH_TICKET_DISCOUNT = 5;

    private static final double ZERO_DISCOUNT = 0;

    @InjectMocks
    private DefaultDiscountService discountService;

    @Mock
    private BirthdayDiscountStrategy birthdayStrategy;

    @Mock
    private TenthTicketDiscountStrategy tenthTicketStrategy;


    @BeforeEach
    void setUp() {
        Set<DiscountStrategy> discountStrategies = Sets.newHashSet(birthdayStrategy, tenthTicketStrategy);
        discountService = new DefaultDiscountService(discountStrategies);
    }

    @Test
    void shouldChooseBirthdayStrategyDiscountAsHigher() {
        User user = new User();
        Set<Ticket> tickets = Sets.newTreeSet();

        when(birthdayStrategy.calculateDiscount(user, tickets)).thenReturn(BIRTHDAY_DISCOUNT);
        when(tenthTicketStrategy.calculateDiscount(user, tickets)).thenReturn(TENTH_TICKET_DISCOUNT);

        double expectedDiscount = discountService.getDiscount(user, tickets);

        assertThat(expectedDiscount, is((BIRTHDAY_DISCOUNT)));

        verify(birthdayStrategy).calculateDiscount(user, tickets);
        verify(tenthTicketStrategy).calculateDiscount(user, tickets);
    }


    @Test
    void shouldReturnZeroDiscountAsNotMatchAnyStrategy() {
        User user = new User();
        Set<Ticket> tickets = Sets.newTreeSet();

        when(birthdayStrategy.calculateDiscount(user, tickets)).thenReturn(ZERO_DISCOUNT);
        when(tenthTicketStrategy.calculateDiscount(user, tickets)).thenReturn(ZERO_DISCOUNT);

        double expectedDiscount = discountService.getDiscount(user, tickets);

        assertThat(expectedDiscount, is((ZERO_DISCOUNT)));

        verify(birthdayStrategy).calculateDiscount(user, tickets);
        verify(tenthTicketStrategy).calculateDiscount(user, tickets);
    }

    @Test
    void shouldNotCallStrategiesIfUserIsNullAndReturnZeroDiscount() {
        Set<Ticket> tickets = Sets.newTreeSet();

        double expectedDiscount = discountService.getDiscount(null, tickets);

        assertThat(expectedDiscount, is((ZERO_DISCOUNT)));

        verify(birthdayStrategy, never()).calculateDiscount(any(), any());
        verify(tenthTicketStrategy, never()).calculateDiscount(any(), any());
    }
}