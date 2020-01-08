package ua.training.spring.hometask.service.strategy;


import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.domain.User;

import java.util.NavigableSet;


public interface DiscountStrategy {

    double calculateDiscount(User user, NavigableSet<Ticket>tickets);

}
