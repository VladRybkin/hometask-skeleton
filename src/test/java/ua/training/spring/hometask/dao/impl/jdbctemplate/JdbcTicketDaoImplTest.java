package ua.training.spring.hometask.dao.impl.jdbctemplate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.jdbc.JdbcTestUtils;
import ua.training.spring.hometask.config.BeansConfiguration;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.testconfig.TestJdbcTemplateBeans;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BeansConfiguration.class, TestJdbcTemplateBeans.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class JdbcTicketDaoImplTest {

    private static final String TABLE_NAME = "tickets";

    @Autowired
    private JdbcTicketDaoImpl jdbcTicketDao;

    @Autowired
    @Qualifier("testJdbcTemplate")
    private JdbcTemplate testJdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTicketDao.setJdbcTemplate(testJdbcTemplate);
    }

    @Test
    void getPurchasedTicketsForEvent() {
        Ticket ticket=buildTestTicket();
        ticket.setUser(new User());

    }

    @Test
    void shouldGetByIdPersistedTicket() {
        Ticket ticket = buildTestTicket();

        jdbcTicketDao.save(ticket);
        Ticket foundTicket = jdbcTicketDao.getById(1L);

        assertThat(JdbcTestUtils.countRowsInTable(testJdbcTemplate, TABLE_NAME), is(1));
        assertThat(foundTicket, is(ticket));
    }

    @Test
    void remove() {
        Ticket ticket = buildTestTicket();

        jdbcTicketDao.save(ticket);
        jdbcTicketDao.remove(ticket);

        assertThat(JdbcTestUtils.countRowsInTable(testJdbcTemplate, TABLE_NAME), is(0));
    }

    @Test
    void getAll() {
        Ticket user = buildTestTicket();
        jdbcTicketDao.save(user);

        Collection<Ticket>persistedTickets=jdbcTicketDao.getAll();

        assertThat(persistedTickets, hasItems(user));
        assertThat(persistedTickets, hasSize(1));
    }

    private Ticket buildTestTicket() {
        Ticket ticket = new Ticket();
        ticket.setId(1L);
        ticket.setBasePrice(100);
        ticket.setSeat(100);


        return ticket;
    }
}