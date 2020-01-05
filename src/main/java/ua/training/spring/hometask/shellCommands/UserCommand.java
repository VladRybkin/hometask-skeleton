package ua.training.spring.hometask.shellCommands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliAvailabilityIndicator;
import org.springframework.shell.core.annotation.CliCommand;

import org.springframework.stereotype.Component;
import ua.training.spring.hometask.service.UserService;

@Component
public class UserCommand implements CommandMarker {

    @Autowired
    private UserService userService;


    @CliAvailabilityIndicator({ "print hello" })
    public boolean isUserCommandsAvailable() {
        return true;
    }

    @CliCommand(value = "print hello", help = "print hello world")
    public String print() {
        return "555";
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
