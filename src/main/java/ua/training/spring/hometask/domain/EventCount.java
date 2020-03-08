package ua.training.spring.hometask.domain;


import com.google.common.base.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "event_counts")
public class EventCount extends DomainObject {

    @Column(name = "name")
    private String eventName;

    @Column(name = "count_get_by_name")
    private long countGetByName;

    @Column(name = "count_book_tickets")
    private long countBookTickets;

    @Column(name = "count_get_price")
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
        EventCount that = (EventCount) o;
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

    public static class Builder {

        private EventCount newEventCount;

        public Builder() {
            newEventCount = new EventCount();
        }

        public Builder withEventName(String eventName) {
            newEventCount.eventName = eventName;
            return this;
        }

        public Builder withCountBookTicket(long countBookTickets) {
            newEventCount.countBookTickets = countBookTickets;
            return this;
        }

        public Builder withCountGetByName(long countGetByName) {
            newEventCount.countGetByName = countGetByName;
            return this;
        }

        public Builder withCountGetPrice(long countGetPrice) {
            newEventCount.countGetPrice = countGetPrice;
            return this;
        }

        public EventCount build() {
            return newEventCount;
        }

    }
}
