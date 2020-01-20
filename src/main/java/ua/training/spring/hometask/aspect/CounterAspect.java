package ua.training.spring.hometask.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import ua.training.spring.hometask.domain.CountInfo;
import ua.training.spring.hometask.domain.Event;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;


@Aspect
@Component
public class CounterAspect {

    private static Map<String, CountInfo> getByNameEventCount = new HashMap<>(0);

    @Pointcut("execution(* ua.training.spring.hometask.service.impl.DefaultEventService.getByName(String)) ")
    public void setGetByNameEventCount() {
    }


    @AfterReturning(value = "setGetByNameEventCount())", returning = "event")
    public void printEventGetByNameCount( Event event) {

        System.out.println(event);


    }
}
