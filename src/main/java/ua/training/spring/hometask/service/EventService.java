package ua.training.spring.hometask.service;

import ua.training.spring.hometask.domain.Event;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.Set;


public interface EventService extends AbstractDomainObjectService<Event> {


    @Nullable
    Event getByName(@Nonnull String name);


    @Nonnull
    Set<Event> getForDateRange(@Nonnull LocalDateTime from,
            @Nonnull LocalDateTime to);

    @Nonnull
    Set<Event> getNextEvents(@Nonnull LocalDateTime to);

}
