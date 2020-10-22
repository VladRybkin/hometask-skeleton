package ua.training.spring.hometask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.training.spring.hometask.facade.BookingFacade;
import ua.training.spring.hometask.service.TicketService;

@Controller
@RequestMapping(value = "/booking")
public class BookingController {

    @Autowired
    private BookingFacade bookingFacade;

    @Autowired
    private TicketService ticketService;

    @GetMapping
    public String getBooking(Model model) {
        model.addAttribute("tickets", ticketService.getAll());

        return "booking";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public String bookTicket(@RequestParam final Long ticketId) {
        bookingFacade.bookTicket(ticketId);

        return "redirect:/booking";
    }
}
