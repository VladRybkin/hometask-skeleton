package ua.training.spring.hometask.dao.impl.immemory;

import org.springframework.stereotype.Repository;
import ua.training.spring.hometask.dao.TicketDao;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.Ticket;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.Objects;


@Repository
public class TicketDaoImpl implements TicketDao {

    private static final Map<Long, Ticket> tickets = new HashMap<>();

    @Override
    public Ticket save(Ticket object) {
        object.setId((long) (tickets.size() + 1));
        tickets.put(object.getId(), object);
        return object;
    }

    @Override
    public void remove(Ticket object) {
        tickets.remove(object.getId());
    }

    @Override
    public Ticket getById(Long id) {
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

    private Predicate<Ticket> purchasedFilter() {
        return t -> !Objects.isNull(t.getUser());
    }

    private Predicate<Ticket> filterDateTime(LocalDateTime dateTime) {
        return t -> t.getDateTime().isEqual(dateTime);
    }

    private Predicate<Ticket> eventFilter(Event event) {
        return t -> Objects.equals(t.getEvent(), event);
    }

}
