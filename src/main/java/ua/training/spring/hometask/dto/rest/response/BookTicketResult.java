package ua.training.spring.hometask.dto.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookTicketResult {

    @JsonProperty
    private String eventName;

    @JsonProperty
    private long seat;

    @JsonProperty
    private double basePrice;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public long getSeat() {
        return seat;
    }

    public void setSeat(long seat) {
        this.seat = seat;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }
}
