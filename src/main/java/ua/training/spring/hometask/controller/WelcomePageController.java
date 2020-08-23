package ua.training.spring.hometask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/welcome")
public class WelcomePageController {

    @GetMapping
    public String welcome() {
        return "welcome";
    }
}
