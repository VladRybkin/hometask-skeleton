package ua.training.spring.hometask;


import org.springframework.shell.Bootstrap;


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
