
package rs.ac.uns.ftn.xws.ws.mpb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the rs.ac.uns.ftn.xws.ws.mpb package. 
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

    private final static QName _RtgsConfirm_QNAME = new QName("http://www.ftn.uns.ac.rs/xws/ws/mpb", "rtgsConfirm");
    private final static QName _ClearingConfirm_QNAME = new QName("http://www.ftn.uns.ac.rs/xws/ws/mpb", "clearingConfirm");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: rs.ac.uns.ftn.xws.ws.mpb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ClearingConfirmType }
     * 
     */
    public ClearingConfirmType createClearingConfirmType() {
        return new ClearingConfirmType();
    }

    /**
     * Create an instance of {@link RtgsConfirmType }
     * 
     */
    public RtgsConfirmType createRtgsConfirmType() {
        return new RtgsConfirmType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RtgsConfirmType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ftn.uns.ac.rs/xws/ws/mpb", name = "rtgsConfirm")
    public JAXBElement<RtgsConfirmType> createRtgsConfirm(RtgsConfirmType value) {
        return new JAXBElement<RtgsConfirmType>(_RtgsConfirm_QNAME, RtgsConfirmType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClearingConfirmType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ftn.uns.ac.rs/xws/ws/mpb", name = "clearingConfirm")
    public JAXBElement<ClearingConfirmType> createClearingConfirm(ClearingConfirmType value) {
        return new JAXBElement<ClearingConfirmType>(_ClearingConfirm_QNAME, ClearingConfirmType.class, null, value);
    }

}
