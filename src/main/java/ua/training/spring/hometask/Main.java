package ua.training.spring.hometask;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.training.spring.hometask.config.BeansConfiguration;
import ua.training.spring.hometask.dao.impl.hibernate.*;
import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.service.EventService;
import ua.training.spring.hometask.service.UserService;

import java.time.LocalDateTime;


public class Main {

    public static void main(String[] args) throws Exception {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

        ctx.register(BeansConfiguration.class);
        ctx.refresh();
        HibernateUserDaoImpl hibernateUserDao = ctx.getBean(HibernateUserDaoImpl.class);
        HibernateEventDaoImpl hibernateEventDao = ctx.getBean((HibernateEventDaoImpl.class));
        EventService eventService = ctx.getBean(EventService.class);
        HibernateTicketDaoImpl ticketDao = ctx.getBean(HibernateTicketDaoImpl.class);
        HibernateEventCountDaoImpl eventCountDao = ctx.getBean(HibernateEventCountDaoImpl.class);
        HibernateUserDiscountCountDaoImpl userDiscountCountDao = ctx.getBean(HibernateUserDiscountCountDaoImpl.class);
        UserService userService = ctx.getBean(UserService.class);

        System.out.println(ticketDao.getPurchasedTicketsForEvent(hibernateEventDao.getById(12l), LocalDateTime.now()));

//        System.out.println(ticketDao.getAll());




        //        Bootstrap.main(args);
    }
}
