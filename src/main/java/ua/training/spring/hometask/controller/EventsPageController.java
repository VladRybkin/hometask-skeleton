package ua.training.spring.hometask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.training.spring.hometask.service.EventService;

@Controller
@RequestMapping(value = "/events")
public class EventsPageController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public String welcome(Model model) {
        model.addAttribute("events", eventService.getAll());
        model.addAttribute("oneObj", "SimpleObject");
        return "events";
    }
}