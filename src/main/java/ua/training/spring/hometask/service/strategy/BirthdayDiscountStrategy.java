package ua.training.spring.hometask.service.strategy;


import org.springframework.beans.factory.annotation.Value;
import ua.training.spring.hometask.domain.User;

import java.time.LocalDateTime;


public class BirthdayDiscountStrategy implements DiscountStrategy {

    @Value("${birthday.discount}")
    private int birthdayDiscount;

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

    public int getBirthdayDiscount() {
        return birthdayDiscount;
    }

    public void setBirthdayDiscount(int birthdayDiscount) {
        this.birthdayDiscount = birthdayDiscount;
    }
}
