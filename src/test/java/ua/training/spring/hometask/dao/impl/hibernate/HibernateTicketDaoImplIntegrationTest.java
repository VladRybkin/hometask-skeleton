package ua.training.spring.hometask.dao.impl.hibernate;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ua.training.spring.hometask.config.BeansConfiguration;
import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.testconfig.TestsSessionFactoryBeans;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static ua.training.spring.hometask.utills.BuildTestEntityUtill.buildTestTicketWithPersistedUserAndEvent;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BeansConfiguration.class, TestsSessionFactoryBeans.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("HIBERNATE")
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
        Ticket ticket = createTestTicket();

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
        Ticket ticket = createTestTicket();

        hibernateTicketDao.save(ticket);
        Ticket foundTicket = hibernateTicketDao.getById(1L);

        assertThat(foundTicket, is(ticket));
    }

    @Test
    void remove() {
        Ticket ticket = createTestTicket();

        hibernateTicketDao.save(ticket);
        assertThat(hibernateTicketDao.getAll(), hasSize(1));

        hibernateTicketDao.remove(ticket);
        assertThat(hibernateTicketDao.getAll(), is(empty()));
    }

    @Test
    void getAll() {
        Ticket user = createTestTicket();
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

    @Test
    void shouldCashingGetById() {
        Ticket ticket = createTestTicket();
        hibernateTicketDao.save(ticket);

        hibernateTicketDao.getById(1L);
        assertThat(sessionFactory.getStatistics().getSecondLevelCachePutCount(), is(3L));
        assertThat(sessionFactory.getStatistics().getSecondLevelCacheHitCount(), is(0L));

        hibernateTicketDao.getById(1L);
        assertThat(sessionFactory.getStatistics().getSecondLevelCachePutCount(), is(3L));
        assertThat(sessionFactory.getStatistics().getSecondLevelCacheHitCount(), is(3L));
    }

    @Test
    void shouldCashingGetAll() {
        Ticket ticketWithEventAndUser = createTestTicket();
        Ticket ticketWithoutEventAndUser = new Ticket();

        hibernateTicketDao.save(ticketWithEventAndUser);
        hibernateTicketDao.save(ticketWithoutEventAndUser);

        hibernateTicketDao.getAll();
        assertThat(sessionFactory.getStatistics().getSecondLevelCachePutCount(), is(4L));
        assertThat(sessionFactory.getStatistics().getSecondLevelCacheHitCount(), is(0L));

        hibernateTicketDao.getAll();
        assertThat(sessionFactory.getStatistics().getSecondLevelCachePutCount(), is(4L));
        assertThat(sessionFactory.getStatistics().getSecondLevelCacheHitCount(), is(4L));
    }

    @Test
    void shouldRemoveFromCache() {
        Ticket ticket = createTestTicket();
        hibernateTicketDao.save(ticket);
        hibernateTicketDao.getById(ticket.getId());

        assertThat(sessionFactory.getStatistics().getSecondLevelCachePutCount(), is(3L));
        hibernateTicketDao.remove(ticket);
        assertThat(hibernateTicketDao.getById(ticket.getId()), is(nullValue()));
    }

    private Ticket createTestTicket() {
        return buildTestTicketWithPersistedUserAndEvent(hibernateUserDao, hibernateEventDao);
    }
}