package ua.training.spring.hometask.dao.impl.mybatis;

import com.google.common.collect.Sets;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ua.training.spring.hometask.dao.EventDao;
import ua.training.spring.hometask.domain.Event;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Repository
@Profile("MYBATIS")
public class MyBatisEventDaoImpl implements EventDao {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public Event getByName(String name) {
        Event event;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            event = session.selectOne("ua.training.spring.hometask.dao.impl.mybatis.mybatisrepos.MBEventMapper.getByName", name);
        }

        return event;
    }

    @Override
    public Set<Event> getForDateRange(LocalDateTime from, LocalDateTime to) {
        Collection<Event> events;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            events = session.selectList("ua.training.spring.hometask.dao.impl.mybatis.mybatisrepos.MBEventMapper.getForDateRange");
        }

        return Sets.newHashSet(events);
    }

    @Override
    public Set<Event> getNextEvents(LocalDateTime to) {
        Collection<Event> events;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            events = session.selectList("ua.training.spring.hometask.dao.impl.mybatis.mybatisrepos.MBEventMapper.getNextEvents");
        }
        return Sets.newHashSet();
    }

    @Override
    public Event save(Event object) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.insert("ua.training.spring.hometask.dao.impl.mybatis.mybatisrepos.MBEventMapper.save", object);
        }

        return object;
    }

    @Override
    public void remove(Event object) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.delete("ua.training.spring.hometask.dao.impl.mybatis.mybatisrepos.MBEventMapper.remove", object);
        }
    }

    @Override
    public Event getById(Long id) {
        Event event;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            event = session.selectOne("ua.training.spring.hometask.dao.impl.mybatis.mybatisrepos.MBEventMapper.getById", id);
        }

        return event;
    }

    @Override
    public Collection<Event> getAll() {
        Collection<Event> events;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            events = session.selectList("ua.training.spring.hometask.dao.impl.mybatis.mybatisrepos.MBEventMapper.getAll");
        }

        return events;
    }

}
