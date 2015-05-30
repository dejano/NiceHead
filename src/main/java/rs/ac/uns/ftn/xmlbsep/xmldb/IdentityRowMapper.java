package rs.ac.uns.ftn.xmlbsep.xmldb;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by dejan on 29.5.2015..
 */
public class IdentityRowMapper implements CustomResultHandler<Long> {

    public void handle(Unmarshaller basex_unmarshaller, Unmarshaller unmarshaller, InputStream is, List<Long> list) throws JAXBException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        try {
            String line = br.readLine();
            if (line != null)
                list.add(Long.valueOf(line) + 1L);
            else
                list.add(1L);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
