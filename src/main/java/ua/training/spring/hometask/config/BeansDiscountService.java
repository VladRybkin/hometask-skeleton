package ua.training.spring.hometask.config;

import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ua.training.spring.hometask.service.DiscountService;
import ua.training.spring.hometask.service.impl.DefaultDiscountService;
import ua.training.spring.hometask.service.strategy.BirthdayDiscountStrategy;
import ua.training.spring.hometask.service.strategy.DiscountStrategy;
import ua.training.spring.hometask.service.strategy.TenthTicketDiscountStrategy;

import java.util.Set;

@Configuration
@PropertySource("classpath:local.properties")
public class BeansDiscountService {

    @Value("${birthday.discount}")
    private String birthdayDiscount;

    @Value("${tenthTicket.discount}")
    private String tenthTicketDiscount;


    @Bean
    DiscountStrategy birthdayDiscountStrategy() {
        BirthdayDiscountStrategy birthdayDiscountStrategy = new BirthdayDiscountStrategy();
        birthdayDiscountStrategy.setBirthdayDiscount(Integer.parseInt(birthdayDiscount));

        return birthdayDiscountStrategy;
    }

    @Bean
    DiscountStrategy tenthTicketDiscountStrategy() {
        TenthTicketDiscountStrategy tenthTicketDiscountStrategy = new TenthTicketDiscountStrategy();
        tenthTicketDiscountStrategy.setTenthTicketDiscount(Integer.parseInt(tenthTicketDiscount));

        return tenthTicketDiscountStrategy;
    }

    @Bean
    DiscountService defaultDiscountService() {
        Set<DiscountStrategy> discountStrategies = Sets.newHashSet(birthdayDiscountStrategy(), tenthTicketDiscountStrategy());
        DefaultDiscountService discountService = new DefaultDiscountService();
        discountService.setDiscountStrategies(discountStrategies);

        return discountService;
    }
}

