package ua.training.spring.hometask;


import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.training.spring.hometask.config.BeansConfiguration;
import ua.training.spring.hometask.domain.*;
import ua.training.spring.hometask.service.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;


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
        DiscountService discountService=ctx.getBean(DiscountService.class);
        UserDiscountService userDiscountService=ctx.getBean(UserDiscountService.class);

//        bookingService.getTicketsPrice(new Event("vlad"), null, null);
        eventService.getByName("first event");
//        eventService.getByName("first event");
//        eventService.getByName("first event");
        List<Ticket>tickets= Lists.newArrayList(ticketService.getAll());
        List<User> users= Lists.newArrayList(userService.getAll());
        bookingService.bookTicket(tickets.get(0), users.get(0));
        bookingService.bookTickets(new HashSet<>(tickets), users.get(0));
        User user=users.get(0);
        User user1=new User();
        user1.setDateOfBirth(LocalDateTime.now());
//        user1.setDateOfBirth(LocalDateTime.now());

        bookingService.getTicketsPrice(eventService.getByName("first event"), user,Sets.newHashSet(1L,2L,3L,4L,5L,6L,7L,8L,9L,10L));
        bookingService.getTicketsPrice(eventService.getByName("first event"), user,Sets.newHashSet(1L,2L,3L,4L,5L,6L,7L,8L,9L,10L));
        bookingService.getTicketsPrice(eventService.getByName("first event"), user1,Sets.newHashSet(1L,2L,3L,4L,5L,6L,7L,8L,9L,10L));
//        System.out.println("discouuntttttttttt"+discountService.getDiscount(users.get(0), Sets.newHashSet(tickets)));


//                Bootstrap.main(args);

        System.out.println(eventCountService.getAll());
        System.out.println(eventService.getAll());
        System.out.println(userDiscountService.getAll());



    }


}
