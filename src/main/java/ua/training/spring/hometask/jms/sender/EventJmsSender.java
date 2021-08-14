package ua.training.spring.hometask.jms.sender;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ua.training.spring.hometask.domain.Event;

@Component
public class EventJmsSender {

    @Value("${routing.key}")
    private String routingKey;

    @Value("${event.exchange.name}")
    private String eventExchangeName;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(Event event) {
        System.out.println("Jms Message Sender : " + event);
        rabbitTemplate.convertAndSend(eventExchangeName, routingKey, event);
    }
}
