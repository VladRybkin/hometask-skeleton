package ua.training.spring.hometask.dao.impl.hibernate;

import com.google.common.collect.Sets;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ua.training.spring.hometask.dao.EventDao;
import ua.training.spring.hometask.domain.AirDate;
import ua.training.spring.hometask.domain.Event;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Repository
@Profile("HIBERNATE")
@Primary
public class HibernateEventDaoImpl implements EventDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Event getByName(final String name) {
        final Event event;
        try (Session session = sessionFactory.openSession()) {
            final Query query = session.createQuery("FROM Event where name=:name");
            query.setParameter("name", name);
            query.setCacheable(true);
            event = (Event) query.getSingleResult();
        }

        return event;
    }

    @Override
    public Set<Event> getForDateRange(final LocalDateTime from, final LocalDateTime to) {
        final Collection<Event> events;
        try (Session session = sessionFactory.openSession()) {
            final Query query = session.createQuery("select ev from Event ev "
                    + "left join  ev.eventAirDates ed "
                    + "where ed.eventDate "
                    + "BETWEEN :from AND :to"
            );
            query.setParameter("from", from);
            query.setParameter("to", to);
            query.setCacheable(true);

            events = query.list();
            events.forEach(event -> event.getEventAirDates().forEach(ai -> event.getAirDates().add(ai.getEventDate())));
        }

        return Sets.newHashSet(events);
    }

    @Override
    public Set<Event> getNextEvents(final LocalDateTime to) {
        final Collection<Event> events;
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("select ev from Event ev "
                    + "left join ev.eventAirDates ed "
                    + "where ed.eventDate < :to", Event.class);
            query.setParameter("to", to);
            query.setCacheable(true);
            events = query.list();
            events.forEach(event -> event.getEventAirDates().forEach(ai -> event.getAirDates().add(ai.getEventDate())));
        }

        return Sets.newHashSet(events);
    }

    @Override
    public Event save(final Event event) {
        event.getAirDates().forEach(ai -> event.getEventAirDates().add(new AirDate(ai)));
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(event);
            session.getTransaction().commit();
        }
        event.getEventAirDates().clear();

        return event;
    }

    @Override
    public void remove(final Event event) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(event);
            session.getTransaction().commit();
        }
    }

    @Override
    public Event getById(final Long id) {
        final Event event;
        try (Session session = sessionFactory.openSession()) {
            event = session.get(Event.class, id);
        }

        return event;
    }

    @Override
    public Collection<Event> getAll() {
        final Collection<Event> events;
        try (Session session = sessionFactory.openSession()) {
            events = session.createQuery("from Event", Event.class).setCacheable(true).list();
            events.forEach(event -> event.getEventAirDates().forEach(ai -> event.getAirDates().add(ai.getEventDate())));
        }

        return events;
    }

    @Override
    public boolean update(Event event) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(event);
            session.getTransaction().commit();
        }

        return true;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
