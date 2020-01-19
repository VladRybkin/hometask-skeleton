package ua.training.spring.hometask.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;


@Aspect
@Component
public class CounterAspect {

    private AtomicLong getByNameEventCount = new AtomicLong(0);

    @Pointcut("execution(* ua.training.spring.hometask.service.impl.DefaultEventService.getByName(String))")
    public void setGetByNameEventCount() {
    }


    @After("setGetByNameEventCount()")
    public void printEventGetByNameCount(JoinPoint joinPoint) {
        getByNameEventCount.getAndIncrement();

        System.out.println("advice method called count = "
                + getByNameEventCount + " in "
                + joinPoint.getTarget().getClass()
                +"with args "+Arrays.toString(joinPoint.getArgs()));

    }

}
