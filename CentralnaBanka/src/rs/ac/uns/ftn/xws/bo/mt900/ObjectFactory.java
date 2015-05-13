
package rs.ac.uns.ftn.xws.bo.mt900;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the rs.ac.uns.ftn.xws.bo.mt900 package. 
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

    private final static QName _Mt900_QNAME = new QName("http://www.ftn.uns.ac.rs/xws/bo/mt900", "mt900");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: rs.ac.uns.ftn.xws.bo.mt900
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Mt900 }
     * 
     */
    public Mt900 createMt900() {
        return new Mt900();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Mt900 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ftn.uns.ac.rs/xws/bo/mt900", name = "mt900")
    public JAXBElement<Mt900> createMt900(Mt900 value) {
        return new JAXBElement<Mt900>(_Mt900_QNAME, Mt900 .class, null, value);
    }

}
