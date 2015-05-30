package rs.ac.uns.ftn.xmlbsep.xmldb;

import net.xqj.basex.bin.T;
import org.basex.rest.Result;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringWriter;

/**
 * Created by dejan on 29.5.2015..
 */
public class IdentityRowMapper implements RowMapper<Long> {
    public Long mapRow(Result wrappedResults, Unmarshaller unmarshaller) throws JAXBException {
        StringWriter sw = new StringWriter();
        wrappedResults.getAny();
        JAXB.marshal(wrappedResults, sw);
        System.out.println("IdentityRowMapper.mapRow");
        System.out.println(sw.toString());
        System.out.println("IdentityRowMapper.mapRow");
        return 1L;
    }
}
