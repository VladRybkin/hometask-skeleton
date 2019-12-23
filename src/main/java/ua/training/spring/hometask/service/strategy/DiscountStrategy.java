package ua.training.spring.hometask.service.strategy;


import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.User;


public interface DiscountStrategy {

    int calculateDiscount(User user, Event event);

}
