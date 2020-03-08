package ua.training.spring.hometask.dao.impl.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.training.spring.hometask.dao.UserDiscountCountDao;
import ua.training.spring.hometask.domain.UserDiscountCount;

import java.util.Collection;

@Repository
public class HibernateUserDiscountCountDaoImpl implements UserDiscountCountDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public UserDiscountCount getByName(String name) {
        UserDiscountCount userDiscountCount;
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("FROM UserDiscountCount where name=:name");
            query.setParameter("name", name);
            userDiscountCount = (UserDiscountCount) query.getSingleResult();
        }

        return userDiscountCount;
    }

    @Override
    public boolean update(UserDiscountCount userDiscountCount) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(userDiscountCount);
            session.getTransaction().commit();
        }

        return true;
    }

    @Override
    public UserDiscountCount save(UserDiscountCount object) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(object);
            session.getTransaction().commit();
        }

        return object;
    }

    @Override
    public void remove(UserDiscountCount object) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(object);
            session.getTransaction().commit();
        }
    }

    @Override
    public UserDiscountCount getById(Long id) {
        UserDiscountCount userDiscountCount;
        try (Session session = sessionFactory.openSession()) {
            userDiscountCount = session.get(UserDiscountCount.class, id);
        }

        return userDiscountCount;
    }

    @Override
    public Collection<UserDiscountCount> getAll() {
        Collection<UserDiscountCount> userDiscountCounts;
        try (Session session = sessionFactory.openSession()) {
            userDiscountCounts = session.createQuery("FROM UserDiscountCount", UserDiscountCount.class).list();
        }

        return userDiscountCounts;
    }
}
