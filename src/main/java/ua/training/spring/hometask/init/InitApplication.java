package ua.training.spring.hometask.init;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ua.training.spring.hometask.dao.EventDao;
import ua.training.spring.hometask.dao.TicketDao;
import ua.training.spring.hometask.dao.UserDao;
import ua.training.spring.hometask.domain.Auditorium;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.EventRating;
import ua.training.spring.hometask.domain.Role;
import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.service.AuditoriumService;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

import static java.time.temporal.ChronoUnit.MINUTES;

@Component
@Profile("IN_MEMORY")
public class InitApplication {

    @Autowired
    @Qualifier("userDaoImpl")
    private UserDao userDao;

    @Autowired
    @Qualifier("eventDaoImpl")
    private EventDao eventDao;

    @Autowired
    @Qualifier("auditoriumService")
    private AuditoriumService auditoriumService;

    @Autowired
    @Qualifier("ticketDaoImpl")
    private TicketDao ticketDao;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @PostConstruct
    void fulfilImmemoryRepositoryWithInitialData() {
        User user = buildUser();
        userDao.save(user);
        Event event = buildEvent();
        Event event1 = new Event();
        event1.setName("secondEvent");
        event1.setBasePrice(300);

        eventDao.save(event);
        eventDao.save(event1);
        saveTickets(10, event);
    }

    private Event buildEvent() {
        Event event = new Event();
        Set<LocalDateTime> airDates = Sets.newTreeSet();
        List<Auditorium> auditoriums = Lists.newArrayList(auditoriumService.getAll());

        LocalDateTime firstDate = LocalDateTime.now().plusMonths(1).plusDays(4).truncatedTo(MINUTES);
        LocalDateTime secondDate = LocalDateTime.now().plusMonths(2).plusDays(5).truncatedTo(MINUTES);
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
        user.setPassword(encoder.encode("testpass"));
        user.setEmail("VladTV@mail");
        user.getRoles().add(new Role("USER"));
        user.getRoles().add(new Role("BOOKING_MANAGER"));
        user.setDateOfBirth(LocalDateTime.now());

        return user;
    }

    private void saveTickets(int amount, Event event) {
        Set<Ticket> tickets = Sets.newTreeSet();
        IntStream.rangeClosed(1, amount).forEach(addTicket(tickets, event));
        tickets.forEach(ticketDao::save);
    }

    private IntConsumer addTicket(Set<Ticket> tickets, Event event) {
        return seat -> tickets.add(new Ticket(event, event.getAirDates().stream().findAny().orElse(null), seat, 100));
    }
}
