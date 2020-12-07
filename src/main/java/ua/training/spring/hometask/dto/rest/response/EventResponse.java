package ua.training.spring.hometask.dto.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.training.spring.hometask.domain.EventRating;

public class EventResponse {

    @JsonProperty
    private String name;

    @JsonProperty
    private double basePrice;

    @JsonProperty
    private EventRating rating;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public EventRating getRating() {
        return rating;
    }

    public void setRating(EventRating rating) {
        this.rating = rating;
    }
}
