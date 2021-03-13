package ua.training.spring.hometask.jms.receiver;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

public class MyMessageListener implements MessageListener {

    public void onMessage(Message message) {
        System.out.println(message);
        System.out.println(message.getBody());
    }
}