package ua.training.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.service.DiscountService;
import ua.training.spring.hometask.service.strategy.DiscountStrategy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.function.ToDoubleFunction;


public class DefaultDiscountService implements DiscountService {


    private Set<DiscountStrategy> discountStrategies;

    @Override
    public double getDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long numberOfTickets) {
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
