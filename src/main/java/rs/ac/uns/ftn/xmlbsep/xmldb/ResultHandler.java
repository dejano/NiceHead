package rs.ac.uns.ftn.xmlbsep.xmldb;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dejan on 29.5.2015..
 */
public class ResultHandler<T> {

    List<T> handleResult(Unmarshaller basex_unmarshaller, Unmarshaller unmarshaller, InputStream is, CustomResultHandler<T> mapper) throws JAXBException {
        List<T> list = new ArrayList<T>();
        mapper.handle(basex_unmarshaller, unmarshaller, is, list);

        return list;
    }

}
