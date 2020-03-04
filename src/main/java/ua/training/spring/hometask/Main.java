package ua.training.spring.hometask;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.shell.Bootstrap;
import ua.training.spring.hometask.config.BeansConfiguration;
import ua.training.spring.hometask.dao.impl.hibernate.HibernateUserDaoImpl;


public class Main {

    public static void main(String[] args) throws Exception {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

        ctx.register(BeansConfiguration.class);
        ctx.refresh();
        ctx.getBean(HibernateUserDaoImpl.class).getAll();


//        Bootstrap.main(args);
    }
}
