package rs.ac.uns.ftn.xmlbsep.xmldb;

import org.basex.rest.Result;
import org.basex.rest.Results;
import org.w3c.dom.Node;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.util.List;

/**
 * Created by dejan on 29.5.2015..
 */
public class GenericResultHandler<T> implements CustomResultHandler<T> {

    @SuppressWarnings("unchecked")
    public void handle(Unmarshaller basex_unmarshaller, Unmarshaller unmarshaller, InputStream is,List<T> list) throws JAXBException {
        Results wrappedResults = (Results) basex_unmarshaller.unmarshal(is);

        for (Result result : wrappedResults.getResult()) {
            list.add((T) unmarshaller.unmarshal((Node)result.getAny()));
        }
    }
}
