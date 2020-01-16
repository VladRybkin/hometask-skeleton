package ua.training.spring.hometask;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.shell.Bootstrap;
import ua.training.spring.hometask.config.BeansConfiguration;
import ua.training.spring.hometask.service.DiscountService;


public class Main {


    public static void main(String[] args) throws Exception {
        //        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:auditorium.xml", "discount-strategies.xml");
        //        System.out.println(applicationContext.getId());
        //        System.out.println(applicationContext.getBean(UserService.class));
                AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

                ctx.register(BeansConfiguration.class);

                ctx.refresh();

                System.out.println(ctx.getBean(DiscountService.class));
//        Bootstrap.main(args);
    }


}
