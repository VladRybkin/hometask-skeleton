package ua.training.spring.hometask.jms.sender;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import ua.training.spring.hometask.config.WebMvcConfig;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.User;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = WebMvcConfig.class)
@ActiveProfiles({"IN_MEMORY", "TEST"})
@WebAppConfiguration
class EventJmsSenderTest {

    @Autowired
    private UserJmsSender userJmsSender;

    @Autowired
    private EventJmsSender eventJmsSender;

    @Test
    void shouldSendEventMessage() {
        Event event = new Event("testEventName");
        eventJmsSender.sendMessage(event);
    }
}