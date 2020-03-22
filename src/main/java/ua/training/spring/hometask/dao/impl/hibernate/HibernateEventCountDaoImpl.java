package ua.training.spring.hometask.dao.impl.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.training.spring.hometask.dao.EventCountDao;
import ua.training.spring.hometask.domain.EventCount;

import javax.persistence.NoResultException;
import java.util.Collection;

@Repository
public class HibernateEventCountDaoImpl implements EventCountDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public EventCount getByName(String name) {
        EventCount eventCount;
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("FROM EventCount where name=:name");
            query.setParameter("name", name);
            eventCount = (EventCount) query.getSingleResult();
        } catch (NoResultException e) {
            eventCount = null;
        }

        return eventCount;
    }

    @Override
    public boolean update(EventCount eventCount) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(eventCount);
            session.getTransaction().commit();
        }

        return true;
    }

    @Override
    public EventCount save(EventCount object) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(object);
            session.getTransaction().commit();
        }

        return object;
    }

    @Override
    public void remove(EventCount object) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(object);
            session.getTransaction().commit();
        }
    }

    @Override
    public EventCount getById(Long id) {
        EventCount eventCount;
        try (Session session = sessionFactory.openSession()) {
            eventCount = session.get(EventCount.class, id);
        }

        return eventCount;
    }

    @Override
    public Collection<EventCount> getAll() {
        Collection<EventCount> eventCounts;
        try (Session session = sessionFactory.openSession()) {
            eventCounts = session.createQuery("FROM EventCount", EventCount.class).list();
        }

        return eventCounts;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
