package ua.training.spring.hometask.facade.impl;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.training.spring.hometask.domain.Auditorium;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.facade.EventFacade;
import ua.training.spring.hometask.service.AuditoriumService;
import ua.training.spring.hometask.service.EventService;

import java.time.LocalDateTime;

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
}
