package ua.training.spring.hometask.init;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.training.spring.hometask.domain.*;
import ua.training.spring.hometask.service.*;

import javax.annotation.PostConstruct;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

@Component
public class InitApplication {


    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Autowired
    private AuditoriumService auditoriumService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private BookingService bookingService;

    @PostConstruct
    void init() {
        User user = buildUser();
        userService.save(user);
        Event event = buildEvent();
        eventService.save(event);
        saveTickets(10, event);

    }

    private Event buildEvent() {
        Event event = new Event();
        Set<LocalDateTime> airDates = Sets.newTreeSet();
        List<Auditorium> auditoriums = Lists.newArrayList(auditoriumService.getAll());

        LocalDateTime firstDate = LocalDateTime.now().plusMonths(1).plusDays(4);
        LocalDateTime secondDate = LocalDateTime.now().plusMonths(2).plusDays(5);
        airDates.add(firstDate);
        airDates.add(secondDate);

        event.setId(1L);
        event.setName("first event");
        event.setRating(EventRating.HIGH);
        event.setBasePrice(100);
        event.getAirDates().addAll(airDates);
        event.assignAuditorium(firstDate, auditoriums.get(0));
        event.assignAuditorium(secondDate, auditoriums.get(1));

        return event;
    }

    private User buildUser() {
        User user = new User();
        user.setFirstName("Vlad");
        user.setId(1L);
        user.setEmail("VladTV@mail");
        return user;
    }


    private void saveTickets(int amount, Event event) {
        Set<Ticket> tickets = Sets.newTreeSet();
        IntStream.rangeClosed(1, amount).forEach(addTicket(tickets, event));
        tickets.forEach(ticketService::save);

    }

    private IntConsumer addTicket(Set<Ticket> tickets, Event event) {
        return seat -> tickets.add(new Ticket(event, event.getAirDates().first(), seat, 100));
    }


    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    public void setAuditoriumService(AuditoriumService auditoriumService) {
        this.auditoriumService = auditoriumService;
    }

    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public void setBookingService(BookingService bookingService) {
        this.bookingService = bookingService;
    }
}
