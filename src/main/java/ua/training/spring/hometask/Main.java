package ua.training.spring.hometask;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Main {

    public static void main(String[] args) throws Exception {
        LocalDateTime localDateTime=LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        System.out.println(localDateTime);
        
//        Bootstrap.main(args);
    }
}
