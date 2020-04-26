package ua.training.spring.hometask.dao.impl.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.training.spring.hometask.dao.UserDiscountCountDao;
import ua.training.spring.hometask.domain.UserDiscountCount;

import java.util.Collection;

@Repository
public class MyBatisUserDiscountCountDaoImpl implements UserDiscountCountDao {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public UserDiscountCount getByName(String name) {
        UserDiscountCount userDiscountCount;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            userDiscountCount = session.selectOne("ua.training.spring.hometask.dao.impl.mybatis.mybatisrepos.MBUserDiscountCountMapper.getByName", name);
        }

        return userDiscountCount;
    }

    @Override
    public boolean update(UserDiscountCount userDiscountCount) {
        return false;
    }

    @Override
    public UserDiscountCount save(UserDiscountCount object) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.insert("ua.training.spring.hometask.dao.impl.mybatis.mybatisrepos.MBUserDiscountCountMapper.save", object);
        }

        return object;
    }

    @Override
    public void remove(UserDiscountCount object) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.delete("ua.training.spring.hometask.dao.impl.mybatis.mybatisrepos.MBUserDiscountCountMapper.remove", object);
        }
    }

    @Override
    public UserDiscountCount getById(Long id) {
        UserDiscountCount userDiscountCount;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            userDiscountCount = session.selectOne("ua.training.spring.hometask.dao.impl.mybatis.mybatisrepos.MBUserDiscountCountMapper.getById", id);
        }

        return userDiscountCount;
    }

    @Override
    public Collection<UserDiscountCount> getAll() {
        Collection<UserDiscountCount> userDiscountCounts;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            userDiscountCounts = session.selectList("ua.training.spring.hometask.dao.impl.mybatis.mybatisrepos.MBUserDiscountCountMapper.getAll");
        }

        return userDiscountCounts;
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }
}
