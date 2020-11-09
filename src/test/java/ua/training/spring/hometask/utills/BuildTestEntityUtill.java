package ua.training.spring.hometask.utills;

import ua.training.spring.hometask.dao.EventDao;
import ua.training.spring.hometask.dao.UserDao;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.EventCount;
import ua.training.spring.hometask.domain.EventRating;
import ua.training.spring.hometask.domain.Role;
import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.domain.UserDiscountCount;

import java.time.LocalDateTime;

public class BuildTestEntityUtill {

    public static EventCount buildTestEventCount() {
        EventCount eventCount = new EventCount();
        eventCount.setEventName("test event name");
        eventCount.setCountBookTickets(0);
        eventCount.setCountGetPrice(0);
        eventCount.setCountGetByName(0);

        return eventCount;
    }

    public static Event buildTestEvent() {
        Event event = new Event();
        event.setName("testEvent");
        event.setRating(EventRating.HIGH);
        event.setBasePrice(100);

        return event;
    }

    public static Ticket buildTestTicketWithPersistedUserAndEvent(UserDao userDao, EventDao eventDao) {
        Ticket ticket = new Ticket();
        ticket.setBasePrice(100);
        ticket.setSeat(100);

        User user = new User();
        user.setEmail("usermail@ticket.com");
        user.setPassword("testPassword");

        Event event = new Event();
        event.setBasePrice(100);
        event.setName("testEvent");

        LocalDateTime ticketDate = LocalDateTime.now();
        ticket.setDateTime(ticketDate);
        ticket.setUser(user);
        ticket.setEvent(event);

        userDao.save(user);
        eventDao.save(event);

        return ticket;
    }

    public static User buildHibernateTestUser() {
        User user = new User();
        user.setEmail("testEmail@gmail.ua");
        user.setFirstName("TestUser");
        user.setLastName("testLastName");
        user.setPassword("testPassword");
        user.getRoles().add(new Role("USER"));

        return user;
    }

    public static User buildTestUser() {
        User user = new User();
        user.setEmail("testEmail@gmail.ua");
        user.setFirstName("TestUser");
        user.setLastName("testLastName");
        user.setPassword("testPassword");

        return user;
    }

    public static UserDiscountCount buildUserDiscountCount() {
        UserDiscountCount userDiscountCount = new UserDiscountCount();
        userDiscountCount.setName("test discount name");
        userDiscountCount.setCountBirthdayDiscount(0);
        userDiscountCount.setCountTenthTicketDiscount(0);

        return userDiscountCount;
    }
}
