package rs.ac.uns.ftn.xmlbsep.xmldb;

import org.basex.rest.Result;
import org.w3c.dom.Node;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * Created by dejan on 29.5.2015..
 */
public class GenericRowMapper<T> implements RowMapper<T> {

    @SuppressWarnings("unchecked")
    public T mapRow(Result wrappedResult, Unmarshaller unmarshaller) throws JAXBException {
        return (T) unmarshaller.unmarshal((Node)wrappedResult.getAny());
    }
}
