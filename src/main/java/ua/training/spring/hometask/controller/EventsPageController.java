package ua.training.spring.hometask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/events")
public class EventsPageController {

    @GetMapping
    public String welcome() {
        return "events";
    }
}
