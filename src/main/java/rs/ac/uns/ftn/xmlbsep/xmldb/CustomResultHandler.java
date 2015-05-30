package rs.ac.uns.ftn.xmlbsep.xmldb;

import org.basex.rest.Result;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.util.List;

/**
 * Created by dejan on 29.5.2015..
 */
public interface CustomResultHandler<T> {

    void handle(Unmarshaller basex_unmarshaller, Unmarshaller unmarshaller, InputStream is, List<T> list) throws JAXBException;
}
