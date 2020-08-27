package ua.training.spring.hometask.controller;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.service.EventService;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping(value = "/events")
public class EventsController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public String getEvents(Model model) {
        model.addAttribute("events", eventService.getAll());

        return "events";
    }

    @PostMapping(value = "/add")
    public String addEvent(@ModelAttribute Event event) {
        eventService.save(event);

        return "redirect:/events";
    }

    @GetMapping(value = "/remove/{id}")
    public String remove(Model model, @PathVariable Long id) {
        eventService.remove(eventService.getById(id));

        return "redirect:/events";
    }

    @GetMapping(value = "/getbyid/{id}")
    public String getById(Model model, @PathVariable Long id) {
        List<Event> events = Lists.newArrayList();
        Event event = eventService.getById(id);
        if (Objects.nonNull(event)) {
            events.add(event);
        }
        model.addAttribute("events", events);

        return "events";
    }

    @GetMapping(value = "/getbyname")
    public String getByName(Model model, @RequestParam String name) {
        List<Event> events = Lists.newArrayList();
        Event event = eventService.getByName(name);
        if (Objects.nonNull(event)) {
            events.add(event);
        }
        model.addAttribute("events", events);

        return "events";
    }
}
