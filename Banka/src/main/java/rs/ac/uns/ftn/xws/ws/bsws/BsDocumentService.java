package rs.ac.uns.ftn.xws.ws.bsws;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.6.5
 * 2015-06-02T02:38:31.953+02:00
 * Generated source version: 2.6.5
 * 
 */
@WebServiceClient(name = "BsDocumentService", 
                  wsdlLocation = "file:/C:/Users/nikola42/Documents/Fakultet/XWS/projekat/NiceHead/Banka/WEB-INF/wsdl/bs.wsdl",
                  targetNamespace = "http://www.ftn.uns.ac.rs/xws/ws/bsws") 
public class BsDocumentService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://www.ftn.uns.ac.rs/xws/ws/bsws", "BsDocumentService");
    public final static QName BsDocumentPort = new QName("http://www.ftn.uns.ac.rs/xws/ws/bsws", "BsDocumentPort");
    static {
        URL url = null;
        try {
            url = new URL("file:/C:/Users/nikola42/Documents/Fakultet/XWS/projekat/NiceHead/Banka/WEB-INF/wsdl/bs.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(BsDocumentService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/C:/Users/nikola42/Documents/Fakultet/XWS/projekat/NiceHead/Banka/WEB-INF/wsdl/bs.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public BsDocumentService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public BsDocumentService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public BsDocumentService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public BsDocumentService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public BsDocumentService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public BsDocumentService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns BsDocument
     */
    @WebEndpoint(name = "BsDocumentPort")
    public BsDocument getBsDocumentPort() {
        return super.getPort(BsDocumentPort, BsDocument.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns BsDocument
     */
    @WebEndpoint(name = "BsDocumentPort")
    public BsDocument getBsDocumentPort(WebServiceFeature... features) {
        return super.getPort(BsDocumentPort, BsDocument.class, features);
    }

}
