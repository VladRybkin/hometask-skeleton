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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DefaultDiscountServiceTest {

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
    public void setUp() {
        Set<DiscountStrategy> discountStrategies = Sets.newHashSet(birthdayStrategy, tenthTicketStrategy);
        discountService = new DefaultDiscountService(discountStrategies);
    }

    @Test
    public void shouldChooseBirthdayStrategyDiscountAsHigher() {
        User user = new User();
        Set<Ticket> tickets = Sets.newTreeSet();

        when(birthdayStrategy.calculateDiscount(user, tickets)).thenReturn(BIRTHDAY_DISCOUNT);
        when(tenthTicketStrategy.calculateDiscount(user, tickets)).thenReturn(TENTH_TICKET_DISCOUNT);

        assertThat(discountService.getDiscount(user, tickets), is(BIRTHDAY_DISCOUNT));
        verify(birthdayStrategy).calculateDiscount(user, tickets);
        verify(tenthTicketStrategy).calculateDiscount(user, tickets);
    }


    @Test
    public void shouldReturnZeroDiscountAsNotMatchAnyStrategy() {
        User user = new User();
        Set<Ticket> tickets = Sets.newTreeSet();

        when(birthdayStrategy.calculateDiscount(user, tickets)).thenReturn(ZERO_DISCOUNT);
        when(tenthTicketStrategy.calculateDiscount(user, tickets)).thenReturn(ZERO_DISCOUNT);

        assertThat(discountService.getDiscount(user, tickets), is(ZERO_DISCOUNT));
        verify(birthdayStrategy).calculateDiscount(user, tickets);
        verify(tenthTicketStrategy).calculateDiscount(user, tickets);
    }


}