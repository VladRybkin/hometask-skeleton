package ua.training.spring.hometask;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.training.spring.hometask.domain.Auditorium;

import ua.training.spring.hometask.service.AuditoriumService;
import ua.training.spring.hometask.service.DiscountService;
import ua.training.spring.hometask.service.strategy.BirthdayDiscountStrategy;
import ua.training.spring.hometask.service.strategy.TenthTicketDiscountStrategy;


public class Main {


    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("auditorium.xml",
                "discount-strategies.xml");
//        Bootstrap.main(args);
        Auditorium auditorium = applicationContext.getBean("auditorium1", Auditorium.class);
        AuditoriumService auditoriumService = applicationContext.getBean(AuditoriumService.class);
        System.out.println(auditoriumService.getAll());
        DiscountService discountService = applicationContext.getBean(DiscountService.class);
        System.out.println(discountService);
        BirthdayDiscountStrategy birthdayDiscountStrategy=applicationContext.getBean(BirthdayDiscountStrategy.class);
        TenthTicketDiscountStrategy tenthti=applicationContext.getBean(TenthTicketDiscountStrategy.class);
        System.out.println(birthdayDiscountStrategy.getBirthdayDiscount());
        System.out.println(tenthti.getTenthTicketDiscount());


    }

}
