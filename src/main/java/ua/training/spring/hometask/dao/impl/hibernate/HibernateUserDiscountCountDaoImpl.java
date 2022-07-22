package ua.training.spring.hometask.dao.impl.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ua.training.spring.hometask.dao.UserDiscountCountDao;
import ua.training.spring.hometask.domain.UserDiscountCount;

import javax.persistence.NoResultException;
import java.util.Collection;

@Repository
@Profile("HIBERNATE")
@Primary
public class HibernateUserDiscountCountDaoImpl implements UserDiscountCountDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public UserDiscountCount getByName(final String name) {
        UserDiscountCount userDiscountCount;
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("FROM UserDiscountCount where name=:name");
            query.setParameter("name", name);
            query.setCacheable(true);
            userDiscountCount = (UserDiscountCount) query.getSingleResult();
        } catch (NoResultException e) {
            userDiscountCount = null;
        }

        return userDiscountCount;
    }

    @Override
    public boolean update(final UserDiscountCount userDiscountCount) {
        try (Session session = sessionFactory.openSession();) {
            session.beginTransaction();
            session.update(userDiscountCount);
            session.getTransaction().commit();
        }

        return true;
    }

    @Override
    public UserDiscountCount save(final UserDiscountCount userDiscountCount) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(userDiscountCount);
            session.getTransaction().commit();
        }

        return userDiscountCount;
    }

    @Override
    public void remove(final UserDiscountCount userDiscountCount) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(userDiscountCount);
            session.getTransaction().commit();
        }
    }

    @Override
    public UserDiscountCount getById(final Long id) {
        final UserDiscountCount userDiscountCount;
        try (Session session = sessionFactory.openSession()) {
            userDiscountCount = session.get(UserDiscountCount.class, id);
        }

        return userDiscountCount;
    }

    @Override
    public Collection<UserDiscountCount> getAll() {
        final Collection<UserDiscountCount> userDiscountCounts;
        try (Session session = sessionFactory.openSession()) {
            userDiscountCounts = session.createQuery("FROM UserDiscountCount", UserDiscountCount.class).setCacheable(true).list();
        }

        return userDiscountCounts;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
