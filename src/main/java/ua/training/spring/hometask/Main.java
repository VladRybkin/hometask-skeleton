package ua.training.spring.hometask;


import org.apache.commons.lang3.Validate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.shell.Bootstrap;
import ua.training.spring.hometask.domain.Auditorium;

import ua.training.spring.hometask.service.AuditoriumService;
import ua.training.spring.hometask.service.DiscountService;
import ua.training.spring.hometask.service.strategy.BirthdayDiscountStrategy;
import ua.training.spring.hometask.service.strategy.TenthTicketDiscountStrategy;
import ua.training.spring.hometask.shellCommands.UserCommand;


public class Main {


    public static void main(String[] args) throws Exception {
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("auditorium.xml",
//                "discount-strategies.xml", "META-INF/spring/spring-shell-plugin.xml");
                Bootstrap.main(args);
//        Auditorium auditorium = applicationContext.getBean("auditorium1", Auditorium.class);
//        AuditoriumService auditoriumService = applicationContext.getBean(AuditoriumService.class);
//        System.out.println(applicationContext.getBean(UserCommand.class));
//        System.out.println(auditoriumService.getAll());




    }


}
