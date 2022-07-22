package ua.training.spring.hometask.controller;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.training.spring.hometask.domain.Auditorium;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.facade.EventFacade;
import ua.training.spring.hometask.service.AuditoriumService;
import ua.training.spring.hometask.service.EventService;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/events")
public class EventsController {

    @Autowired
    private EventFacade eventFacade;

    @Autowired
    private EventService eventService;

    @Autowired
    private AuditoriumService auditoriumService;

    @GetMapping
    public String getEvents(final Model model) {
        model.addAttribute("events", eventService.getAll());
        addAuditoriumNamesAttribute(model);

        return "events";
    }

    @PreAuthorize("hasAuthority('BOOKING_MANAGER')")
    @PostMapping(value = "/add")
    public String addEvent(@ModelAttribute final Event event, @RequestParam final String eventDate,
            @RequestParam final String auditoriumName) {
        eventFacade.saveEvent(event, eventDate, auditoriumName);

        return "redirect:/events";
    }

    @PreAuthorize("hasAuthority('BOOKING_MANAGER')")
    @GetMapping(value = "/remove/{id}")
    public String remove(Model model, @PathVariable Long id) {
        eventService.remove(eventService.getById(id));

        return "redirect:/events";
    }

    @GetMapping(value = "/getbyid/{id}")
    public String getById(Model model, @PathVariable Long id) {
        final List<Event> events = Lists.newArrayList();
        final Event event = eventService.getById(id);
        if (Objects.nonNull(event)) {
            events.add(event);
        }

        model.addAttribute("events", events);
        addAuditoriumNamesAttribute(model);

        return "events";
    }

    @GetMapping(value = "/getbyname")
    public String getByName(Model model, @RequestParam String name) {
        final List<Event> events = Lists.newArrayList();
        final Event event = eventService.getByName(name);
        if (Objects.nonNull(event)) {
            events.add(event);
        }
        model.addAttribute("events", events);
        addAuditoriumNamesAttribute(model);

        return "events";
    }

    private void addAuditoriumNamesAttribute(Model model) {
        final Collection<Auditorium> auditoriums = auditoriumService.getAll();
        final Set<String> auditoriumNames = auditoriums.stream().map(Auditorium::getName).collect(Collectors.toSet());
        model.addAttribute("auditoriumNames", auditoriumNames);
    }
}
