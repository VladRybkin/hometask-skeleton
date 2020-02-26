package ua.training.spring.hometask.service.impl;


import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.service.DiscountService;
import ua.training.spring.hometask.service.strategy.DiscountStrategy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.function.ToDoubleFunction;


public class DefaultDiscountService implements DiscountService {

    private Set<DiscountStrategy> discountStrategies;

    public DefaultDiscountService() {
    }

    public DefaultDiscountService(Set<DiscountStrategy> discountStrategies) {
        this.discountStrategies = discountStrategies;
    }

    @Override
    public double getDiscount(@Nullable User user, @Nonnull Set<Ticket> tickets) {
        double discount = 0;
        if (!Objects.isNull(user)) {
            OptionalDouble optionalDiscount = discountStrategies.stream()
                    .mapToDouble(getDiscountStrategyToDoubleFunction(user, tickets)).max();
            if (optionalDiscount.isPresent()) {
                discount = optionalDiscount.getAsDouble();
            }
        }

        return discount;
    }

    private ToDoubleFunction<DiscountStrategy> getDiscountStrategyToDoubleFunction(User user, Set<Ticket> tickets) {
        return strategy -> strategy.calculateDiscount(user, tickets);
    }

    public void setDiscountStrategies(Set<DiscountStrategy> discountStrategies) {
        this.discountStrategies = discountStrategies;
    }
}
