package ua.training.spring.hometask.dao.impl.hibernate;

import com.google.common.collect.Sets;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import ua.training.spring.hometask.dao.EventDao;
import ua.training.spring.hometask.domain.AirDate;
import ua.training.spring.hometask.domain.Event;

import javax.persistence.NoResultException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Repository
@Primary
public class HibernateEventDaoImpl implements EventDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Event getByName(String name) {
        Event event;
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("FROM Event where name=:name");
            query.setParameter("name", name);
            query.setCacheable(true);
            event = (Event) query.getSingleResult();
        } catch (NoResultException e) {
            event = null;
        }

        return event;
    }

    @Override
    public Set<Event> getForDateRange(LocalDateTime from, LocalDateTime to) {
        Collection<Event> events;
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("select ev from Event ev "
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
    public Set<Event> getNextEvents(LocalDateTime to) {
        Collection<Event> events;
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
    public Event save(Event object) {
        object.getAirDates().forEach(ai -> object.getEventAirDates().add(new AirDate(ai)));
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(object);
            session.getTransaction().commit();
        }
        object.getEventAirDates().clear();

        return object;
    }

    @Override
    public void remove(Event object) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(object);
            session.getTransaction().commit();
        }
    }

    @Override
    public Event getById(Long id) {
        Event event;
        try (Session session = sessionFactory.openSession()) {
            event = session.get(Event.class, id);
        }

        return event;
    }

    @Override
    public Collection<Event> getAll() {
        Collection<Event> events;
        try (Session session = sessionFactory.openSession()) {
            events = session.createQuery("from Event", Event.class).setCacheable(true).list();
            events.forEach(event -> event.getEventAirDates().forEach(ai -> event.getAirDates().add(ai.getEventDate())));
        }

        return events;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
