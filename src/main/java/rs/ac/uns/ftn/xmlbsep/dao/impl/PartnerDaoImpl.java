package rs.ac.uns.ftn.xmlbsep.dao.impl;

import rs.ac.uns.ftn.xmlbsep.beans.jaxb.generated.partner.Partner;
import rs.ac.uns.ftn.xmlbsep.dao.PartnerDaoLocal;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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

    public boolean isPartner(String partnerId) throws IOException {
        InputStream isPartner = findBy(String.format(XBASE_NAMESPACE_QUERY + queryFindPib, partnerId), false);
        BufferedReader br = new BufferedReader(new InputStreamReader(isPartner));
        return br.readLine() != null;
    }
}
