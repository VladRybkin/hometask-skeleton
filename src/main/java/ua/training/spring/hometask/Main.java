package ua.training.spring.hometask;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.shell.Bootstrap;
import ua.training.spring.hometask.config.BeansAuditorium;
import ua.training.spring.hometask.config.BeansDiscountService;
import ua.training.spring.hometask.domain.Auditorium;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.service.AuditoriumService;
import ua.training.spring.hometask.service.DiscountService;
import ua.training.spring.hometask.service.impl.DefaultDiscountService;

import java.util.Set;


public class Main {


    public static void main(String[] args) throws Exception {
//        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:auditorium.xml", "discount-strategies.xml");
//        System.out.println(applicationContext.getId());
//        System.out.println(applicationContext.getBean(UserService.class));
//        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
//
//        ctx.register(BeansAuditorium.class);
//        ctx.register(BeansDiscountService.class);
//        ctx.refresh();
//
//        System.out.println(ctx.getBean(DiscountService.class));

        Bootstrap.main(args);
    }




}
