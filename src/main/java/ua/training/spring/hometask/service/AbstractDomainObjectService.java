package ua.training.spring.hometask.service;

import ua.training.spring.hometask.domain.DomainObject;

import java.util.Collection;


public interface AbstractDomainObjectService<T extends DomainObject> {

    T save(T object);

    void remove(T object);

    T getById(Long id);

    Collection<T> getAll();

    Collection<T> saveAll(Collection<T> collection);
}
