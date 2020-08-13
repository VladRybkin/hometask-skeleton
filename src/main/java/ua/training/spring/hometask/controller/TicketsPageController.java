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
import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.facade.TicketFacade;
import ua.training.spring.hometask.service.EventService;
import ua.training.spring.hometask.service.TicketService;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/tickets")
public class TicketsPageController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketFacade ticketFacade;

    @Autowired
    private EventService eventService;

    @GetMapping
    public String welcome(Model model) {
        List<Ticket> tickets = Lists.newArrayList(ticketService.getAll());
        Set<String> eventNames = eventService.getAll().stream().map(Event::getName).collect(Collectors.toSet());

        model.addAttribute("tickets", tickets);
        model.addAttribute("options", eventNames);

        return "tickets";
    }

    @PostMapping(value = "/add")
    public String addEvent(@RequestParam String eventName, @ModelAttribute Ticket ticket) {
        ticketFacade.saveTicketWithEvent(eventName, ticket);

        return "redirect:/tickets";
    }

    @GetMapping(value = "/remove/{id}")
    public String remove(Model model, @PathVariable Long id) {
        ticketService.remove(ticketService.getById(id));

        return "redirect:/tickets";
    }

    @GetMapping(value = "/getbyid/{id}")
    public String getById(Model model, @PathVariable Long id) {
        List<Ticket> tickets = Lists.newArrayList();
        Ticket ticket = ticketService.getById(id);
        if (Objects.nonNull(ticket)) {
            tickets.add(ticket);
        }
        model.addAttribute("tickets", tickets);

        return "tickets";
    }
}
