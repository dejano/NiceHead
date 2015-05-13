
package rs.ac.uns.ftn.xws.bo.mt102;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the rs.ac.uns.ftn.xws.bo.mt102 package. 
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

    private final static QName _Mt102_QNAME = new QName("http://www.ftn.uns.ac.rs/xws/bo/mt102", "mt102");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: rs.ac.uns.ftn.xws.bo.mt102
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Mt102 }
     * 
     */
    public Mt102 createMt102() {
        return new Mt102();
    }

    /**
     * Create an instance of {@link Payment }
     * 
     */
    public Payment createPayment() {
        return new Payment();
    }

    /**
     * Create an instance of {@link Mt102 .Payments }
     * 
     */
    public Mt102 .Payments createMt102Payments() {
        return new Mt102 .Payments();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Mt102 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ftn.uns.ac.rs/xws/bo/mt102", name = "mt102")
    public JAXBElement<Mt102> createMt102(Mt102 value) {
        return new JAXBElement<Mt102>(_Mt102_QNAME, Mt102 .class, null, value);
    }

}
