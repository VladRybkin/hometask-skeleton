package ua.training.spring.hometask.shellCommands;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliAvailabilityIndicator;
import org.springframework.shell.core.annotation.CliCommand;

import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.service.UserService;

import javax.annotation.PostConstruct;
import java.util.Collection;


@Component
public class UserCommand implements CommandMarker {

    @Autowired
    UserService userService;

    @PostConstruct
    void print() {
        User user = new User();
        user.setFirstName("Vlad");
        user.setId(1L);
        user.setEmail("VladTV@mail");
        userService.save(user);
    }

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


}
