package ua.training.spring.hometask.service;

import ua.training.spring.hometask.domain.Auditorium;

import java.util.Set;


public interface AuditoriumService {

    Set<Auditorium> getAll();

    Auditorium getByName(String name);
}
