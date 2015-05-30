package rs.ac.uns.ftn.xmlbsep.xmldb;

import org.basex.rest.Result;
import org.basex.rest.Results;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dejan on 29.5.2015..
 */
public class ResultHandler<T> {

    List<T> handleResult(Unmarshaller basex_unmarshaller, Unmarshaller unmarshaller, InputStream is, RowMapper<T> mapper) throws JAXBException {
        List<T> list = new ArrayList<T>();
        Results wrappedResults = (Results) basex_unmarshaller.unmarshal(is);

        for (Result result : wrappedResults.getResult()) {
            list.add(mapper.mapRow(result, unmarshaller));
        }

        return list;
    }
}
