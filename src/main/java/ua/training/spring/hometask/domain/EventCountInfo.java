package ua.training.spring.hometask.domain;


import com.google.common.base.Objects;

public class EventCountInfo extends DomainObject {

    private String eventName;

    private long countGetByName;

    private long countBookTickets;

    private long countGetPrice;


    public long getCountGetByName() {
        return countGetByName;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setCountGetByName(long countGetByName) {
        this.countGetByName = countGetByName;
    }

    public long getCountBookTickets() {
        return countBookTickets;
    }

    public void setCountBookTickets(long countBookTickets) {
        this.countBookTickets = countBookTickets;
    }

    public long getCountGetPrice() {
        return countGetPrice;
    }

    public void setCountGetPrice(long countGetPrice) {
        this.countGetPrice = countGetPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EventCountInfo that = (EventCountInfo) o;
        return countGetByName == that.countGetByName &&
                countBookTickets == that.countBookTickets &&
                countGetPrice == that.countGetPrice &&
                Objects.equal(eventName, that.eventName);
    }


    @Override
    public int hashCode() {
        return Objects.hashCode(eventName, countGetByName, countBookTickets, countGetPrice);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", super.getId())
                .add("eventName", eventName)
                .add("countGetByName", countGetByName)
                .add("countBookTickets", countBookTickets)
                .add("countGetPrice", countGetPrice)
                .toString();
    }
}
