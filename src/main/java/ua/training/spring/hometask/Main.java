package ua.training.spring.hometask;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.training.spring.hometask.dao.AbstractDomainObjectDao;
import ua.training.spring.hometask.dao.impl.UserDaoImpl;
import ua.training.spring.hometask.domain.User;

public class Main {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        User user=applicationContext.getBean("vlad" ,User.class);
        System.out.println(user);
        AbstractDomainObjectDao<User> dao= applicationContext.getBean(UserDaoImpl.class);
        dao.save(new User());
        System.out.println(dao.getAll());

    }

}
