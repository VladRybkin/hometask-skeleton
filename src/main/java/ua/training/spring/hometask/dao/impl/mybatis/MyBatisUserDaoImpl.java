package ua.training.spring.hometask.dao.impl.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ua.training.spring.hometask.dao.UserDao;
import ua.training.spring.hometask.domain.User;

import java.util.Collection;

@Repository
@Profile("MYBATIS")
public class MyBatisUserDaoImpl implements UserDao {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public User getUserByEmail(String email) {
        User user;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            user = session.selectOne("ua.training.spring.hometask.dao.impl.mybatis.mybatisrepos.MBUserMapper.getByName", email);
        }

        return user;
    }

    @Override
    public User save(User object) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.insert("ua.training.spring.hometask.dao.impl.mybatis.mybatisrepos.MBUserMapper.save", object);
        }

        return object;
    }

    @Override
    public void remove(User object) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.delete("ua.training.spring.hometask.dao.impl.mybatis.mybatisrepos.MBUserMapper.remove", object);
        }
    }

    @Override
    public User getById(Long id) {
        User user;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            user = session.selectOne("ua.training.spring.hometask.dao.impl.mybatis.mybatisrepos.MBUserMapper.getById", id);
        }

        return user;
    }

    @Override
    public Collection<User> getAll() {
        Collection<User> users;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            users = session.selectList("ua.training.spring.hometask.dao.impl.mybatis.mybatisrepos.MBUserMapper.getAll");
        }

        return users;
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }
}
