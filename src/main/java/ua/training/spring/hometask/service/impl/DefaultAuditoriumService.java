package ua.training.spring.hometask.service.impl;

import ua.training.spring.hometask.domain.Auditorium;
import ua.training.spring.hometask.exceptions.AuditoriumNotFoundException;
import ua.training.spring.hometask.service.AuditoriumService;

import java.util.Optional;
import java.util.Set;

public class DefaultAuditoriumService implements AuditoriumService {

    private Set<Auditorium> auditoriums;

    public DefaultAuditoriumService(Set<Auditorium> auditoriums) {
        this.auditoriums = auditoriums;
    }

    @Override
    public Set<Auditorium> getAll() {
        return auditoriums;
    }

    @Override
    public Auditorium getByName(final String name) {
        Optional<Auditorium> optionalAuditorium = auditoriums.stream().filter(au -> au.getName().equals(name)).findAny();
        if (optionalAuditorium.isPresent()) {
            return optionalAuditorium.get();
        } else {
            throw new AuditoriumNotFoundException("Auditorium with name:" + name + " not found");
        }
    }
}
