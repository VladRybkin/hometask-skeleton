package ua.training.spring.hometask.dto.soap.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getEventByIdResponse", namespace = "http://training/schemas/hometask")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class GetEventByIdResponse {

    private EventResponse eventResponse;

    public EventResponse getEventResponse() {
        return eventResponse;
    }

    public void setEventResponse(EventResponse eventResponse) {
        this.eventResponse = eventResponse;
    }
}
