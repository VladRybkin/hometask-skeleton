package ua.training.spring.hometask;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.training.spring.hometask.config.BeansConfiguration;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.service.*;


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
        userService.save(user);
        DiscountService discountService = ctx.getBean(DiscountService.class);
        UserDiscountCountService userDiscountCountService = ctx.getBean(UserDiscountCountService.class);

        //        Bootstrap.main(args);

    }


}
