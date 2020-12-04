package ua.training.spring.hometask.controller.restcontroller;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.dto.rest.request.AddEventParameter;
import ua.training.spring.hometask.dto.rest.response.AddEventResult;
import ua.training.spring.hometask.facade.EventFacade;
import ua.training.spring.hometask.service.EventService;

import java.util.List;

@RestController
@RequestMapping("/operations/events")
public class EventOperationsController {

    @Autowired
    private EventFacade eventFacade;

    @Autowired
    private EventService eventService;


    @PostMapping
    public AddEventResult addEvent(AddEventParameter addEventParameter) {
        return eventFacade.saveEvent(addEventParameter);
    }

    @PostMapping(value = "/remove/{id}")
    public ResponseEntity remove(@PathVariable Long id) {
        eventService.remove(eventService.getById(id));

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/getbyid/{id}")
    public Event getById(@PathVariable Long id) {
        return eventService.getById(id);
    }

    @GetMapping(value = "/getbyemail")
    public Event getByName(@RequestParam String name) {
        return eventService.getByName(name);
    }

    @GetMapping(value = "/getAll")
    public List<Event> getAllEvents() {
        return Lists.newArrayList(eventService.getAll());
    }

}
