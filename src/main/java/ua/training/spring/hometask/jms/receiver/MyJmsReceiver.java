package ua.training.spring.hometask.jms.receiver;

import org.springframework.stereotype.Component;

@Component
public class MyJmsReceiver {

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
    }
}
