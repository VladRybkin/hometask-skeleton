package ua.training.spring.hometask.shellCommands;

import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliAvailabilityIndicator;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.service.BookingService;
import ua.training.spring.hometask.service.EventService;
import ua.training.spring.hometask.service.TicketService;
import ua.training.spring.hometask.service.UserService;

import java.util.Collection;
import java.util.Set;

@Component
public class TheatreCommand implements CommandMarker {

    @Autowired
    UserService userService;

    @Autowired
    EventService eventService;

    @Autowired
    TicketService ticketService;

    @Autowired
    BookingService bookingService;


    @CliAvailabilityIndicator({"print", "all user", "id user", "email user", "all event", "all ticket", "ticket price"})
    public boolean isUserCommandsAvailable() {
        return true;
    }


    @CliCommand(value = "all user", help = "get all users")
    public Collection<User> getAllUsers() {

        return userService.getAll();
    }

    @CliCommand(value = "id user", help = "get user by id")
    public User getById(@CliOption(key = "id") long id) {

        return userService.getById(id);
    }

    @CliCommand(value = "email user", help = "get user by email")
    public User getById(@CliOption(key = "mail") String email) {

        return userService.getUserByEmail(email);
    }

    @CliCommand(value = "all event", help = "get all events")
    public Collection<Event> getAllEvent() {

        return eventService.getAll();
    }

    @CliCommand(value = "all ticket", help = "get all tickets")
    public Collection<Ticket> getAllTickets() {

        return ticketService.getAll();
    }

    @CliCommand(value = "ticket price", help = "ticket price")
    public double getTicketsPrice() {
        User user = userService.getById(1L);
        Event event = eventService.getById(1L);
        Set<Long> seats = Sets.newHashSet(1L, 2L);

        return bookingService.getTicketsPrice(event, user, seats);
    }
}
