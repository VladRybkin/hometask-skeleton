package ua.training.spring.hometask.jms.sender;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.training.spring.hometask.domain.User;

@Component
public class MyJmsSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(User user) {
        System.out.println("Jms Message Sender : " + user);
        rabbitTemplate.convertAndSend(user);
    }
}
