package ua.training.spring.hometask.service.impl;


import ua.training.spring.hometask.domain.Auditorium;
import ua.training.spring.hometask.service.AuditoriumService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

public class DefaultAuditoriumService implements AuditoriumService {

    public DefaultAuditoriumService() {
    }

    private Set<Auditorium> auditoriums;

    public DefaultAuditoriumService(Set<Auditorium> auditoriums) {
        this.auditoriums = auditoriums;
    }

    @Nonnull
    @Override
    public Set<Auditorium> getAll() {
        return auditoriums;
    }

    @Nullable
    @Override
    public Auditorium getByName(@Nonnull String name) {
        return auditoriums.stream().filter(au -> au.getName().equals(name)).findAny().get();
    }

    public void setAuditoriums(Set<Auditorium> auditoriums) {
        this.auditoriums = auditoriums;
    }
}
