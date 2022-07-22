package ua.training.spring.hometask.dao.impl.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ua.training.spring.hometask.dao.EventCountDao;
import ua.training.spring.hometask.domain.EventCount;

import javax.persistence.NoResultException;
import java.util.Collection;

@Repository
@Profile("HIBERNATE")
@Primary
public class HibernateEventCountDaoImpl implements EventCountDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public EventCount getByName(final String name) {
        final EventCount eventCount;
        try (Session session = sessionFactory.openSession()) {
            final Query query = session.createQuery("FROM EventCount where name=:name");
            query.setParameter("name", name);
            query.setCacheable(true);
            eventCount = (EventCount) query.getSingleResult();
        }

        return eventCount;
    }

    @Override
    public boolean update(final EventCount eventCount) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(eventCount);
            session.getTransaction().commit();
        }

        return true;
    }

    @Override
    public EventCount save(final EventCount object) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(object);
            session.getTransaction().commit();
        }

        return object;
    }

    @Override
    public void remove(final EventCount object) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(object);
            session.getTransaction().commit();
        }
    }

    @Override
    public EventCount getById(final Long id) {
        final EventCount eventCount;
        try (Session session = sessionFactory.openSession()) {
            eventCount = session.get(EventCount.class, id);
        }

        return eventCount;
    }

    @Override
    public Collection<EventCount> getAll() {
        final Collection<EventCount> eventCounts;
        try (Session session = sessionFactory.openSession()) {
            eventCounts = session.createQuery("FROM EventCount", EventCount.class).setCacheable(true).list();
        }

        return eventCounts;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
