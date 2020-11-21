package ua.training.spring.hometask.dto.soap.response;

import ua.training.spring.hometask.domain.EventRating;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "eventResponse", namespace = "http://training/schemas/hometask")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class EventResponse {

    @XmlElement
    private Long id;

    @XmlElement
    private String name;

    @XmlElement
    private Double basePrice;

    @XmlElement
    private EventRating rating;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public EventRating getRating() {
        return rating;
    }

    public void setRating(EventRating rating) {
        this.rating = rating;
    }
}
