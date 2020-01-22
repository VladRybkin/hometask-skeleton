package ua.training.spring.hometask;


import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.shell.Bootstrap;
import ua.training.spring.hometask.aspect.CounterAspect;
import ua.training.spring.hometask.config.BeansConfiguration;
import ua.training.spring.hometask.domain.*;
import ua.training.spring.hometask.service.*;

import java.util.List;
import java.util.Set;


public class Main {


    public static void main(String[] args) throws Exception {
        //        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:auditorium.xml", "discount-strategies.xml");
        //        System.out.println(applicationContext.getId());
        //        System.out.println(applicationContext.getBean(UserService.class));
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

        ctx.register(BeansConfiguration.class);

        ctx.refresh();
        EventService eventService = ctx.getBean(EventService.class);
        EventCountService eventCountService= ctx.getBean(EventCountService.class);
        BookingService bookingService= ctx.getBean(BookingService.class);
        TicketService ticketService= ctx.getBean(TicketService.class);
        UserService userService= ctx.getBean(UserService.class);

//        bookingService.getTicketsPrice(new Event("vlad"), null, null);
        eventService.getByName("first event");
        eventService.getByName("first event");
        eventService.getByName("first event");
        Set<Ticket>tickets= Sets.newHashSet(ticketService.getAll());
        List<User> users= Lists.newArrayList(userService.getAll());
        bookingService.bookTickets(tickets, users.get(0));
        bookingService.getTicketsPrice(eventService.getByName("first event"), new User(),Sets.newHashSet());

//                Bootstrap.main(args);

        System.out.println(eventCountService.getAll());


    }


}
