package ua.training.spring.hometask;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.training.spring.hometask.domain.Auditorium;
import ua.training.spring.hometask.service.AuditoriumService;

public class Main {

    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Auditorium auditorium = applicationContext.getBean("auditorium1", Auditorium.class);
        AuditoriumService auditoriumService=applicationContext.getBean(AuditoriumService.class);
        System.out.println(auditorium);




    }

}
