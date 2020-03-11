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
    private LocalDateTime localDateTime;

    @ManyToMany(targetEntity = Event.class, mappedBy = "eventAirDates")
    private Set<Event> events = new HashSet<>();


    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }
}
