package ua.training.spring.hometask;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.training.spring.hometask.domain.Auditorium;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.service.AuditoriumService;
import ua.training.spring.hometask.service.DiscountService;
import ua.training.spring.hometask.service.strategy.DiscountStrategy;

import java.time.LocalDateTime;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Auditorium.xml", "DiscountStrategies.xml");



        Auditorium auditorium = applicationContext.getBean("auditorium1", Auditorium.class);
        AuditoriumService auditoriumService=applicationContext.getBean(AuditoriumService.class);
        System.out.println(auditoriumService.getAll());
        DiscountService discountService=applicationContext.getBean(DiscountService.class);









    }

}
