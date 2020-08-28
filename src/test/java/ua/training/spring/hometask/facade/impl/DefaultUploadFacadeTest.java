package ua.training.spring.hometask.facade.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import ua.training.spring.hometask.config.BeansConfiguration;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.EventRating;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.facade.UploadFacade;
import ua.training.spring.hometask.service.EventService;
import ua.training.spring.hometask.service.UserService;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BeansConfiguration.class)
@ActiveProfiles({"WEB_MVC", "TEST", "IN_MEMORY"})
@WebAppConfiguration
class DefaultUploadFacadeTest {

    private final String JSON_FILE_CONTENT = "{\n" +
            "\t\t\"users\" :\t[{\n" +
            "\t\t\t  \"firstName\" : \"firstName1\",\n" +
            "              \"lastName\" : \"lastName1\",\n" +
            "\t\t\t  \"email\": \"json@email1\"\n" +
            "            },\n" +
            "\t\t\t{\n" +
            "\t\t\t  \"firstName\" : \"firstName2\",\n" +
            "              \"lastName\" : \"lastName2\",\n" +
            "\t\t\t  \"email\" : \"json@email2\"\n" +
            "            }\n" +
            "\t\t\t],\n" +
            "\t\t\t\"events\" :\t[{\n" +
            "              \"name\" : \"event1\",\n" +
            "\t\t\t  \"basePrice\" : 150,\n" +
            "\t\t\t  \"rating\" : \"HIGH\"\n" +
            "            },\n" +
            "\t\t\t{\n" +
            "              \"name\":\"event2\",\n" +
            "\t\t\t  \"basePrice\" : 200,\n" +
            "\t\t\t  \"rating\" : \"LOW\"\n" +
            "            }\n" +
            "\t\t\t]\t\t\n" +
            "}   \n";

    @Autowired
    private UploadFacade uploadFacade;

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @Test
    void saveDataFromJsonFile() throws IOException {
        MockMultipartFile jsonFile = new MockMultipartFile("test.json", "", "application/json", JSON_FILE_CONTENT.getBytes());

        assertThat(userService.getUserByEmail("json@email1"), nullValue());
        assertThat(userService.getUserByEmail("json@email2"), nullValue());

        assertThat(eventService.getByName("event1"), nullValue());
        assertThat(eventService.getByName("event2"), nullValue());

        uploadFacade.saveDataFromJsonFile(jsonFile);

        User importedUser1 = userService.getUserByEmail("json@email1");
        User importedUser2 = userService.getUserByEmail("json@email2");

        Event importedEvent1 = eventService.getByName("event1");
        Event importedEvent2 = eventService.getByName("event2");

        assertThat(importedUser1.getEmail(), is("json@email1"));
        assertThat(importedUser1.getFirstName(), is("firstName1"));
        assertThat(importedUser1.getLastName(), is("lastName1"));

        assertThat(importedUser2.getFirstName(), is("firstName2"));
        assertThat(importedUser2.getEmail(), is("json@email2"));
        assertThat(importedUser2.getLastName(), is("lastName2"));

        assertThat(importedEvent1.getName(), is("event1"));
        assertThat(importedEvent1.getBasePrice(), is(150D));
        assertThat(importedEvent1.getRating(), is(EventRating.HIGH));

        assertThat(importedEvent2.getName(), is("event2"));
        assertThat(importedEvent2.getBasePrice(), is(200D));
        assertThat(importedEvent2.getRating(), is(EventRating.LOW));
    }
}