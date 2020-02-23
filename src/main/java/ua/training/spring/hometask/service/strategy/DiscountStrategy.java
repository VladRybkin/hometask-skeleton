package ua.training.spring.hometask.service.strategy;


import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.domain.User;

import java.util.Set;


public interface DiscountStrategy {

    double calculateDiscount(User user, Set<Ticket> tickets);
}
