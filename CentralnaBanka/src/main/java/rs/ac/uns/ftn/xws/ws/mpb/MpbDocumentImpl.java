
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package rs.ac.uns.ftn.xws.ws.mpb;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.6.5
 * 2015-05-15T17:45:33.200+02:00
 * Generated source version: 2.6.5
 * 
 */

@Stateless
@javax.jws.WebService(
                      serviceName = "MpbDocumentService",
                      portName = "MpbDocumentPort",
                      targetNamespace = "http://www.ftn.uns.ac.rs/xws/ws/mpb",
                      wsdlLocation = "file:/C:/Users/nikola42/Documents/Fakultet/XWS/projekat/NiceHead/CentralnaBanka/WEB-INF/wsdl/mpb.wsdl",
                      endpointInterface = "rs.ac.uns.ftn.xws.ws.mpb.MpbDocument")
                      
public class MpbDocumentImpl implements MpbDocument {

    private static final Logger LOG = Logger.getLogger(MpbDocumentImpl.class.getName());

    /* (non-Javadoc)
     * @see rs.ac.uns.ftn.xws.ws.mpb.MpbDocument#rtgsConfirm(rs.ac.uns.ftn.xws.generated.RtgsConfirmMessage  rtgsConfirmPart )*
     */
    public void rtgsConfirm(rs.ac.uns.ftn.xws.generated.RtgsConfirmMessage rtgsConfirmPart) { 
        LOG.info("Executing operation rtgsConfirm");
        System.out.println(rtgsConfirmPart);
        try {
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see rs.ac.uns.ftn.xws.ws.mpb.MpbDocument#clearingConfirm(rs.ac.uns.ftn.xws.generated.ClearingConfirmMessage  clearingConfirmPart )*
     */
    public void clearingConfirm(rs.ac.uns.ftn.xws.generated.ClearingConfirmMessage clearingConfirmPart) { 
        LOG.info("Executing operation clearingConfirm");
        System.out.println(clearingConfirmPart);
        try {
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}