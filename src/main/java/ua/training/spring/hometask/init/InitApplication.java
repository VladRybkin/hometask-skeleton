package ua.training.spring.hometask.init;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ua.training.spring.hometask.dao.EventDao;
import ua.training.spring.hometask.dao.TicketDao;
import ua.training.spring.hometask.dao.UserDao;
import ua.training.spring.hometask.domain.*;
import ua.training.spring.hometask.service.AuditoriumService;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

@Component
public class InitApplication {

    @Autowired
    @Qualifier("userDaoImpl")
    private UserDao userDao;

    @Autowired
    @Qualifier("eventDaoImpl")
    private EventDao eventDao;

    @Autowired
    private AuditoriumService auditoriumService;

    @Autowired
    @Qualifier("ticketDaoImpl")
    private TicketDao ticketDao;

    @PostConstruct
    void init() {
        User user = buildUser();
        userDao.save(user);
        Event event = buildEvent();

        eventDao.save(event);
        saveTickets(10, event);
        System.out.println("init method executed");
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
        tickets.forEach(ticketDao::save);
    }

    private IntConsumer addTicket(Set<Ticket> tickets, Event event) {
        return seat -> tickets.add(new Ticket(event, event.getAirDates().first(), seat, 100));
    }
}
