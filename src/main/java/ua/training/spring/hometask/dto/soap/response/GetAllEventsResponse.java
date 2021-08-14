package ua.training.spring.hometask.dto.soap.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "getAllEventsResponse", namespace = "http://training/schemas/hometask")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class GetAllEventsResponse {

    @XmlElement
    private List<EventResponse> eventResponses = new ArrayList<>();

    public List<EventResponse> getEventResponses() {
        return eventResponses;
    }

    public void setEventResponses(List<EventResponse> eventResponses) {
        this.eventResponses = eventResponses;
    }
}
