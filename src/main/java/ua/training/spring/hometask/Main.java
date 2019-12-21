package ua.training.spring.hometask;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.training.spring.hometask.dao.AbstractDomainObjectDao;
import ua.training.spring.hometask.dao.impl.UserDaoImpl;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.service.UserService;
import ua.training.spring.hometask.service.impl.UserServiceImpl;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        UserService userService = applicationContext.getBean(UserServiceImpl.class);
        System.out.println(userService.getAll());

        List list= Arrays.asList(1, 4, 5, 6);
        List list1=Arrays.asList(1, 4, 5, 6);

        System.out.println(list.containsAll(list1));


    }

}
