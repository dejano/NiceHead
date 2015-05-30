package rs.ac.uns.ftn.xmlbsep.dao;

import rs.ac.uns.ftn.xmlbsep.xmldb.CustomResultHandler;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Created by dejan on 25.5.2015..
 */
public interface GenericDaoLocal<T, ID extends Serializable> {

    <G> List<G> findBy(String xQuery, CustomResultHandler<G> rowMapper) throws IOException, JAXBException;
    /**
     * Find and return entity with passed id.
     *
     * @param id of the entity to return
     * @return entity with passed id or null if not found
     */
    T findById(ID id) throws IOException, JAXBException;

    /**
     * Return back all existing entities.
     *
     * @return list of existing entities, empty list if there are no entities
     */
    List<T> findAll() throws IOException, JAXBException;

    /**
     * Save entity and return saved instance (with id set).
     *
     * @param entity to be saved
     * @return saved instance
     */
    T persist(T entity) throws Exception;

    /**
     *
     * @param entity to be merged
     * @param id of entity to merge with
     * @return new merged entity
     */
    T merge(T entity, ID id) throws IOException, JAXBException ;

    /**
     * Remove entity with passed id.
     *
     * @param id of the entity to be removed
     * @throws IllegalArgumentException if there is no entity with passed id
     */
    void remove(ID id) throws IllegalArgumentException, IOException;

}
