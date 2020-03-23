package ua.training.spring.hometask.dao.impl.hibernate;

import com.google.common.collect.Sets;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.training.spring.hometask.dao.TicketDao;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.Ticket;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Repository
public class HibernateTicketDaoImpl implements TicketDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(Event event, LocalDateTime dateTime) {
        Collection<Ticket> tickets;
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("FROM Ticket t where t.user is not null and t.event=:event and t.dateTime > :dateTime", Ticket.class);

            query.setParameter("event", event);
            query.setParameter("dateTime", dateTime);
            tickets = query.getResultList();
        }

        return Sets.newHashSet(tickets);
    }

    @Override
    public Ticket save(Ticket object) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(object);
            session.getTransaction().commit();
        }

        return object;
    }

    @Override
    public void remove(Ticket object) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(object);
            session.getTransaction().commit();
        }
    }

    @Override
    public Ticket getById(Long id) {
        Ticket ticket;
        try (Session session = sessionFactory.openSession()) {
            ticket = session.get(Ticket.class, id);
        }

        return ticket;
    }

    @Override
    public Collection<Ticket> getAll() {
        Collection<Ticket> tickets;
        try (Session session = sessionFactory.openSession()) {
            tickets = session.createQuery("FROM Ticket", Ticket.class).list();
        }

        return tickets;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}