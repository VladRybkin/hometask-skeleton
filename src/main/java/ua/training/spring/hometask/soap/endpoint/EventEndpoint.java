package ua.training.spring.hometask.soap.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ua.training.spring.hometask.dto.soap.request.AddEventRequest;
import ua.training.spring.hometask.dto.soap.request.GetAllEventsRequest;
import ua.training.spring.hometask.dto.soap.request.GetEventByIdRequest;
import ua.training.spring.hometask.dto.soap.request.GetEventByNameRequest;
import ua.training.spring.hometask.dto.soap.request.RemoveEventRequest;
import ua.training.spring.hometask.dto.soap.response.AddEventResponse;
import ua.training.spring.hometask.dto.soap.response.GetAllEventsResponse;
import ua.training.spring.hometask.dto.soap.response.GetEventByIdResponse;
import ua.training.spring.hometask.dto.soap.response.GetEventByNameResponse;
import ua.training.spring.hometask.dto.soap.response.RemoveEventResponse;
import ua.training.spring.hometask.facade.EventFacade;

@Endpoint
public class EventEndpoint {

    @Autowired
    private EventFacade eventFacade;

    private static final String NAMESPACE_URI = "http://training/schemas/hometask";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addEventRequest")
    @ResponsePayload
    public AddEventResponse saveEvent(@RequestPayload AddEventRequest addEventRequest) {
        return eventFacade.saveEvent(addEventRequest);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "removeEventRequest")
    @ResponsePayload
    public RemoveEventResponse removeEvent(@RequestPayload RemoveEventRequest removeEventRequest) {
        return eventFacade.removeEvent(removeEventRequest);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEventByIdRequest")
    @ResponsePayload
    public GetEventByIdResponse getEventById(@RequestPayload GetEventByIdRequest getEventByIdRequest) {
        return eventFacade.getEventById(getEventByIdRequest);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEventByNameRequest")
    @ResponsePayload
    public GetEventByNameResponse getEventByName(@RequestPayload GetEventByNameRequest getEventByNameRequest) {
        return eventFacade.getEventByName(getEventByNameRequest);
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllEventsRequest")
    @ResponsePayload
    public GetAllEventsResponse getAllEvents(@RequestPayload GetAllEventsRequest getAllEventsRequest) {
        return eventFacade.getAllEvents(getAllEventsRequest);
    }
}
