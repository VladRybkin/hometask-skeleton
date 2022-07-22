package ua.training.spring.hometask.controller;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.facade.TicketFacade;
import ua.training.spring.hometask.service.EventService;
import ua.training.spring.hometask.service.TicketService;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/tickets")
public class TicketsController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketFacade ticketFacade;

    @Autowired
    private EventService eventService;

    @GetMapping
    public String getTickets(final Model model) {
        final Collection<Ticket> tickets = ticketService.getAll();

        model.addAttribute("tickets", tickets);
        addEventNamesAttribute(model);

        return "tickets";
    }

    @PreAuthorize("hasAuthority('BOOKING_MANAGER')")
    @PostMapping(value = "/add")
    public String addTicket(@RequestParam final String eventName, @ModelAttribute final Ticket ticket) {
        ticketFacade.saveTicketWithEvent(eventName, ticket);

        return "redirect:/tickets";
    }

    @PreAuthorize("hasAuthority('BOOKING_MANAGER')")
    @GetMapping(value = "/remove/{id}")
    public String remove(Model model, @PathVariable final Long id) {
        ticketService.remove(ticketService.getById(id));

        return "redirect:/tickets";
    }

    @GetMapping(value = "/getbyid/{id}")
    public String getById(final Model model, @PathVariable final Long id) {
        final List<Ticket> tickets = Lists.newArrayList();
        Ticket ticket = ticketService.getById(id);

        if (Objects.nonNull(ticket)) {
            tickets.add(ticket);
        }
        model.addAttribute("tickets", tickets);
        addEventNamesAttribute(model);

        return "tickets";
    }

    @PreAuthorize("hasAuthority('BOOKING_MANAGER')")
    @GetMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ModelAndView getPdf(@RequestParam final String ticketDate, @RequestParam final String eventName) {
        final Collection<Ticket> tickets = ticketFacade.getPurchasedTicketsForEvent(eventName, ticketDate);

        return new ModelAndView("ticketPdfView", "ticketData", tickets);
    }


    private void addEventNamesAttribute(final Model model) {
        Set<String> eventNames = eventService.getAll().stream().map(Event::getName)
                .collect(Collectors.toSet());
        model.addAttribute("eventNames", eventNames);
    }
}
