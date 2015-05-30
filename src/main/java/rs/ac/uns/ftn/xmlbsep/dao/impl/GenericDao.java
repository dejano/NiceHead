package rs.ac.uns.ftn.xmlbsep.dao.impl;

import rs.ac.uns.ftn.xmlbsep.beans.jaxb.AbstractBaseEntity;
import rs.ac.uns.ftn.xmlbsep.dao.GenericDaoLocal;
import rs.ac.uns.ftn.xmlbsep.xmldb.EntityManagerBaseX;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

/**
 * Created by dejan on 27.5.2015..
 */
public abstract class GenericDao<T extends AbstractBaseEntity, ID extends Serializable> implements GenericDaoLocal<T, ID> {

    protected String contextPath;

    protected JAXBContext context;

    protected EntityManagerBaseX<T, ID> em;

    public GenericDao(String contextPath, String schemaName) {

        try {
            context = JAXBContext.newInstance(contextPath);
            em = new EntityManagerBaseX<T, ID>(schemaName, contextPath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void parseMultipleResults(List<T> results, InputStream input) throws JAXBException, IOException {
        em.parseMultipleResults(results, input);
    }

    public T parseResult(InputStream input) throws JAXBException, IOException {
        return em.parseResult(input);
    }

    public T persist(T entity) throws JAXBException, IOException {
        Long id = em.getIdentity();
        entity.setId(String.valueOf(id));
        em.persist(entity, id);
        return entity;
    }

    public T findById(ID id) throws IOException, JAXBException {
        T entity;
        entity = em.find(id);
        return entity;
    }

    public InputStream findBy(String xQuery, boolean wrap) throws IOException {
        InputStream result;
        result = em.executeQuery(xQuery, wrap);
        return result;
    }

    public List<T> findAll() throws IOException, JAXBException {
        List<T> result;
        result = em.findAll();
        return result;
    }

    public void remove(ID id) throws IOException {
        em.delete(id);
    }

    public T merge(T entity, ID id) throws IOException, JAXBException {
        em.update(entity, id);
        return entity;
    }
}
