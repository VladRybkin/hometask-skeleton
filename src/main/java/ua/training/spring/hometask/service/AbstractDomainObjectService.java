package ua.training.spring.hometask.service;

import ua.training.spring.hometask.domain.DomainObject;

import javax.annotation.Nonnull;
import java.util.Collection;


public interface AbstractDomainObjectService<T extends DomainObject> {


    T save(@Nonnull T object);

    void remove(@Nonnull T object);


    T getById(@Nonnull Long id);


    @Nonnull
    Collection<T> getAll();
}
