package ua.training.spring.hometask.service.strategy;


import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.User;

import java.time.LocalDateTime;


public class BirthdayDiscountStrategy implements DiscountStrategy {


    @Override
    public double calculateDiscount(User user) {
        LocalDateTime dateOfBirth = user.getDateOfBirth();
        int discount = 0;
        if (dateOfBirth != null) {
            if (dateOfBirth.getMonth().equals(LocalDateTime.now().getMonth()) &
                    Integer.valueOf(dateOfBirth.getDayOfMonth()).equals(LocalDateTime.now().getDayOfMonth())) {
                discount = 10;
            }
        }
        return discount;
    }
}
