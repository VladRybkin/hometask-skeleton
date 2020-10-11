package ua.training.spring.hometask.facade;

import ua.training.spring.hometask.domain.Event;

public interface EventFacade {

    void saveEvent(Event event, String eventDate, String auditoriumName);
}
