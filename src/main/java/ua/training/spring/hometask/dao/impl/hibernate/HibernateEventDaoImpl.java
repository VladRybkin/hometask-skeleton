package ua.training.spring.hometask.dao.impl.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.training.spring.hometask.dao.EventDao;
import ua.training.spring.hometask.domain.Event;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Repository
public class HibernateEventDaoImpl implements EventDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Event getByName(String name) {
        Event event;
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("FROM Event where name=:name");
            query.setParameter("name", name);
            event = (Event) query.getSingleResult();
        }

        return event;
    }

    /**
     * @todo
     **/
    @Override
    public Set<Event> getForDateRange(LocalDateTime from, LocalDateTime to) {
        return null;
    }

    /**
     * @todo
     **/
    @Override
    public Set<Event> getNextEvents(LocalDateTime to) {
        return null;
    }

    @Override
    public Event save(Event object) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(object);
            session.getTransaction().commit();
        }

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
            events = session.createQuery("FROM Event", Event.class).list();
        }

        return events;
    }
}
