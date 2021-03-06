package ua.training.spring.hometask.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.service.UserDiscountCountService;


@Aspect
@Component
public class UserDiscountAspect {

    @Autowired
    private UserDiscountCountService discountCountService;

    @Value("${birthday.discount}")
    private double birthdayDiscount;


    @AfterReturning(value =
            "execution(* ua.training.spring.hometask." +
                    "service.impl.DefaultDiscountService.getDiscount(..)) && args(user, ..)",
            returning = ("discount"),
            argNames = "discount,user")
    public void incrementUserDiscountCount(final Double discount, final User user) {
        if (discount == birthdayDiscount) {
            discountCountService.countBirthdayDiscountIncrement(user.getEmail());
        } else if (discount != 0) {
            discountCountService.countTenthTicketDiscountIncrement(user.getEmail());
        }
    }

    @AfterReturning(value =
            "execution(* ua.training.spring.hometask." +
                    "service.impl.DefaultDiscountService.getDiscount(..)) && args(user, ..)",
            returning = ("discount"),
            argNames = "discount,user")
    public void incrementTotalDiscountCount(final Double discount, final User user) {
        if (discount == birthdayDiscount) {
            discountCountService.countBirthdayDiscountIncrement("birthday discount");
        } else if (discount != 0) {
            discountCountService.countTenthTicketDiscountIncrement("tenth ticket discount");
        }
    }
}
