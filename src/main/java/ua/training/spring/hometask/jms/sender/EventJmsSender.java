package ua.training.spring.hometask.jms.sender;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.training.spring.hometask.domain.Event;

@Component
public class EventJmsSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(Event event) {
        System.out.println("Jms Message Sender : " + event);
        rabbitTemplate.convertAndSend("event-exchange", "foo.bar.baz", event);
    }
}
