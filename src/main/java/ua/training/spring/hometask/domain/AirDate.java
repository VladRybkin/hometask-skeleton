package ua.training.spring.hometask.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "air_dates")
public class AirDate extends DomainObject {

    @Column(name = "event_date")
    private LocalDateTime eventDate;

    @ManyToMany(targetEntity = Event.class, mappedBy = "eventAirDates")
    private Set<Event> events = new HashSet<>();


    public AirDate() {
    }

    public AirDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }
}
