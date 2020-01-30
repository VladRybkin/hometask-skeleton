package ua.training.spring.hometask.service;

import ua.training.spring.hometask.domain.Auditorium;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;


public interface AuditoriumService {


    @Nonnull
    Set<Auditorium> getAll();

    @Nullable
    Auditorium getByName(@Nonnull String name);

}
