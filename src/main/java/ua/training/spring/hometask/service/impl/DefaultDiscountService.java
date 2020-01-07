package ua.training.spring.hometask.service.impl;


import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.service.DiscountService;
import ua.training.spring.hometask.service.strategy.DiscountStrategy;


import javax.annotation.Nullable;

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
    public double getDiscount(@Nullable User user) {
        double discount = 0;
        OptionalDouble optionalDiscount = discountStrategies.stream().mapToDouble(getDiscountStrategyToDoubleFunction(user)).max();
        if (optionalDiscount.isPresent()) {
            discount = optionalDiscount.getAsDouble();
        }

        return discount;
    }

    private ToDoubleFunction<DiscountStrategy> getDiscountStrategyToDoubleFunction(@Nullable User user) {
        return strategy -> strategy.calculateDiscount(user);
    }

    public void setDiscountStrategies(Set<DiscountStrategy> discountStrategies) {
        this.discountStrategies = discountStrategies;
    }
}
