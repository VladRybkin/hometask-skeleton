package ua.training.spring.hometask.facade.impl;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.EventRating;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.service.EventService;
import ua.training.spring.hometask.service.UserService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DefaultUploadFacadeTest {

    private final String JSON_FILE_CONTENT = "{\n" +
            "\t\t\"users\" :\t[{\n" +
            "\t\t\t  \"firstName\" : \"firstName1\",\n" +
            "              \"lastName\" : \"lastName1\",\n" +
            "\t\t\t  \"email\": \"json@email1\",\n" +
            "\t\t\t  \"dateOfBirth\": \"2017-01-01T11:00\"\n" +
            "\t\t\t  \n" +
            "            },\n" +
            "\t\t\t{\n" +
            "\t\t\t  \"firstName\" : \"firstName2\",\n" +
            "              \"lastName\" : \"lastName2\",\n" +
            "\t\t\t  \"email\" : \"json@email2\",\n" +
            "\t\t\t  \"dateOfBirth\": \"2017-01-01T11:00\"\n" +
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
            "}   \n" +
            "\n";

    @InjectMocks
    private DefaultUploadFacade uploadFacade;

    @Mock
    private EventService eventService;

    @Mock
    private UserService userService;

    @Test
    void saveDataFromJsonFile() throws IOException {
        MockMultipartFile jsonFile = new MockMultipartFile("test.json", "", "application/json", JSON_FILE_CONTENT.getBytes());

        User user1 = buildFirstUser();
        User user2 = buildSecondUser();

        Event event1 = buildFirstEvent();
        Event event2 = buildSecondEvent();

        List<User> users = Lists.newArrayList(user1, user2);
        List<Event> events = Lists.newArrayList(event1, event2);

        uploadFacade.saveDataFromJsonFile(jsonFile);

        verify(userService).saveAll(users);
        verify(eventService).saveAll(events);
    }

    private User buildFirstUser() {
        User user = new User();
        user.setFirstName("firstName1");
        user.setLastName("lastName1");
        user.setEmail("json@email1");
        setDateOfBirthToUser(user);
        return user;
    }

    private User buildSecondUser() {
        User user = new User();
        user.setFirstName("firstName2");
        user.setLastName("lastName2");
        user.setEmail("json@email2");
        setDateOfBirthToUser(user);
        return user;
    }

    private Event buildFirstEvent() {
        Event event = new Event();
        event.setName("event1");
        event.setBasePrice(150);
        event.setRating(EventRating.HIGH);

        return event;
    }

    private Event buildSecondEvent() {
        Event event = new Event();
        event.setName("event2");
        event.setBasePrice(200);
        event.setRating(EventRating.LOW);

        return event;
    }

    private void setDateOfBirthToUser(User user) {
        LocalDateTime birthday = LocalDateTime.of(2017, 01, 01, 11, 00);
        user.setDateOfBirth(birthday);
    }
}