package rs.ac.uns.ftn.xmlbsep.service;

import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

/**
 * Created by dejan on 25.5.2015..
 */
public interface CrudService <T extends Object> {

    /**
     * Find and return entity with passed id.
     *
     * @param id of the entity to return
     * @return entity with passed id or null if not found
     */
    T getOne(Long id);

    /**
     * Return back all existing entities.
     *
     * @return list of existing entities, empty list if there are no entities
     */
    List<T> findAll();

    /**
     * Save entity and return saved instance (with id set).
     *
     * @param entity to be saved
     * @return saved instance
     */
    T save(T entity) throws IOException, JAXBException, SAXException;

    /**
     * Remove entity with passed id.
     *
     * @param id of the entity to be removed
     * @throws IllegalArgumentException if there is no entity with passed id
     */
    void remove(Long id) throws IllegalArgumentException;
}
