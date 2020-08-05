package ua.training.spring.hometask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.service.EventService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping(value = "/events")
public class EventsPageController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public String welcome(Model model) {
        model.addAttribute("events", eventService.getAll());
        model.addAttribute("oneObj", null);

        return "events";
    }

    @PostMapping
    public String addEvent(Model model, @ModelAttribute Event event) {
        eventService.save(event);

        return "redirect:/events";
    }

    @GetMapping(value = "/remove/{id}")
    public String remove(Model model, @PathVariable Long id) {
        eventService.remove(eventService.getById(id));
        model.addAttribute("events", eventService.getAll());

        return "redirect:/events";
    }

    @GetMapping(value = "/getbyid/{id}")
    public String getById(Model model, @PathVariable Long id) {
        List<Event> events = new ArrayList<>();
        Event ev = eventService.getById(id);
        if (Objects.nonNull(ev)) {
            events.add(ev);
        }
        model.addAttribute("events", events);

        return "events";
    }

    @GetMapping(value = "/getbyname/{name}")
    public String getByName(Model model, @PathVariable String name) {
        List<Event> events = new ArrayList<>();
        Event ev = eventService.getByName(name);
        if (Objects.nonNull(ev)) {
            events.add(ev);
        }
        model.addAttribute("events", events);

        return "events";
    }
}
