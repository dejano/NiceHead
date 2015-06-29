package rs.ac.uns.ftn.xws.ws.crl;

import java.util.logging.Logger;

import javax.ejb.Stateless;

import rs.ac.uns.ftn.xws.dao.CrlDao;

@Stateless
@javax.jws.WebService(
                      serviceName = "CrlDocumentService",
                      portName = "CrlDocumentPort",
                      targetNamespace = "http://www.ftn.uns.ac.rs/xws/ws/crl",
                      wsdlLocation = "file:/C:/Users/Nikola/Documents/Fakultet/XWS/projekat/NiceHead/CentralnaBanka/WEB-INF/wsdl/crl.wsdl",
                      endpointInterface = "rs.ac.uns.ftn.xws.ws.crl.CrlDocument")
                      
public class CrlDocumentImpl implements CrlDocument {

    private static final Logger LOG = Logger.getLogger(CrlDocumentImpl.class.getName());

    public boolean isInCrl(String certSerial) { 
        LOG.info("Executing operation isInCrl");
        return CrlDao.isInCrl(certSerial);
    }

}
