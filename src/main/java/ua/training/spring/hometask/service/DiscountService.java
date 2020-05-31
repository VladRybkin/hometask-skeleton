package ua.training.spring.hometask.service;

import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;


public interface DiscountService {

    double getDiscount(@Nullable User user, @Nonnull Set<Ticket> tickets);
}
