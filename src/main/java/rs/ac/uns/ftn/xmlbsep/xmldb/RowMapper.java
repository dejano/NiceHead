package rs.ac.uns.ftn.xmlbsep.xmldb;

import org.basex.rest.Result;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * Created by dejan on 29.5.2015..
 */
public interface RowMapper<T> {

    T mapRow(Result wrappedResults, Unmarshaller unmarshaller) throws JAXBException;
}
