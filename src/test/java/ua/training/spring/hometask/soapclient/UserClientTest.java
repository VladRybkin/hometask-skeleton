package ua.training.spring.hometask.soapclient;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import ua.training.spring.hometask.config.WebMvcConfig;
import ua.training.spring.hometask.dto.soap.response.GetUserByIdResponse;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = WebMvcConfig.class)
@ActiveProfiles({"TEST", "IN_MEMORY"})
@WebAppConfiguration
public class UserClientTest {

    @Autowired
    private UserClient client;

    @Test
    public void getUserById() {
        GetUserByIdResponse response = client.getUserByIdResponse(1L);
        System.out.println(response);
    }

}
