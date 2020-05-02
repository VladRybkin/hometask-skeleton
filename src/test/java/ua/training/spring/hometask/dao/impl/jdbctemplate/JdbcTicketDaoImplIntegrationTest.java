package ua.training.spring.hometask.dao.impl.jdbctemplate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.jdbc.JdbcTestUtils;
import ua.training.spring.hometask.config.BeansConfiguration;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.testconfig.TestJdbcTemplateBeans;

import java.time.LocalDateTime;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BeansConfiguration.class, TestJdbcTemplateBeans.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("JDBC_TEMPLATE")
class JdbcTicketDaoImplIntegrationTest {

    private static final String TABLE_NAME = "tickets";

    @Autowired
    private JdbcTicketDaoImpl jdbcTicketDao;

    @Autowired
    private JdbcUserDaoImpl jdbcUserDao;

    @Autowired
    private JdbcEventDaoImpl jdbcEventDao;

    @Autowired
    @Qualifier("testJdbcTemplate")
    private JdbcTemplate testJdbcTemplate;


    @BeforeEach
    void setUp() {
        jdbcTicketDao.setJdbcTemplate(testJdbcTemplate);
        jdbcUserDao.setJdbcTemplate(testJdbcTemplate);
        jdbcEventDao.setJdbcTemplate(testJdbcTemplate);
    }

    @Test
    void getPurchasedTicketsForEvent() {
        Ticket ticket = createTestTicket();

        jdbcTicketDao.save(ticket);
        ticket.setId(1L);

        Collection<Ticket> persistedPurchasedTickets = jdbcTicketDao.getPurchasedTicketsForEvent(ticket.getEvent(), ticket.getDateTime());

        assertThat(persistedPurchasedTickets, hasItems(ticket));
        assertThat(persistedPurchasedTickets, hasSize(1));
    }

    @Test
    void shouldReturnEmptyPurchasedTicketsList() {
        Ticket ticket = createTestTicket();
        ticket.setUser(null);

        jdbcTicketDao.save(ticket);
        ticket.setId(1L);

        Collection<Ticket> persistedPurchasedTickets = jdbcTicketDao.getPurchasedTicketsForEvent(ticket.getEvent(), ticket.getDateTime());

        assertThat(persistedPurchasedTickets, empty());
    }

    @Test
    void shouldGetByIdPersistedTicket() {
        Ticket ticket = createTestTicket();

        jdbcTicketDao.save(ticket);
        ticket.setId(1L);

        Ticket foundTicket = jdbcTicketDao.getById(1L);

        assertThat(foundTicket, is(ticket));
    }

    @Test
    void remove() {
        Ticket ticket = createTestTicket();

        jdbcTicketDao.save(ticket);
        ticket.setId(1L);

        assertThat(JdbcTestUtils.countRowsInTable(testJdbcTemplate, TABLE_NAME), is(1));

        jdbcTicketDao.remove(ticket);
        assertThat(JdbcTestUtils.countRowsInTable(testJdbcTemplate, TABLE_NAME), is(0));
    }

    @Test
    void getAll() {
        Ticket ticket = createTestTicket();

        jdbcTicketDao.save(ticket);
        ticket.setId(1L);

        Collection<Ticket> persistedTickets = jdbcTicketDao.getAll();

        assertThat(JdbcTestUtils.countRowsInTable(testJdbcTemplate, TABLE_NAME), is(1));
        assertThat(persistedTickets, hasItems(ticket));
        assertThat(persistedTickets, hasSize(1));
    }

    @Test
    void shouldReturnNullWhenGetById() {
        assertThat(JdbcTestUtils.countRowsInTable(testJdbcTemplate, TABLE_NAME), is(0));

        Ticket foundById = jdbcTicketDao.getById(1L);

        assertThat(foundById, is(nullValue()));
    }

    private Ticket createTestTicket() {
        Ticket ticket = new Ticket();
        ticket.setBasePrice(100);
        ticket.setSeat(100);

        User user = new User();
        user.setEmail("usermail1");
        user.setId(1L);

        Event event = new Event();
        event.setBasePrice(100);
        event.setName("testEvent");
        event.setId(1L);

        LocalDateTime ticketDate = LocalDateTime.now();
        ticket.setDateTime(ticketDate);
        ticket.setUser(user);
        ticket.setEvent(event);

        jdbcUserDao.save(user);
        jdbcEventDao.save(event);

        return ticket;
    }
}