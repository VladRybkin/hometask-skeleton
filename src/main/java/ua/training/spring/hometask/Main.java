package ua.training.spring.hometask;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import ua.training.spring.hometask.config.BeansConfiguration;
import ua.training.spring.hometask.domain.*;
import ua.training.spring.hometask.service.*;
import ua.training.spring.hometask.service.impl.DefaultEventCountService;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


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
//        User user = new User();
//        user.setEmail("brithd");
//        user.setFirstName("TestFirst");
//        user.setDateOfBirth(LocalDateTime.now().minusDays(5));
        User user1 = new User();
        user1.setEmail("birthd3");
        user1.setFirstName("Testsecond");
        user1.setDateOfBirth(LocalDateTime.now().plusDays(5));

        UserDiscountCountService defaultEventCountService=ctx.getBean(UserDiscountCountService.class);
        System.out.println(eventCountService);

        System.out.println(eventService.getAll());









    }

    //        Bootstrap.main(args);


}
