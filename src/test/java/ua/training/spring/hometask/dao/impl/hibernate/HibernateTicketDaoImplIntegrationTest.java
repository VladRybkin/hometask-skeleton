package ua.training.spring.hometask.dao.impl.hibernate;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ua.training.spring.hometask.config.BeansConfiguration;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.EventRating;
import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.testconfig.TestsSessionFactoryBeans;

import java.time.LocalDateTime;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BeansConfiguration.class, TestsSessionFactoryBeans.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class HibernateTicketDaoImplIntegrationTest {

    @Autowired
    private HibernateTicketDaoImpl hibernateTicketDao;

    @Autowired
    private HibernateUserDaoImpl hibernateUserDao;

    @Autowired
    private HibernateEventDaoImpl hibernateEventDao;

    @Autowired
    @Qualifier("testSessionFactory")
    private SessionFactory sessionFactory;

    @BeforeEach
    void setUp() {
        hibernateTicketDao.setSessionFactory(sessionFactory);
        hibernateUserDao.setSessionFactory(sessionFactory);
        hibernateEventDao.setSessionFactory(sessionFactory);
    }

    @Test
    void getPurchasedTicketsForEvent() {
        Ticket ticket = buildTestTicket();

        hibernateTicketDao.save(ticket);
        System.out.println(ticket.getDateTime());

        Collection<Ticket> persistedPurchasedTickets = hibernateTicketDao.
                getPurchasedTicketsForEvent(ticket.getEvent(), ticket.getDateTime().minusDays(1));

        assertThat(persistedPurchasedTickets, hasItems(ticket));
        assertThat(persistedPurchasedTickets, hasSize(1));
    }

    @Test
    void shouldReturnEmptyPurchasedTicketsList() {
        Ticket ticket = new Ticket();

        hibernateTicketDao.save(ticket);

        Collection<Ticket> persistedPurchasedTickets = hibernateTicketDao.getPurchasedTicketsForEvent(ticket.getEvent(), ticket.getDateTime());

        assertThat(persistedPurchasedTickets, empty());
    }

    @Test
    void shouldGetByIdPersistedTicket() {
        Ticket ticket = buildTestTicket();

        hibernateTicketDao.save(ticket);
        Ticket foundTicket = hibernateTicketDao.getById(1L);

        assertThat(foundTicket, is(ticket));
    }

    @Test
    void remove() {
        Ticket ticket = buildTestTicket();

        hibernateTicketDao.save(ticket);

        hibernateTicketDao.remove(ticket);

    }

    @Test
    void getAll() {
        Ticket user = buildTestTicket();
        hibernateTicketDao.save(user);

        Collection<Ticket> persistedTickets = hibernateTicketDao.getAll();

        assertThat(persistedTickets, hasItems(user));
        assertThat(persistedTickets, hasSize(1));
    }

    @Test
    void shouldReturnNullWhenGetById() {
        Ticket foundById = hibernateTicketDao.getById(1L);

        assertThat(foundById, is(nullValue()));
    }

    private Ticket buildTestTicket() {
        Ticket ticket = new Ticket();

        ticket.setBasePrice(100);
        ticket.setSeat(100);

        User user = new User();

        user.setEmail("usermail1");

        Event event = new Event();

        event.setBasePrice(100);
        event.setName("testEvent");
        event.setRating(EventRating.LOW);

        LocalDateTime ticketDate = LocalDateTime.now();
        ticket.setDateTime(ticketDate);
        ticket.setUser(user);
        ticket.setEvent(event);

        hibernateUserDao.save(user);
        hibernateEventDao.save(event);

        return ticket;
    }
}