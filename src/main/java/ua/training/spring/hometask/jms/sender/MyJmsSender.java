package ua.training.spring.hometask.jms.sender;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import ua.training.spring.hometask.domain.User;

import javax.jms.Queue;
import java.util.HashMap;
import java.util.Map;

@Component
public class MyJmsSender {

    private JmsTemplate jmsTemplate;
    private Queue queue;


    public void sendMessage(User user) {
        System.out.println("Jms Message Sender : " + user);
        Map<String, Object> map = new HashMap<>();
        map.put("email", user.getEmail());
        jmsTemplate.convertAndSend(map);
    }
}
