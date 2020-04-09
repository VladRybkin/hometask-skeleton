package ua.training.spring.hometask.domain;

import com.google.common.base.Objects;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "air_dates")
public class AirDate extends DomainObject {

    @NotNull
    @Column(name = "event_date", unique = true)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AirDate airDate = (AirDate) o;
        return Objects.equal(eventDate, airDate.eventDate) &&
                Objects.equal(events, airDate.events) &&
                Objects.equal(getId(), airDate.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(eventDate, events, getId());
    }
}
