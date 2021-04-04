package ua.training.spring.hometask.jms.receiver;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import ua.training.spring.hometask.domain.User;

public class UserMessageListener implements MessageListener {

    @Autowired
    private Jackson2JsonMessageConverter jackson2JsonMessageConverter;

    public void onMessage(Message message) {
        System.out.println("User message listener <received message " + message);
        User user = (User) jackson2JsonMessageConverter.fromMessage(message);
        System.out.println("parsed user " + user);
    }
}