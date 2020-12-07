package ua.training.spring.hometask.rest.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ua.training.spring.hometask.dto.rest.response.EventResponse;

@Component
public class EventOperationsClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpHeaders jsonRequestHeaders;

    public EventResponse getEventByIdRequest(long id) {
        return restTemplate.getForObject("http://localhost:8888/operations/events/getbyid/" + id, EventResponse.class, jsonRequestHeaders);
    }

    public void removeEventByIdRequest(long id) {
        restTemplate.delete("http://localhost:8888/operations/events/getbyid/" + id);
    }

    public EventResponse getEventByNameRequest(String name) {
        return restTemplate.getForObject("http://localhost:8888/operations/events/getbyname?name=" + name, EventResponse.class, jsonRequestHeaders);
    }

    public EventResponse[] getAllEventsRequest() {
        return restTemplate.getForObject("http://localhost:8888/operations/events/getAll", EventResponse[].class, jsonRequestHeaders);
    }

}
