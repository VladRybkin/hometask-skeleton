package ua.training.spring.hometask.aspect;

import org.apache.commons.lang3.RandomUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.domain.User;

@Aspect
@Component
public class LuckyWinnerAspect {

    @Before(value =
            "execution(* ua.training.spring.hometask.service." +
                    "impl.DefaultBookingService.bookTicket(..)) && args(ticket, user)",
            argNames = "ticket,user ")
    public void incrementUserDiscountCount(final Ticket ticket, final User user) {
        if (checkLucky()) {
            ticket.setBasePrice(0);
        }
    }

    private boolean checkLucky() {
        return RandomUtils.nextInt(1, 51) == 25;
    }
}
