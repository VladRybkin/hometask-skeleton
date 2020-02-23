package ua.training.spring.hometask.dao;

import ua.training.spring.hometask.domain.DomainObject;

import java.util.Collection;


public interface AbstractDomainObjectDao<T extends DomainObject> {

    T save(T object);

    void remove(T object);

    T getById(Long id);

    Collection<T> getAll();
}
