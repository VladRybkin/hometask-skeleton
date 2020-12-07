package ua.training.spring.hometask.rest.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.training.spring.hometask.dto.rest.request.BookTicketParameter;
import ua.training.spring.hometask.dto.rest.response.BookTicketResult;
import ua.training.spring.hometask.facade.BookingFacade;
import ua.training.spring.hometask.service.TicketService;

@RestController
@RequestMapping("/operations/booking")
public class BookingOperationsController {

    @Autowired
    private BookingFacade bookingFacade;

    @Autowired
    private TicketService ticketService;

    @PutMapping(value = "/book")
    public BookTicketResult bookTicket(@RequestBody BookTicketParameter bookTicketParameter) {
        return bookingFacade.bookTicket(bookTicketParameter);
    }

    @GetMapping(value = "/tickets", produces = {MediaType.APPLICATION_PDF_VALUE})
    public byte[] getPdfTickets() {
        return ticketService.getAll().toString().getBytes();
    }
}
