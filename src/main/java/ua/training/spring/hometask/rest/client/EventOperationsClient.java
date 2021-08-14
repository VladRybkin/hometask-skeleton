package ua.training.spring.hometask.rest.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ua.training.spring.hometask.dto.rest.request.AddEventParameter;
import ua.training.spring.hometask.dto.rest.response.AddEventResult;
import ua.training.spring.hometask.dto.rest.response.EventResponse;

@Component
public class EventOperationsClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpHeaders jsonRequestHeaders;

    public AddEventResult addEventRequest(AddEventParameter addEventParameter) {
        HttpEntity<AddEventParameter> request = new HttpEntity<>(addEventParameter, jsonRequestHeaders);

        return restTemplate.postForObject("http://localhost:8888/operations/events/add", request, AddEventResult.class);
    }

    public EventResponse getEventByIdRequest(long id) {
        return restTemplate.getForObject("http://localhost:8888/operations/events/getbyid/" + id, EventResponse.class, jsonRequestHeaders);
    }

    public void removeEventByIdRequest(long id) {
        restTemplate.delete("http://localhost:8888/operations/events/remove/" + id);
    }

    public EventResponse getEventByNameRequest(String name) {
        return restTemplate.getForObject("http://localhost:8888/operations/events/getbyname?name=" + name, EventResponse.class, jsonRequestHeaders);
    }

    public EventResponse[] getAllEventsRequest() {
        return restTemplate.getForObject("http://localhost:8888/operations/events/getAll", EventResponse[].class, jsonRequestHeaders);
    }
}
