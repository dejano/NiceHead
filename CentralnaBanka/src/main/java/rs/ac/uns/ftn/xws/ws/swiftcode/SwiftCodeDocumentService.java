package rs.ac.uns.ftn.xws.ws.swiftcode;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.6.5
 * 2015-05-16T22:10:49.197+02:00
 * Generated source version: 2.6.5
 * 
 */
@WebServiceClient(name = "SwiftCodeDocumentService", 
                  wsdlLocation = "file:/C:/Users/nikola42/Documents/Fakultet/XWS/projekat/NiceHead/CentralnaBanka/WEB-INF/wsdl/swiftCode.wsdl",
                  targetNamespace = "http://www.ftn.uns.ac.rs/xws/ws/swiftCode") 
public class SwiftCodeDocumentService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://www.ftn.uns.ac.rs/xws/ws/swiftCode", "SwiftCodeDocumentService");
    public final static QName SwiftCodeDocumentPort = new QName("http://www.ftn.uns.ac.rs/xws/ws/swiftCode", "SwiftCodeDocumentPort");
    static {
        URL url = null;
        try {
            url = new URL("file:/C:/Users/nikola42/Documents/Fakultet/XWS/projekat/NiceHead/CentralnaBanka/WEB-INF/wsdl/swiftCode.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(SwiftCodeDocumentService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/C:/Users/nikola42/Documents/Fakultet/XWS/projekat/NiceHead/CentralnaBanka/WEB-INF/wsdl/swiftCode.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public SwiftCodeDocumentService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public SwiftCodeDocumentService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SwiftCodeDocumentService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public SwiftCodeDocumentService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public SwiftCodeDocumentService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public SwiftCodeDocumentService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns SwiftCodeDocument
     */
    @WebEndpoint(name = "SwiftCodeDocumentPort")
    public SwiftCodeDocument getSwiftCodeDocumentPort() {
        return super.getPort(SwiftCodeDocumentPort, SwiftCodeDocument.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SwiftCodeDocument
     */
    @WebEndpoint(name = "SwiftCodeDocumentPort")
    public SwiftCodeDocument getSwiftCodeDocumentPort(WebServiceFeature... features) {
        return super.getPort(SwiftCodeDocumentPort, SwiftCodeDocument.class, features);
    }

}