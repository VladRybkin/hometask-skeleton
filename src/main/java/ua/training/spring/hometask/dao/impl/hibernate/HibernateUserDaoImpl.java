package ua.training.spring.hometask.dao.impl.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ua.training.spring.hometask.dao.UserDao;
import ua.training.spring.hometask.domain.User;

import java.util.Collection;

@Repository
@Profile("HIBERNATE")
@Primary
public class HibernateUserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User getUserByEmail(final String email) {
        final User user;
        try (Session session = sessionFactory.openSession()) {
            final Query query = session.createQuery("FROM User where email=:email");
            query.setParameter("email", email);
            query.setCacheable(true);
            user = (User) query.getSingleResult();
        }

        return user;
    }

    @Override
    public User save(final User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
        }

        return user;
    }

    @Override
    public void remove(final User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public User getById(final Long id) {
        final User user;
        try (Session session = sessionFactory.openSession()) {
            user = session.get(User.class, id);
        }

        return user;
    }

    @Override
    public Collection<User> getAll() {
        final Collection<User> users;
        try (Session session = sessionFactory.openSession()) {
            users = session.createQuery("FROM User", User.class).setCacheable(true).list();
        }

        return users;
    }

    @Override
    public boolean update(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        }

        return true;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
