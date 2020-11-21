package ua.training.spring.hometask.facade;

import ua.training.spring.hometask.domain.Event;
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

public interface EventFacade {

    void saveEvent(Event event, String eventDate, String auditoriumName);

    AddEventResponse saveEvent(AddEventRequest addEventRequest);

    RemoveEventResponse removeEvent(RemoveEventRequest removeEventRequest);

    GetEventByIdResponse getEventById(GetEventByIdRequest getEventByIdRequest);

    GetEventByNameResponse getEventByName(GetEventByNameRequest getEventByNameRequest);

    GetAllEventsResponse getAllEvents(GetAllEventsRequest getAllEventsRequest);
}
