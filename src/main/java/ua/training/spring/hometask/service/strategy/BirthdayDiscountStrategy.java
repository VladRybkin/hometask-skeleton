package ua.training.spring.hometask.service.strategy;


import ua.training.spring.hometask.domain.User;


public class BirthdayDiscountStrategy implements DiscountStrategy {

    public BirthdayDiscountStrategy() {
    }

    @Override
    public double calculateDiscount(User user) {
        return 0;
    }
}
