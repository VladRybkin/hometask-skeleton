package ua.training.spring.hometask.dao.impl.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ua.training.spring.hometask.dao.TicketDao;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.Ticket;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Repository
@Profile("MYBATIS")
public class MyBatisTicketDaoImpl implements TicketDao {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(Event event, LocalDateTime dateTime) {
        return null;
    }

    @Override
    public Ticket save(Ticket object) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.insert("ua.training.spring.hometask.dao.impl.mybatis.mybatisrepos.MBTicketMapper.save", object);
        }

        return object;
    }

    @Override
    public void remove(Ticket object) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.delete("ua.training.spring.hometask.dao.impl.mybatis.mybatisrepos.MBTicketMapper.remove", object);
        }
    }

    @Override
    public Ticket getById(Long id) {
        Ticket ticket;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ticket = session.selectOne("ua.training.spring.hometask.dao.impl.mybatis.mybatisrepos.MBTicketMapper.getById", id);
        }

        return ticket;
    }

    @Override
    public Collection<Ticket> getAll() {
        Collection<Ticket> tickets;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            tickets = session.selectList("ua.training.spring.hometask.dao.impl.mybatis.mybatisrepos.MBTicketMapper.getAll");
        }

        return tickets;
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }
}
