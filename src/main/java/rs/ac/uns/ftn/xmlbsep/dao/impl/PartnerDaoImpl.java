package rs.ac.uns.ftn.xmlbsep.dao.impl;

import rs.ac.uns.ftn.xmlbsep.beans.jaxb.generated.partner.Partner;
import rs.ac.uns.ftn.xmlbsep.dao.PartnerDaoLocal;
import rs.ac.uns.ftn.xmlbsep.xmldb.CustomResultHandler;

import javax.ejb.Local;
import javax.ejb.Stateless;
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
@Stateless
@Local(PartnerDaoLocal.class)
public class PartnerDaoImpl extends GenericDao<Partner, Long> implements PartnerDaoLocal {

    public static final String contextPath = "rs.ac.uns.ftn.xmlbsep.beans.jaxb.generated.partner";

    public static final String schemaName = "partner";

    private static final String XBASE_NAMESPACE_QUERY = "declare default element namespace \"http://www.ftn.uns.ac.rs/xmlbsep/company\";";

    private static final String queryFindPib = "//company [pib = '%s']";

    public PartnerDaoImpl() {
        super(contextPath, schemaName);
    }

    public boolean isPartner(String partnerId) throws IOException, JAXBException {
        List<Boolean> result = findBy(String.format(XBASE_NAMESPACE_QUERY + queryFindPib, partnerId), new PartnerResultHandler());
        return result.size() > 0;
    }

    private static class PartnerResultHandler implements CustomResultHandler<Boolean> {

        public void handle(Unmarshaller basex_unmarshaller, Unmarshaller unmarshaller, InputStream is, List list) throws JAXBException {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            try {
                if (br.readLine() != null) {
                    list.add(true);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
