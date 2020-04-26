package ua.training.spring.hometask.dao.impl.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.training.spring.hometask.dao.EventCountDao;
import ua.training.spring.hometask.domain.EventCount;

import java.util.Collection;

@Repository
public class MyBatisEventCountDaoImpl implements EventCountDao {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

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
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.insert("ua.training.spring.hometask.dao.impl.mybatis.mybatisrepos.MBEventCountMapper.save", object);
        }

        return object;
    }

    @Override
    public void remove(EventCount object) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.delete("ua.training.spring.hometask.dao.impl.mybatis.mybatisrepos.MBEventCountMapper.remove", object);
        }
    }

    @Override
    public EventCount getById(Long id) {
        EventCount eventCount;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            eventCount = session.selectOne("ua.training.spring.hometask.dao.impl.mybatis.mybatisrepos.MBEventCountMapper.getById", id);
        }

        return eventCount;
    }

    @Override
    public Collection<EventCount> getAll() {
        Collection<EventCount> eventCounts;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            eventCounts = session.selectList("ua.training.spring.hometask.dao.impl.mybatis.mybatisrepos.MBEventCountMapper.getAll");
        }

        return eventCounts;
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }
}
