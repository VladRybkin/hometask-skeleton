package ua.training.spring.hometask.soapclient;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import ua.training.spring.hometask.config.WebMvcConfig;
import ua.training.spring.hometask.domain.EventRating;
import ua.training.spring.hometask.dto.soap.request.AddEventRequest;
import ua.training.spring.hometask.dto.soap.request.GetAllEventsRequest;
import ua.training.spring.hometask.dto.soap.request.GetEventByIdRequest;
import ua.training.spring.hometask.dto.soap.request.GetEventByNameRequest;
import ua.training.spring.hometask.dto.soap.request.RemoveEventRequest;
import ua.training.spring.hometask.dto.soap.response.AddEventResponse;
import ua.training.spring.hometask.dto.soap.response.EventResponse;
import ua.training.spring.hometask.dto.soap.response.GetAllEventsResponse;
import ua.training.spring.hometask.dto.soap.response.GetEventByIdResponse;
import ua.training.spring.hometask.dto.soap.response.GetEventByNameResponse;
import ua.training.spring.hometask.dto.soap.response.RemoveEventResponse;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = WebMvcConfig.class)
@ActiveProfiles({"IN_MEMORY", "TEST"})
@WebAppConfiguration
public class EventClientIntegrationTest {

    @Autowired
    private EventClient client;

    @Test
    void addEvent() {
        AddEventRequest addEventRequest = prepareAddEventRequest();

        AddEventResponse addEventResponse = client.addEventResponse(addEventRequest);
        EventResponse eventResponse = addEventResponse.getEventResponse();

        assertThat(eventResponse.getBasePrice(), is(addEventRequest.getBasePrice()));
        assertThat(eventResponse.getName(), is(addEventRequest.getName()));
        assertThat(eventResponse.getRating().name(), is(addEventRequest.getRating()));

        SendRemoveEventRequestToClearEvent(addEventResponse);
    }

    @Test
    void removeEvent() {
        AddEventResponse addEventResponse = prepareAndSendAddEventRequest();

        RemoveEventRequest removeEventRequest = new RemoveEventRequest();
        removeEventRequest.setId(addEventResponse.getEventResponse().getId());

        RemoveEventResponse removeEventResponse = client.removeEventResponse(removeEventRequest);

        assertThat(removeEventResponse.isRemoved(), is(true));
    }


    @Test
    void getEventByName() {
        AddEventResponse addEventResponse = prepareAndSendAddEventRequest();

        GetEventByNameRequest getEventByNameRequest = new GetEventByNameRequest();
        getEventByNameRequest.setEventName(addEventResponse.getEventResponse().getName());

        GetEventByNameResponse getEventByNameResponse = client.getEventByNameResponse(getEventByNameRequest);
        EventResponse eventResponse = getEventByNameResponse.getEventResponse();

        assertThat(eventResponse.getName(), is(addEventResponse.getEventResponse().getName()));
    }

    @Test
    void getEventById() {
        AddEventResponse addEventResponse = prepareAndSendAddEventRequest();
        GetEventByIdRequest getEventByIdRequest = new GetEventByIdRequest();
        getEventByIdRequest.setId(addEventResponse.getEventResponse().getId());

        GetEventByIdResponse getEventByIdResponse = client.getEventByIdResponse(getEventByIdRequest);
        EventResponse eventResponse = getEventByIdResponse.getEventResponse();

        assertThat(eventResponse.getId(), is(eventResponse.getId()));
    }

    @Test
    void getAllUsers() {
        GetAllEventsRequest getAllEventsRequest = new GetAllEventsRequest();
        GetAllEventsResponse getAllEventsResponse = client.getAllEventsResponse(getAllEventsRequest);

        List<EventResponse> userResponses = getAllEventsResponse.getEventResponses();

        assertThat(userResponses, not(empty()));
    }

    private AddEventResponse prepareAndSendAddEventRequest() {
        AddEventRequest eventRequest = prepareAddEventRequest();

        return client.addEventResponse(eventRequest);
    }

    private AddEventRequest prepareAddEventRequest() {
        AddEventRequest eventRequest = new AddEventRequest();
        eventRequest.setBasePrice(100);
        eventRequest.setName("testEventname");
        eventRequest.setRating(EventRating.LOW.name());

        return eventRequest;
    }

    private void SendRemoveEventRequestToClearEvent(AddEventResponse addEventResponse) {
        RemoveEventRequest removeEventRequest = new RemoveEventRequest();
        removeEventRequest.setId(addEventResponse.getEventResponse().getId());
        client.removeEventResponse(removeEventRequest);
    }
}
