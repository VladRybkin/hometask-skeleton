package ua.training.spring.hometask.dao;

import ua.training.spring.hometask.domain.DomainObject;

import javax.annotation.Nonnull;
import java.util.Collection;


public interface AbstractDomainObjectDao<T extends DomainObject> {


    public T save(T object);

    /**
     * Removing object from storage
     *
     * @param object Object to remove
     */
    public void remove( T object);

    /**
     * Getting object by id from storage
     *
     * @param id id of the object
     * @return Found object or <code>null</code>
     */
    public T getById( Long id);

    /**
     * Getting all objects from storage
     *
     * @return collection of objects
     */
    public
    Collection<T> getAll();

}
