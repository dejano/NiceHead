package rs.ac.uns.ftn.xmlbsep.xmldb;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.util.List;

/**
 * Created by dejan on 29.5.2015..
 */
public class IdentityRowMapper implements CustomResultHandler<Long> {

    public void handle(Unmarshaller basex_unmarshaller, Unmarshaller unmarshaller, InputStream is, List<Long> list) throws JAXBException {

    }
}
