package ua.training.spring.hometask.dao.impl.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.training.spring.hometask.dao.EventCountDao;
import ua.training.spring.hometask.domain.EventCount;

import java.util.Collection;

@Repository
public class HibernateEventCountDaoImpl implements EventCountDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public EventCount getByName(String name) {
        return null;
    }

    @Override
    public boolean update(EventCount eventCount) {
        return false;
    }

    @Override
    public EventCount save(EventCount object) {
        return null;
    }

    @Override
    public void remove(EventCount object) {

    }

    @Override
    public EventCount getById(Long id) {
        return null;
    }

    @Override
    public Collection<EventCount> getAll() {
        return null;
    }
}
