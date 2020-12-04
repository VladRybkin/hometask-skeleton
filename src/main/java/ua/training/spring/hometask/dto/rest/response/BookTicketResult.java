package ua.training.spring.hometask.dto.rest.response;

import ua.training.spring.hometask.domain.Ticket;

public class BookTicketResult {

    private Ticket ticket;

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
