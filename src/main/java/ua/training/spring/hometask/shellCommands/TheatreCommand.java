package ua.training.spring.hometask.shellCommands;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliAvailabilityIndicator;
import org.springframework.shell.core.annotation.CliCommand;

import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.service.EventService;
import ua.training.spring.hometask.service.TicketService;
import ua.training.spring.hometask.service.UserService;

import java.util.Collection;


@Component
public class TheatreCommand implements CommandMarker {

    @Autowired
    UserService userService;

    @Autowired
    EventService eventService;

    @Autowired
    TicketService ticketService;


    @CliAvailabilityIndicator({"print", "all user", "id user", "email user"})
    public boolean isUserCommandsAvailable() {
        return true;
    }

    @CliCommand(value = "print hello", help = "print hello world")
    public String print(@CliOption(key = "word") String word) {
        return word;
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
    public User getById(@CliOption(key = "mail") String email) throws Exception {

        return userService.getUserByEmail(email);
    }

    @CliCommand(value = "all event", help = "get all events")
    public Collection<Event> getAllEvent() throws Exception {

        return eventService.getAll();
    }

    @CliCommand(value = "all ticket", help = "get all tickets")
    public Collection<Ticket> getById() throws Exception {

        return ticketService.getAll();
    }


}
