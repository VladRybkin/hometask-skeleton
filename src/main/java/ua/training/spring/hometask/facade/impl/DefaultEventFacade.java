package ua.training.spring.hometask.facade.impl;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.training.spring.hometask.domain.Auditorium;
import ua.training.spring.hometask.domain.Event;
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
import ua.training.spring.hometask.facade.EventFacade;
import ua.training.spring.hometask.service.AuditoriumService;
import ua.training.spring.hometask.service.EventService;

import java.time.LocalDateTime;
import java.util.List;

import static ua.training.spring.hometask.utils.ConvertUtil.convertDateTime;

@Component
public class DefaultEventFacade implements EventFacade {

    @Autowired
    private EventService eventService;

    @Autowired
    private AuditoriumService auditoriumService;

    @Override
    public void saveEvent(Event event, String eventDate, String auditoriumName) {
        Validate.notEmpty(eventDate, "eventDate is not present");
        Auditorium auditorium = auditoriumService.getByName(auditoriumName);
        LocalDateTime parsedTicketEventDate = convertDateTime(eventDate);
        event.getAuditoriums().put(parsedTicketEventDate, auditorium);
        event.getAirDates().add(parsedTicketEventDate);
        eventService.save(event);
    }

    @Override
    public AddEventResponse saveEvent(AddEventRequest addEventRequest) {
        String name = addEventRequest.getEventName();
        Validate.notBlank(name, "email should not be null");

        Event event = populateEventFromRequest(addEventRequest, name);
        eventService.save(event);

        AddEventResponse response = new AddEventResponse();
        EventResponse eventResponse = populateEventResponseFromEvent(event);
        response.setEventResponse(eventResponse);

        return response;
    }

    @Override
    public RemoveEventResponse removeEvent(RemoveEventRequest removeEventRequest) {
        Long id = removeEventRequest.getId();
        Validate.notNull(id, "id should not be null");
        Event event = eventService.getById(id);
        eventService.remove(event);

        RemoveEventResponse removeUserResponse = new RemoveEventResponse();
        removeUserResponse.setRemoved(true);

        return removeUserResponse;
    }

    @Override
    public GetEventByIdResponse getEventById(GetEventByIdRequest getEventByIdRequest) {
        Long id = getEventByIdRequest.getId();
        Validate.notNull(id, "id should not be null");
        Event event = eventService.getById(id);

        GetEventByIdResponse getEventByIdResponse = new GetEventByIdResponse();
        EventResponse eventResponse = populateEventResponseFromEvent(event);
        getEventByIdResponse.setEventResponse(eventResponse);

        return getEventByIdResponse;
    }

    @Override
    public GetEventByNameResponse getEventByName(GetEventByNameRequest getEventByNameRequest) {
        final String name = getEventByNameRequest.getEventName();
        Validate.notBlank(name, "eventName should not be null");
        Event event = eventService.getByName(name);

        GetEventByNameResponse getEventByNameResponse = new GetEventByNameResponse();
        EventResponse eventResponse = populateEventResponseFromEvent(event);
        getEventByNameResponse.setEventResponse(eventResponse);

        return getEventByNameResponse;
    }

    @Override
    public GetAllEventsResponse getAllEvents(GetAllEventsRequest getAllEventsRequest) {
        GetAllEventsResponse getAllUsersResponse = new GetAllEventsResponse();
        List<Event> events = Lists.newArrayList(eventService.getAll());
        List<EventResponse> eventResponses = Lists.newArrayList();

        events.forEach(event -> {
            EventResponse eventResponse = populateEventResponseFromEvent(event);
            eventResponses.add(eventResponse);
        });
        getAllUsersResponse.setEventResponses(eventResponses);

        return getAllUsersResponse;
    }

    private Event populateEventFromRequest(AddEventRequest addEventRequest, String name) {
        Event event = new Event();
        event.setName(name);
        event.setBasePrice(addEventRequest.getBasePrice());
        event.setRating(EventRating.valueOf(addEventRequest.getRating()));

        return event;
    }

    private EventResponse populateEventResponseFromEvent(Event event) {
        EventResponse eventResponse = new EventResponse();
        eventResponse.setId(event.getId());
        eventResponse.setName(event.getName());
        eventResponse.setBasePrice(event.getBasePrice());
        eventResponse.setRating(event.getRating());

        return eventResponse;
    }
}
