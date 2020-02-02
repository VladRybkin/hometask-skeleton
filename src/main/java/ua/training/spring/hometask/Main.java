package ua.training.spring.hometask;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import ua.training.spring.hometask.config.BeansConfiguration;
import ua.training.spring.hometask.dao.mapper.TicketMapper;
import ua.training.spring.hometask.dao.mapper.UserMapper;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.EventRating;
import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.service.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


public class Main {


    public static void main(String[] args) throws Exception {
        //        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:auditorium.xml", "discount-strategies.xml");
        //        System.out.println(applicationContext.getId());
        //        System.out.println(applicationContext.getBean(UserService.class));
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

        ctx.register(BeansConfiguration.class);

        ctx.refresh();
        EventService eventService = ctx.getBean(EventService.class);
        EventCountService eventCountService = ctx.getBean(EventCountService.class);
        BookingService bookingService = ctx.getBean(BookingService.class);
        TicketService ticketService = ctx.getBean(TicketService.class);
        UserService userService = ctx.getBean(UserService.class);
        User user=new User();
        user.setEmail("testemail2");
        user.setFirstName("TestFirst");
        Ticket ticket=new Ticket();
        ticket.setSeat(3);
        ticket.setBasePrice(103);
        ticket.setDateTime(LocalDateTime.now());
        ticketService.save(ticket);



    }

        //        Bootstrap.main(args);




}
