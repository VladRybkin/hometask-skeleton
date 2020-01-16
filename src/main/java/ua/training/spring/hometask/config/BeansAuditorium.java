package ua.training.spring.hometask.config;

import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ua.training.spring.hometask.domain.Auditorium;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.service.AuditoriumService;
import ua.training.spring.hometask.service.impl.DefaultAuditoriumService;

import java.util.Set;


@Configuration
@PropertySource("classpath:local.properties")
public class BeansAuditorium {

    @Value("${first.auditorium.seats.number}")
    private String greenAuditoriumNumberOfSeats;

    @Value("${second.auditorium.seats.number}")
    private String redAuditoriumNumberOfSeats;

    @Value("${vip.seats}")
    private Set<String> vipSeats;


    @Bean
    public Auditorium greenAuditorium() {
        Auditorium greenAuditorium = new Auditorium();
        greenAuditorium.setName("green auditorium");
        greenAuditorium.setNumberOfSeats(Long.parseLong(greenAuditoriumNumberOfSeats));
        greenAuditorium.setVipSeats(vipSeats);

        return greenAuditorium;
    }

    @Bean
    public Auditorium redAuditorium() {
        Auditorium redAuditorium = new Auditorium();
        redAuditorium.setName("red auditorium");
        redAuditorium.setNumberOfSeats(Long.parseLong(redAuditoriumNumberOfSeats));
        redAuditorium.setVipSeats(vipSeats);

        return redAuditorium;
    }

    @Bean
    public Set<Auditorium> auditoriums() {
        Set<Auditorium> auditoriums = Sets.newHashSet();
        auditoriums.add(redAuditorium());
        auditoriums.add(greenAuditorium());

        return auditoriums;
    }

    @Bean
    public AuditoriumService auditoriumService() {
        return new DefaultAuditoriumService(auditoriums());
    }




}
