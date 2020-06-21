package ua.training.spring.hometask.service;

import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.domain.User;

import java.util.Set;


public interface DiscountService {

    double getDiscount(User user, Set<Ticket> tickets);
}
