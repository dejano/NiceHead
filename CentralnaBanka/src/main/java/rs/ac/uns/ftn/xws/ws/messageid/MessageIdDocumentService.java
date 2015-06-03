package rs.ac.uns.ftn.xws.ws.messageid;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

@WebServiceClient(name = "MessageIdDocumentService", 
                  wsdlLocation = "file:/C:/Users/nikola42/Documents/Fakultet/XWS/projekat/NiceHead/CentralnaBanka/WEB-INF/wsdl/messageId.wsdl",
                  targetNamespace = "http://www.ftn.uns.ac.rs/xws/ws/messageId") 
public class MessageIdDocumentService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://www.ftn.uns.ac.rs/xws/ws/messageId", "MessageIdDocumentService");
    public final static QName MessageIdDocumentPort = new QName("http://www.ftn.uns.ac.rs/xws/ws/messageId", "MessageIdDocumentPort");
    static {
        URL url = null;
        try {
            url = new URL("file:/C:/Users/nikola42/Documents/Fakultet/XWS/projekat/NiceHead/CentralnaBanka/WEB-INF/wsdl/messageId.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(MessageIdDocumentService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/C:/Users/nikola42/Documents/Fakultet/XWS/projekat/NiceHead/CentralnaBanka/WEB-INF/wsdl/messageId.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public MessageIdDocumentService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public MessageIdDocumentService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public MessageIdDocumentService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public MessageIdDocumentService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public MessageIdDocumentService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public MessageIdDocumentService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns MessageIdDocument
     */
    @WebEndpoint(name = "MessageIdDocumentPort")
    public MessageIdDocument getMessageIdDocumentPort() {
        return super.getPort(MessageIdDocumentPort, MessageIdDocument.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns MessageIdDocument
     */
    @WebEndpoint(name = "MessageIdDocumentPort")
    public MessageIdDocument getMessageIdDocumentPort(WebServiceFeature... features) {
        return super.getPort(MessageIdDocumentPort, MessageIdDocument.class, features);
    }

}
