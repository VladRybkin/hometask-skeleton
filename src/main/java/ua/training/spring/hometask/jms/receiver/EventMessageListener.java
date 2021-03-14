package ua.training.spring.hometask.jms.receiver;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import ua.training.spring.hometask.domain.Event;

public class EventMessageListener implements MessageListener {


    @Autowired
    private Jackson2JsonMessageConverter jackson2JsonMessageConverter;

    @Override
    public void onMessage(Message message) {
        System.out.println("<received message " + message);
        Event event = (Event) jackson2JsonMessageConverter.fromMessage(message);
        System.out.println("parsed event " + event);
    }
}
