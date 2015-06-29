package rs.ac.uns.ftn.xws.ws.po;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.6.5
 * 2015-05-29T14:27:42.442+02:00
 * Generated source version: 2.6.5
 * 
 */
@WebServiceClient(name = "PoDocumentService", 
                  wsdlLocation = "file:/C:/Users/Nikola/Documents/Fakultet/XWS/projekat/NiceHead/Banka/WEB-INF/wsdl/po.wsdl",
                  targetNamespace = "http://www.ftn.uns.ac.rs/xws/ws/po") 
public class PoDocumentService extends Service {
//	file:/C:/Users/Nikola/Documents/Fakultet/XWS/projekat/NiceHead/Banka/WEB-INF/wsdl/bs.wsdl
    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://www.ftn.uns.ac.rs/xws/ws/po", "PoDocumentService");
    public final static QName PoDocumentPort = new QName("http://www.ftn.uns.ac.rs/xws/ws/po", "PoDocumentPort");
    static {
        URL url = null;
        try {
            url = new URL("file:/C:/Users/Nikola/Documents/Fakultet/XWS/projekat/NiceHead/Banka/WEB-INF/wsdl/po.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(PoDocumentService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/C:/Users/Nikola/Documents/Fakultet/XWS/projekat/NiceHead/Banka/WEB-INF/wsdl/po.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public PoDocumentService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public PoDocumentService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public PoDocumentService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public PoDocumentService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public PoDocumentService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public PoDocumentService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns PoDocument
     */
    @WebEndpoint(name = "PoDocumentPort")
    public PoDocument getPoDocumentPort() {
        return super.getPort(PoDocumentPort, PoDocument.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PoDocument
     */
    @WebEndpoint(name = "PoDocumentPort")
    public PoDocument getPoDocumentPort(WebServiceFeature... features) {
        return super.getPort(PoDocumentPort, PoDocument.class, features);
    }

}
