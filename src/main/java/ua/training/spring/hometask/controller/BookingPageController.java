package ua.training.spring.hometask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.training.spring.hometask.facade.BookingFacade;

@Controller
@RequestMapping(value = "/booking")
public class BookingPageController {

    @Autowired
    private BookingFacade bookingFacade;

    @GetMapping
    public String welcome() {

        return "booking";
    }

    @PostMapping
    public String bookTicket(@RequestParam final Long ticketId, @RequestParam final Long userId) {
        bookingFacade.bookTicket(ticketId, userId);
        return "redirect:booking";
    }
}
