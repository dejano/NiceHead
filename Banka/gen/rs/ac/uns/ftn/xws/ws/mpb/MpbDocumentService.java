package rs.ac.uns.ftn.xws.ws.mpb;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.6.5
 * 2015-05-24T20:21:16.869+02:00
 * Generated source version: 2.6.5
 * 
 */
@WebServiceClient(name = "MpbDocumentService", 
                  wsdlLocation = "file:/C:/Users/Bandjur/Desktop/Workspace/XWS-BSEP-PI/XWS/NiceHead/Banka/WEB-INF/wsdl/mpb.wsdl",
                  targetNamespace = "http://www.ftn.uns.ac.rs/xws/ws/mpb") 
public class MpbDocumentService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://www.ftn.uns.ac.rs/xws/ws/mpb", "MpbDocumentService");
    public final static QName MpbDocumentPort = new QName("http://www.ftn.uns.ac.rs/xws/ws/mpb", "MpbDocumentPort");
    static {
        URL url = null;
        try {
            url = new URL("file:/C:/Users/Bandjur/Desktop/Workspace/XWS-BSEP-PI/XWS/NiceHead/Banka/WEB-INF/wsdl/mpb.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(MpbDocumentService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/C:/Users/Bandjur/Desktop/Workspace/XWS-BSEP-PI/XWS/NiceHead/Banka/WEB-INF/wsdl/mpb.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public MpbDocumentService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public MpbDocumentService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public MpbDocumentService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public MpbDocumentService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public MpbDocumentService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public MpbDocumentService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns MpbDocument
     */
    @WebEndpoint(name = "MpbDocumentPort")
    public MpbDocument getMpbDocumentPort() {
        return super.getPort(MpbDocumentPort, MpbDocument.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns MpbDocument
     */
    @WebEndpoint(name = "MpbDocumentPort")
    public MpbDocument getMpbDocumentPort(WebServiceFeature... features) {
        return super.getPort(MpbDocumentPort, MpbDocument.class, features);
    }

}
