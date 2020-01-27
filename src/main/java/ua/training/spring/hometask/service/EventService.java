package ua.training.spring.hometask.service;

import ua.training.spring.hometask.domain.Event;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author Yuriy_Tkach
 */
public interface EventService extends AbstractDomainObjectService<Event> {

    /**
     * Finding event by name
     *
     * @param name Name of the event
     * @return found event or <code>null</code>
     */
    @Nullable
    Event getByName(@Nonnull String name);

    /*
     * Finding all events that air on specified date range
     *
     * @param from Start date
     *
     * @param to End date inclusive
     *
     * @return Set of events
     */
    @Nonnull
    Set<Event> getForDateRange(@Nonnull LocalDateTime from,
            @Nonnull LocalDateTime to);

    /*
     * Return events from 'now' till the the specified date time
     *
     * @param to End date time inclusive
     * s
     * @return Set of events
     */
    @Nonnull
    Set<Event> getNextEvents(@Nonnull LocalDateTime to);

}
