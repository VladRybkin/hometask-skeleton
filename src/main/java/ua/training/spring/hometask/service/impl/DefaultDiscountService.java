package ua.training.spring.hometask.service.impl;

import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.service.DiscountService;
import ua.training.spring.hometask.service.strategy.DiscountStrategy;

import java.util.Objects;
import java.util.OptionalDouble;
import java.util.Set;

public class DefaultDiscountService implements DiscountService {

    private Set<DiscountStrategy> discountStrategies;

    public DefaultDiscountService() {
    }

    public DefaultDiscountService(final Set<DiscountStrategy> discountStrategies) {
        this.discountStrategies = discountStrategies;
    }

    @Override
    public double getDiscount(final User user, final Set<Ticket> tickets) {
        double discount = 0;
        if (!Objects.isNull(user)) {
            OptionalDouble optionalDiscount = discountStrategies.stream()
                    .mapToDouble(strategy -> strategy.calculateDiscount(user, tickets)).max();
            if (optionalDiscount.isPresent()) {
                discount = optionalDiscount.getAsDouble();
            }
        }

        return discount;
    }

    public void setDiscountStrategies(Set<DiscountStrategy> discountStrategies) {
        this.discountStrategies = discountStrategies;
    }
}
