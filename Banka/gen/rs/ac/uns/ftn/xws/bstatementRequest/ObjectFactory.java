
package rs.ac.uns.ftn.xws.bstatementRequest;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the rs.ac.uns.ftn.xws.bstatementRequest package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _BankStatementRequest_QNAME = new QName("http://www.ftn.uns.ac.rs/xws/xsd/bstatementRequest", "bankStatementRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: rs.ac.uns.ftn.xws.bstatementRequest
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BankStatmentRequest }
     * 
     */
    public BankStatmentRequest createBankStatmentRequest() {
        return new BankStatmentRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BankStatmentRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ftn.uns.ac.rs/xws/xsd/bstatementRequest", name = "bankStatementRequest")
    public JAXBElement<BankStatmentRequest> createBankStatementRequest(BankStatmentRequest value) {
        return new JAXBElement<BankStatmentRequest>(_BankStatementRequest_QNAME, BankStatmentRequest.class, null, value);
    }

}
