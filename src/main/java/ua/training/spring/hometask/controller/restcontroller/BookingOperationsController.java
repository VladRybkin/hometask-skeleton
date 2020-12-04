package ua.training.spring.hometask.controller.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.training.spring.hometask.dto.rest.request.BookTicketParameter;
import ua.training.spring.hometask.dto.rest.response.BookTicketResult;
import ua.training.spring.hometask.facade.BookingFacade;

@RestController
@RequestMapping("/operations/booking")
public class BookingOperationsController {

    @Autowired
    private BookingFacade bookingFacade;

    @PostMapping(value = "/book")
    public BookTicketResult bookTicket(@RequestBody BookTicketParameter bookTicketParameter) {
        return bookingFacade.bookTicket(bookTicketParameter);
    }
}
