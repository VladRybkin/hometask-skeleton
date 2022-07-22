package ua.training.spring.hometask.dao.impl.inmemory;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ua.training.spring.hometask.dao.TicketDao;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.Ticket;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository("ticketDaoImpl")
@Profile("IN_MEMORY")
public class TicketDaoImpl implements TicketDao {

    private static final Map<Long, Ticket> tickets = new HashMap<>();

    @Override
    public Ticket save(final Ticket ticket) {
        final  Long ticketId = ticket.getId();
        if (Objects.isNull(ticketId)) {
            ticket.setId((long) (tickets.size() + 1));
            tickets.put(ticket.getId(), ticket);
        } else {
            tickets.put(ticketId, ticket);
        }

        return ticket;
    }

    @Override
    public void remove(final Ticket ticket) {
        tickets.remove(ticket.getId());
    }

    @Override
    public Ticket getById(final Long id) {
        return tickets.get(id);
    }

    @Override
    public Collection<Ticket> getAll() {
        return tickets.values();
    }

    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(Event event, LocalDateTime dateTime) {
        return tickets.values().stream()
                .filter(purchasedFilter()
                        .and(eventFilter(event)
                                .and(filterDateTime(dateTime))))
                .collect(Collectors.toSet());
    }

    @Override
    public boolean update(final Ticket ticket) {
        boolean update = false;
        if (tickets.containsKey(ticket.getId())) {
            tickets.put(ticket.getId(), ticket);
            update = true;
        }

        return update;
    }

    private Predicate<Ticket> purchasedFilter() {
        return t -> !Objects.isNull(t.getUser());
    }

    private Predicate<Ticket> filterDateTime(final LocalDateTime dateTime) {
        return t -> t.getDateTime().isEqual(dateTime);
    }

    private Predicate<Ticket> eventFilter(final Event event) {
        return t -> Objects.equals(t.getEvent(), event);
    }
}
