package ua.training.spring.hometask;


import com.google.common.base.Objects;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.shell.Bootstrap;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.service.UserService;


public class Main {


    public static void main(String[] args) throws Exception {
//        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:auditorium.xml", "discount-strategies.xml");
//        System.out.println(applicationContext.getId());
//        System.out.println(applicationContext.getBean(UserService.class));


        Bootstrap.main(args);
    }


}
