
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package rs.ac.uns.ftn.xws.ws.bsws;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.6.5
 * 2015-05-22T15:07:57.542+02:00
 * Generated source version: 2.6.5
 * 
 */

@Stateless
@javax.jws.WebService(
                      serviceName = "BsDocumentService",
                      portName = "BsDocumentPort",
                      targetNamespace = "http://www.ftn.uns.ac.rs/xws/ws/bsws",
                      wsdlLocation = "file:/C:/Users/Bandjur/Desktop/Workspace/XWS-BSEP-PI/XWS/NiceHead/Banka/WEB-INF/wsdl/bstatement.wsdl",
                      endpointInterface = "rs.ac.uns.ftn.xws.ws.bsws.BsDocument")
                      
public class BsDocumentImpl implements BsDocument {

    private static final Logger LOG = Logger.getLogger(BsDocumentImpl.class.getName());

    /* (non-Javadoc)
     * @see rs.ac.uns.ftn.xws.ws.bsws.BsDocument#getSectionalData(rs.ac.uns.ftn.xws.bstatementRequest.BankStatmentRequest  bsRequestPart )*
     */
    public rs.ac.uns.ftn.xws.bstatement.SectionalData getSectionalData(rs.ac.uns.ftn.xws.bstatementRequest.BankStatmentRequest bsRequestPart) { 
        LOG.info("Executing operation getSectionalData");
        System.out.println(bsRequestPart);
        try {
            rs.ac.uns.ftn.xws.bstatement.SectionalData _return = new rs.ac.uns.ftn.xws.bstatement.SectionalData();
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}
