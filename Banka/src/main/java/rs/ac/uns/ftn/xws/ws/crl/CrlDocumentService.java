package rs.ac.uns.ftn.xws.ws.crl;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.6.5
 * 2015-06-28T18:09:15.633+02:00
 * Generated source version: 2.6.5
 * 
 */
@WebServiceClient(name = "CrlDocumentService", 
                  wsdlLocation = "file:/C:/Users/Nikola/Documents/Fakultet/XWS/projekat/NiceHead/CentralnaBanka/WEB-INF/wsdl/crl.wsdl",
                  targetNamespace = "http://www.ftn.uns.ac.rs/xws/ws/crl") 
public class CrlDocumentService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://www.ftn.uns.ac.rs/xws/ws/crl", "CrlDocumentService");
    public final static QName CrlDocumentPort = new QName("http://www.ftn.uns.ac.rs/xws/ws/crl", "CrlDocumentPort");
    static {
        URL url = null;
        try {
            url = new URL("file:/C:/Users/Nikola/Documents/Fakultet/XWS/projekat/NiceHead/CentralnaBanka/WEB-INF/wsdl/crl.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(CrlDocumentService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/C:/Users/Nikola/Documents/Fakultet/XWS/projekat/NiceHead/CentralnaBanka/WEB-INF/wsdl/crl.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public CrlDocumentService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public CrlDocumentService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CrlDocumentService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public CrlDocumentService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public CrlDocumentService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public CrlDocumentService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns CrlDocument
     */
    @WebEndpoint(name = "CrlDocumentPort")
    public CrlDocument getCrlDocumentPort() {
        return super.getPort(CrlDocumentPort, CrlDocument.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns CrlDocument
     */
    @WebEndpoint(name = "CrlDocumentPort")
    public CrlDocument getCrlDocumentPort(WebServiceFeature... features) {
        return super.getPort(CrlDocumentPort, CrlDocument.class, features);
    }

}
