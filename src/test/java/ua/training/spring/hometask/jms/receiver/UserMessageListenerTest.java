package ua.training.spring.hometask.jms.receiver;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import ua.training.spring.hometask.config.WebMvcConfig;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.jms.sender.UserJmsSender;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = WebMvcConfig.class)
@ActiveProfiles({"IN_MEMORY", "TEST"})
@WebAppConfiguration
class UserMessageListenerTest {

    @Autowired
    private UserJmsSender userJmsSender;

    @Test
    void onMessage() {
        User user =new User("testEmail");
        userJmsSender.sendMessage(user);
    }
}