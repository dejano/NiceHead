
package rs.ac.uns.ftn.xws.ws.mpb;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.6.5
 * 2015-05-20T01:00:02.479+02:00
 * Generated source version: 2.6.5
 * 
 */
public final class MpbDocument_MpbDocumentPort_Client {

    private static final QName SERVICE_NAME = new QName("http://www.ftn.uns.ac.rs/xws/ws/mpb", "MpbDocumentService");

    private MpbDocument_MpbDocumentPort_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = MpbDocumentService.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        MpbDocumentService ss = new MpbDocumentService(wsdlURL, SERVICE_NAME);
        MpbDocument port = ss.getMpbDocumentPort();  
        
        {
        System.out.println("Invoking rtgsConfirm...");
        rs.ac.uns.ftn.xws.generated.RtgsConfirmMessage _rtgsConfirm_rtgsConfirmPart = null;
        port.rtgsConfirm(_rtgsConfirm_rtgsConfirmPart);


        }
        {
        System.out.println("Invoking clearingConfirm...");
        rs.ac.uns.ftn.xws.generated.ClearingConfirmMessage _clearingConfirm_clearingConfirmPart = null;
        port.clearingConfirm(_clearingConfirm_clearingConfirmPart);


        }

        System.exit(0);
    }

}
