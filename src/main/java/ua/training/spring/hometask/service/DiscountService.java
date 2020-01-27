package ua.training.spring.hometask.service;

import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.domain.User;

import javax.annotation.Nullable;
import java.util.Set;

/**
 * @author Yuriy_Tkach
 */
public interface DiscountService {

    /**
     * Getting discount based on some rules for user that buys some number of
     * tickets for the specific date time of the event
     *
     * @param user User that buys tickets. Can be <code>null</code>
     */
    double getDiscount(@Nullable User user, Set<Ticket> tickets);

}
