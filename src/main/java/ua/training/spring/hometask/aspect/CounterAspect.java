package ua.training.spring.hometask.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.service.EventCountService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Aspect
@Component
public class CounterAspect {

    @Autowired
    private EventCountService eventCountService;


    @AfterReturning(value = "execution(* ua.training.spring.hometask.service.impl.DefaultEventService.getByName(String))", returning = ("event"),
            argNames = "event")
    public void eventGetByNameCount(Event event) {
        String eventName = event.getName();
        eventCountService.getByNameCountIncrement(eventName);
        System.out.println("event get by name aspect");
    }

    @AfterReturning(value = "execution(* ua.training.spring.hometask.service.impl.DefaultBookingService.getTicketsPrice(..))  && args (event, ..), ")
    public void countGetTicketsPrice(Event event) {
        System.out.println(event);
        String eventName = event.getName();
        eventCountService.getPriceCountIncrement(eventName);
        System.out.println("tickets price aspect");
    }


    @After(value = "execution(* ua.training.spring.hometask.service.impl.DefaultBookingService.bookTickets(..)) && args(tickets, ..)", argNames = "tickets")
    public void bookTicketsCount(Set<Ticket> tickets) {
        Set<String> eventNames = tickets.stream().map(ticket -> ticket.getEvent().getName()).collect(Collectors.toSet());
        eventNames.forEach(en->eventCountService.bookTicketsCountIncrement(en));
        System.out.println("book tickets aspect");

    }
}
