package ua.training.spring.hometask.service.strategy;


import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.User;

import java.time.LocalDateTime;


public class BirthdayDiscountStrategy implements DiscountStrategy {

    private final static int birthdayDiscount=10;

    @Override
    public double calculateDiscount(User user) {
        LocalDateTime dateOfBirth = user.getDateOfBirth();
        int discount = 0;
        if (dateOfBirth != null) {
            if (dateOfBirth.getMonth().equals(LocalDateTime.now().getMonth()) &
                    Integer.valueOf(dateOfBirth.getDayOfMonth()).equals(LocalDateTime.now().getDayOfMonth())) {
                discount = birthdayDiscount;
            }
        }
        return discount;
    }
}
