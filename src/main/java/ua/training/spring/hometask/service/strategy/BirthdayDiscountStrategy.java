package ua.training.spring.hometask.service.strategy;


import org.springframework.beans.factory.annotation.Value;
import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.domain.User;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;


public class BirthdayDiscountStrategy implements DiscountStrategy {

    @Value("${birthday.discount}")
    private int birthdayDiscount;

    @Override
    public double calculateDiscount(User user, Set<Ticket> tickets) {
        LocalDateTime dateOfBirth = user.getDateOfBirth();
        int discount = 0;
        if (!Objects.isNull(dateOfBirth)) {
            if (dateOfBirth.getMonth().equals(LocalDateTime.now().getMonth()) &
                    Integer.valueOf(dateOfBirth.getDayOfMonth()).equals(LocalDateTime.now().getDayOfMonth())) {
                discount = birthdayDiscount;
            }
        }

        return discount;
    }

    public void setBirthdayDiscount(int birthdayDiscount) {
        this.birthdayDiscount = birthdayDiscount;
    }
}
