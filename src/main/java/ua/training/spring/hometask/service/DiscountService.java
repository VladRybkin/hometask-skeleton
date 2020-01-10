package ua.training.spring.hometask.service;

import java.time.LocalDateTime;
import java.util.NavigableSet;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.domain.User;

/**
 * @author Yuriy_Tkach
 */
public interface DiscountService {

    /**
     * Getting discount based on some rules for user that buys some number of
     * tickets for the specific date time of the event
     * 
     * @param user
     *            User that buys tickets. Can be <code>null</code>

     */
    double getDiscount(@Nullable User user, Set<Ticket> tickets);

}
