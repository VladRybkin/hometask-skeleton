package ua.training.spring.hometask.domain;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
public class Ticket extends DomainObject implements Comparable<Ticket> {

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "event_id")
    private Event event;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "seat")
    private long seat;

    @Column(name = "base_price")
    private double basePrice;

    public Ticket() {
    }

    public Ticket(Event event, LocalDateTime dateTime, long seat, double basePrice) {
        this.event = event;
        this.dateTime = dateTime;
        this.seat = seat;
        this.basePrice = basePrice;
    }

    public Ticket(User user, Event event, LocalDateTime dateTime, long seat, double basePrice) {
        this.user = user;
        this.event = event;
        this.dateTime = dateTime;
        this.seat = seat;
        this.basePrice = basePrice;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
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

    public User getUser() {
        return user;
    }

    public Event getEvent() {
        return event;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public long getSeat() {
        return seat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ticket ticket = (Ticket) o;
        return seat == ticket.seat &&
                Double.compare(ticket.basePrice, basePrice) == 0 &&
                Objects.equal(user, ticket.user) &&
                Objects.equal(event, ticket.event) &&
                Objects.equal(dateTime, ticket.dateTime) &&
                Objects.equal(getId(), ticket.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(user, event, dateTime, seat, basePrice, getId());
    }

    @Override
    public int compareTo(Ticket other) {
        if (other == null) {
            return 1;
        }
        return ComparisonChain.start()
                .compare(dateTime, other.getDateTime())
                .compare(event.getName(), other.getEvent().getName())
                .compare(seat, other.getSeat())
                .compare(basePrice, other.getBasePrice())
                .result();
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("user", user)
                .add("event", event)
                .add("dateTime", dateTime)
                .add("seat", seat)
                .add("basePrice", basePrice)
                .toString();
    }
}
