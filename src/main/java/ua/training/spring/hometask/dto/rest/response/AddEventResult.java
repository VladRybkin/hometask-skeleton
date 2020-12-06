package ua.training.spring.hometask.dto.rest.response;

import ua.training.spring.hometask.domain.EventRating;

public class AddEventResult {

    private String name;

    private double basePrice;

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
