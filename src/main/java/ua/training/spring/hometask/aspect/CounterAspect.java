package ua.training.spring.hometask.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.service.EventCountService;

import java.util.Objects;

@Aspect
@Component
public class CounterAspect {

    @Autowired
    private EventCountService eventCountService;

    @AfterReturning(value =
            "execution(* ua.training.spring.hometask.service.impl.DefaultEventService.getByName(String))",
            returning = ("event"),
            argNames = "event")
    public void eventGetByNameCount(Event event) {
        if (Objects.nonNull(event)) {
            String eventName = event.getName();
            eventCountService.getByNameCountIncrement(eventName);
        }
    }

    @AfterReturning(value = "execution(* ua.training.spring.hometask." +
            "service.impl.DefaultBookingService.getTicketsPrice(..))  && args (event, ..)")
    public void countGetTicketsPrice(Event event) {
        if (Objects.nonNull(event)) {
            String eventName = event.getName();
            eventCountService.getPriceCountIncrement(eventName);
        }
    }


    @After(value = "execution(* ua.training.spring.hometask." +
            "service.impl.DefaultBookingService.bookTicket(..)) && args(ticket, ..)",
            argNames = "ticket")
    public void bookTicketsCount(Ticket ticket) {
        if (Objects.nonNull(ticket)) {
            Event event = ticket.getEvent();
            eventCountService.bookTicketsCountIncrement(event.getName());
        }
    }
}
