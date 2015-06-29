//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.06.29 at 11:13:22 AM CEST 
//


package rs.ac.uns.ftn.xws.generated.crl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the rs.ac.uns.ftn.xws.generated.crl package. 
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

    private final static QName _CertSerial_QNAME = new QName("http://www.ftn.uns.ac.rs/xws/xsd/crl", "certSerial");
    private final static QName _IsRevoked_QNAME = new QName("http://www.ftn.uns.ac.rs/xws/xsd/crl", "isRevoked");
    private final static QName _CertificateRevocationList_QNAME = new QName("http://www.ftn.uns.ac.rs/xws/xsd/crl", "certificateRevocationList");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: rs.ac.uns.ftn.xws.generated.crl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CertificateRevocationList }
     * 
     */
    public CertificateRevocationList createCertificateRevocationList() {
        return new CertificateRevocationList();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ftn.uns.ac.rs/xws/xsd/crl", name = "certSerial")
    public JAXBElement<String> createCertSerial(String value) {
        return new JAXBElement<String>(_CertSerial_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ftn.uns.ac.rs/xws/xsd/crl", name = "isRevoked")
    public JAXBElement<Boolean> createIsRevoked(Boolean value) {
        return new JAXBElement<Boolean>(_IsRevoked_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CertificateRevocationList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ftn.uns.ac.rs/xws/xsd/crl", name = "certificateRevocationList")
    public JAXBElement<CertificateRevocationList> createCertificateRevocationList(CertificateRevocationList value) {
        return new JAXBElement<CertificateRevocationList>(_CertificateRevocationList_QNAME, CertificateRevocationList.class, null, value);
    }

}
