package ua.training.spring.hometask.rest.client;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import ua.training.spring.hometask.config.WebMvcConfig;
import ua.training.spring.hometask.domain.EventRating;
import ua.training.spring.hometask.dto.rest.request.AddEventParameter;
import ua.training.spring.hometask.dto.rest.response.AddEventResult;
import ua.training.spring.hometask.dto.rest.response.EventResponse;

import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyCollection.empty;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = WebMvcConfig.class)
@ActiveProfiles({"IN_MEMORY", "TEST"})
@WebAppConfiguration
class EventOperationsClientIntegrationTest {

    @Autowired
    private EventOperationsClient eventOperationsClient;

    @Test
    void addEventRequest() {
        AddEventParameter addEventParameter = new AddEventParameter();
        addEventParameter.setName("testEvent");
        addEventParameter.setBasePrice(100);
        addEventParameter.setRating(EventRating.HIGH);

        AddEventResult eventResult = eventOperationsClient.addEventRequest(addEventParameter);

        assertThat(eventResult.getBasePrice(), is(addEventParameter.getBasePrice()));
        assertThat(eventResult.getName(), is(addEventParameter.getName()));
        assertThat(eventResult.getRating(), is(addEventParameter.getRating()));
        assertThat(eventResult.getId(), not(nullValue()));
    }

    @Test
    void getEventByIdRequest() {
        AddEventParameter addEventParameter = new AddEventParameter();
        addEventParameter.setName("testEvent");
        addEventParameter.setBasePrice(100);
        addEventParameter.setRating(EventRating.HIGH);

        AddEventResult eventResult = eventOperationsClient.addEventRequest(addEventParameter);
        EventResponse eventResponse = eventOperationsClient.getEventByIdRequest(eventResult.getId());

        assertThat(eventResponse.getBasePrice(), is(addEventParameter.getBasePrice()));
        assertThat(eventResponse.getName(), is(addEventParameter.getName()));
        assertThat(eventResponse.getRating(), is(addEventParameter.getRating()));
        assertThat(eventResponse.getId(), is(eventResult.getId()));
    }

    @Test
    void removeEventByIdRequest() {
        AddEventParameter addEventParameter = new AddEventParameter();
        addEventParameter.setName("testEvent");
        addEventParameter.setBasePrice(100);
        addEventParameter.setRating(EventRating.HIGH);

        AddEventResult eventResult = eventOperationsClient.addEventRequest(addEventParameter);
        eventOperationsClient.removeEventByIdRequest(eventResult.getId());

    }

    @Test
    void getEventByNameRequest() {
        AddEventParameter addEventParameter = new AddEventParameter();
        addEventParameter.setName("testGetByNameEvent");
        addEventParameter.setBasePrice(100);
        addEventParameter.setRating(EventRating.HIGH);

        AddEventResult eventResult = eventOperationsClient.addEventRequest(addEventParameter);
        EventResponse eventResponse = eventOperationsClient.getEventByNameRequest(addEventParameter.getName());

        assertThat(eventResponse.getBasePrice(), is(addEventParameter.getBasePrice()));
        assertThat(eventResponse.getName(), is(addEventParameter.getName()));
        assertThat(eventResponse.getRating(), is(addEventParameter.getRating()));
        assertThat(eventResponse.getId(), is(eventResult.getId()));
    }

    @Test
    void getAllEventsRequest() {
        AddEventParameter addEventParameter = new AddEventParameter();
        addEventParameter.setName("testEvent");
        addEventParameter.setBasePrice(100);
        addEventParameter.setRating(EventRating.HIGH);

        eventOperationsClient.addEventRequest(addEventParameter);
        List<EventResponse> eventResponses = Lists.newArrayList(eventOperationsClient.getAllEventsRequest());

        assertThat(eventResponses, not(empty()));
    }
}