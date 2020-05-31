package ua.training.spring.hometask.dao.impl.mybatis;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ua.training.spring.hometask.config.BeansConfiguration;
import ua.training.spring.hometask.domain.Ticket;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static ua.training.spring.hometask.utills.BuildTestEntityUtill.buildTestTicketWithPersistedUserAndEvent;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BeansConfiguration.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles({"MYBATIS", "TEST"})
class MyBatisTicketDaoImplIntegrationTest {

    @Autowired
    private MyBatisTicketDaoImpl myBatisTicketDao;

    @Autowired
    private MyBatisUserDaoImpl myBatisUserDao;

    @Autowired
    private MyBatisEventDaoImpl myBatisEventDao;

    @Test
    void getPurchasedTicketsForEvent() {
        Ticket ticket = createTestTicket();

        myBatisTicketDao.save(ticket);
        System.out.println(ticket.getDateTime());

        Collection<Ticket> persistedPurchasedTickets = myBatisTicketDao.
                getPurchasedTicketsForEvent(ticket.getEvent(), ticket.getDateTime().minusDays(1));

        assertThat(persistedPurchasedTickets, hasItems(ticket));
        assertThat(persistedPurchasedTickets, hasSize(1));
    }

    @Test
    void shouldReturnEmptyPurchasedTicketsList() {
        Ticket ticket = new Ticket();

        myBatisTicketDao.save(ticket);

        Collection<Ticket> persistedPurchasedTickets = myBatisTicketDao.getPurchasedTicketsForEvent(ticket.getEvent(), ticket.getDateTime());

        assertThat(persistedPurchasedTickets, empty());
    }

    @Test
    void shouldGetByIdPersistedTicket() {
        Ticket ticket = createTestTicket();

        myBatisTicketDao.save(ticket);
        Ticket foundTicket = myBatisTicketDao.getById(1L);

        assertThat(foundTicket, is(ticket));
    }

    @Test
    void remove() {
        Ticket ticket = createTestTicket();

        myBatisTicketDao.save(ticket);
        assertThat(myBatisTicketDao.getAll(), hasSize(1));

        myBatisTicketDao.remove(ticket);
        assertThat(myBatisTicketDao.getAll(), is(empty()));
    }

    @Test
    void getAll() {
        Ticket ticket = createTestTicket();
        myBatisTicketDao.save(ticket);

        Collection<Ticket> persistedTickets = myBatisTicketDao.getAll();

        assertThat(persistedTickets, hasItems(ticket));
        assertThat(persistedTickets, hasSize(1));
    }

    @Test
    void shouldReturnNullWhenGetById() {
        Ticket foundById = myBatisTicketDao.getById(1L);

        assertThat(foundById, is(nullValue()));
    }

    private Ticket createTestTicket() {
        return buildTestTicketWithPersistedUserAndEvent(myBatisUserDao, myBatisEventDao);
    }
}