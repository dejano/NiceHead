
package rs.ac.uns.ftn.xws.ws.swiftcode;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the rs.ac.uns.ftn.xws.ws.swiftcode package. 
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

    private final static QName _BankClearingAccountNumber_QNAME = new QName("http://www.ftn.uns.ac.rs/xws/ws/swiftCode", "bankClearingAccountNumber");
    private final static QName _SwiftCode_QNAME = new QName("http://www.ftn.uns.ac.rs/xws/ws/swiftCode", "swiftCode");
    private final static QName _NoBankAccountException_QNAME = new QName("http://www.ftn.uns.ac.rs/xws/ws/swiftCode", "noBankAccountException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: rs.ac.uns.ftn.xws.ws.swiftcode
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ftn.uns.ac.rs/xws/ws/swiftCode", name = "bankClearingAccountNumber")
    public JAXBElement<String> createBankClearingAccountNumber(String value) {
        return new JAXBElement<String>(_BankClearingAccountNumber_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ftn.uns.ac.rs/xws/ws/swiftCode", name = "swiftCode")
    public JAXBElement<String> createSwiftCode(String value) {
        return new JAXBElement<String>(_SwiftCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ftn.uns.ac.rs/xws/ws/swiftCode", name = "noBankAccountException")
    public JAXBElement<String> createNoBankAccountException(String value) {
        return new JAXBElement<String>(_NoBankAccountException_QNAME, String.class, null, value);
    }

}
