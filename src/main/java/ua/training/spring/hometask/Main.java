package ua.training.spring.hometask;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.training.spring.hometask.config.BeansConfiguration;
import ua.training.spring.hometask.dao.impl.hibernate.HibernateUserDaoImpl;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.service.UserService;


public class Main {

    public static void main(String[] args) throws Exception {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

        ctx.register(BeansConfiguration.class);
        ctx.refresh();
        HibernateUserDaoImpl hibernateUserDao = ctx.getBean(HibernateUserDaoImpl.class);
        UserService userService=ctx.getBean(UserService.class);
        User user = new User();
        user.setEmail("hibernateEmail1");
        user.setFirstName("Hibernate");
//        userService.save(user);
        System.out.println( hibernateUserDao.getAll());
        System.out.println(hibernateUserDao.getById(8L));


        //        Bootstrap.main(args);
    }
}
