package ua.training.spring.hometask.service.impl;


import ua.training.spring.hometask.domain.Auditorium;
import ua.training.spring.hometask.exceptions.AuditoriumNotFoundException;
import ua.training.spring.hometask.service.AuditoriumService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;
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
        Optional<Auditorium> optionalAuditorium = auditoriums.stream().filter(au -> au.getName().equals(name)).findAny();
        if (optionalAuditorium.isPresent()) {
            return optionalAuditorium.get();
        } else {
            throw new AuditoriumNotFoundException("Auditorium with name:" + name + " not found");
        }
    }

    public void setAuditoriums(Set<Auditorium> auditoriums) {
        this.auditoriums = auditoriums;
    }
}
