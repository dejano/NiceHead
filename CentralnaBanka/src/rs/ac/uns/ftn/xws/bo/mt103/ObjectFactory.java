
package rs.ac.uns.ftn.xws.bo.mt103;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the rs.ac.uns.ftn.xws.bo.mt103 package. 
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

    private final static QName _Mt103_QNAME = new QName("http://www.ftn.uns.ac.rs/xws/bo/mt103", "mt103");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: rs.ac.uns.ftn.xws.bo.mt103
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Mt103 }
     * 
     */
    public Mt103 createMt103() {
        return new Mt103();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Mt103 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ftn.uns.ac.rs/xws/bo/mt103", name = "mt103")
    public JAXBElement<Mt103> createMt103(Mt103 value) {
        return new JAXBElement<Mt103>(_Mt103_QNAME, Mt103 .class, null, value);
    }

}
