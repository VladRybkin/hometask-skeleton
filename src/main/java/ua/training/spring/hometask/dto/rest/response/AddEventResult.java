package ua.training.spring.hometask.dto.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.training.spring.hometask.domain.EventRating;

public class AddEventResult {

    private Long id;

    private String name;

    private double basePrice;

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
