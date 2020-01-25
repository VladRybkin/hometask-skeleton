package ua.training.spring.hometask.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.service.impl.DefaultUserDiscountCountService;


@Aspect
@Component
public class UserDiscountAspect {


    @Autowired
    private DefaultUserDiscountCountService discountCountService;

    private static final double birthdayDiscount = 10;

    private static final double tenthTicketDiscount = 5;

    @AfterReturning(value =
            "execution(* ua.training.spring.hometask.service.impl.DefaultDiscountService.getDiscount(..)) && args(user, ..)",
            returning = ("discount"),
            argNames = "discount,user")
    public void incrementUserDiscountCount(final Double discount, final User user) {
        if (discount == tenthTicketDiscount) {
            discountCountService.countTenthTicketDiscountIncrement(user.getEmail());
        }
        if (discount == birthdayDiscount) {
            discountCountService.countBirthdayDiscountIncrement(user.getEmail());
        }


    }

    @AfterReturning(value =
            "execution(* ua.training.spring.hometask.service.impl.DefaultDiscountService.getDiscount(..)) && args(user, ..)",
            returning = ("discount"),
            argNames = "discount,user")
    public void incrementTotalDiscountCount(final Double discount, final User user) {
        if (discount == tenthTicketDiscount) {
            discountCountService.countTenthTicketDiscountIncrement("tenth ticket discount");
        }
        if (discount == birthdayDiscount) {
            discountCountService.countBirthdayDiscountIncrement("birthday discount");
        }


    }


}
