package rs.ac.uns.ftn.xmlbsep.dao;

import rs.ac.uns.ftn.xmlbsep.beans.jaxb.generated.partner.Partner;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

/**
 * Created by dejan on 29.5.2015..
 */

public interface PartnerDaoLocal extends GenericDaoLocal<Partner, Long> {
    boolean isPartner(String partnerId) throws IOException, JAXBException;

    List<Partner> getPartners() throws IOException, JAXBException;
}
