package ua.training.spring.hometask.dao.impl.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.training.spring.hometask.dao.UserDao;
import ua.training.spring.hometask.domain.User;

import java.util.Collection;

@Repository
public class HibernateUserDaoImpl implements UserDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public User save(User object) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(object);
            session.getTransaction().commit();
        }

        return object;
    }

    @Override
    public void remove(User object) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(object);
            session.getTransaction().commit();
        }
    }

    @Override
    public User getById(Long id) {
        User user;
        try (Session session = sessionFactory.openSession()) {
            user = session.get(User.class, id);
        }

        return user;
    }

    @Override
    public Collection<User> getAll() {
        Collection<User> users;
        try (Session session = sessionFactory.openSession()) {
            users = session.createQuery("FROM User", User.class).list();
        }

        return users;
    }
}
