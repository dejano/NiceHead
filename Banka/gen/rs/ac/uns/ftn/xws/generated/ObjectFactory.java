
package rs.ac.uns.ftn.xws.generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the rs.ac.uns.ftn.xws.generated package. 
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

    private final static QName _MpExceptionEnum_QNAME = new QName("http://www.ftn.uns.ac.rs/xws/xsd/mp", "mpExceptionEnum");
    private final static QName _RtgsRequestMessage_QNAME = new QName("http://www.ftn.uns.ac.rs/xws/xsd/mp", "rtgsRequestMessage");
    private final static QName _RtgsConfirmMessage_QNAME = new QName("http://www.ftn.uns.ac.rs/xws/xsd/mp", "rtgsConfirmMessage");
    private final static QName _ClearingRequestMessage_QNAME = new QName("http://www.ftn.uns.ac.rs/xws/xsd/mp", "clearingRequestMessage");
    private final static QName _Mt102_QNAME = new QName("http://www.ftn.uns.ac.rs/xws/xsd/mt102", "mt102");
    private final static QName _Mt910_QNAME = new QName("http://www.ftn.uns.ac.rs/xws/xsd/mt910", "mt910");
    private final static QName _Mt103_QNAME = new QName("http://www.ftn.uns.ac.rs/xws/xsd/mt103", "mt103");
    private final static QName _MpcbResponseMessage_QNAME = new QName("http://www.ftn.uns.ac.rs/xws/xsd/mp", "mpcbResponseMessage");
    private final static QName _ClearingConfirmMessage_QNAME = new QName("http://www.ftn.uns.ac.rs/xws/xsd/mp", "clearingConfirmMessage");
    private final static QName _Mt900_QNAME = new QName("http://www.ftn.uns.ac.rs/xws/xsd/mt900", "mt900");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: rs.ac.uns.ftn.xws.generated
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
     * Create an instance of {@link Mt910 }
     * 
     */
    public Mt910 createMt910() {
        return new Mt910();
    }

    /**
     * Create an instance of {@link Mt900 }
     * 
     */
    public Mt900 createMt900() {
        return new Mt900();
    }

    /**
     * Create an instance of {@link BankDetails }
     * 
     */
    public BankDetails createBankDetails() {
        return new BankDetails();
    }

    /**
     * Create an instance of {@link AccountDetails }
     * 
     */
    public AccountDetails createAccountDetails() {
        return new AccountDetails();
    }

    /**
     * Create an instance of {@link ClearingRequestMessage }
     * 
     */
    public ClearingRequestMessage createClearingRequestMessage() {
        return new ClearingRequestMessage();
    }

    /**
     * Create an instance of {@link MpcbResponseMessage }
     * 
     */
    public MpcbResponseMessage createMpcbResponseMessage() {
        return new MpcbResponseMessage();
    }

    /**
     * Create an instance of {@link ClearingConfirmMessage }
     * 
     */
    public ClearingConfirmMessage createClearingConfirmMessage() {
        return new ClearingConfirmMessage();
    }

    /**
     * Create an instance of {@link RtgsRequestMessage }
     * 
     */
    public RtgsRequestMessage createRtgsRequestMessage() {
        return new RtgsRequestMessage();
    }

    /**
     * Create an instance of {@link RtgsConfirmMessage }
     * 
     */
    public RtgsConfirmMessage createRtgsConfirmMessage() {
        return new RtgsConfirmMessage();
    }

    /**
     * Create an instance of {@link Mt103 }
     * 
     */
    public Mt103 createMt103() {
        return new Mt103();
    }

    /**
     * Create an instance of {@link Mt102 .Payments }
     * 
     */
    public Mt102 .Payments createMt102Payments() {
        return new Mt102 .Payments();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MpExceptionEnum }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ftn.uns.ac.rs/xws/xsd/mp", name = "mpExceptionEnum")
    public JAXBElement<MpExceptionEnum> createMpExceptionEnum(MpExceptionEnum value) {
        return new JAXBElement<MpExceptionEnum>(_MpExceptionEnum_QNAME, MpExceptionEnum.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RtgsRequestMessage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ftn.uns.ac.rs/xws/xsd/mp", name = "rtgsRequestMessage")
    public JAXBElement<RtgsRequestMessage> createRtgsRequestMessage(RtgsRequestMessage value) {
        return new JAXBElement<RtgsRequestMessage>(_RtgsRequestMessage_QNAME, RtgsRequestMessage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RtgsConfirmMessage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ftn.uns.ac.rs/xws/xsd/mp", name = "rtgsConfirmMessage")
    public JAXBElement<RtgsConfirmMessage> createRtgsConfirmMessage(RtgsConfirmMessage value) {
        return new JAXBElement<RtgsConfirmMessage>(_RtgsConfirmMessage_QNAME, RtgsConfirmMessage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClearingRequestMessage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ftn.uns.ac.rs/xws/xsd/mp", name = "clearingRequestMessage")
    public JAXBElement<ClearingRequestMessage> createClearingRequestMessage(ClearingRequestMessage value) {
        return new JAXBElement<ClearingRequestMessage>(_ClearingRequestMessage_QNAME, ClearingRequestMessage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Mt102 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ftn.uns.ac.rs/xws/xsd/mt102", name = "mt102")
    public JAXBElement<Mt102> createMt102(Mt102 value) {
        return new JAXBElement<Mt102>(_Mt102_QNAME, Mt102 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Mt910 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ftn.uns.ac.rs/xws/xsd/mt910", name = "mt910")
    public JAXBElement<Mt910> createMt910(Mt910 value) {
        return new JAXBElement<Mt910>(_Mt910_QNAME, Mt910 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Mt103 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ftn.uns.ac.rs/xws/xsd/mt103", name = "mt103")
    public JAXBElement<Mt103> createMt103(Mt103 value) {
        return new JAXBElement<Mt103>(_Mt103_QNAME, Mt103 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MpcbResponseMessage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ftn.uns.ac.rs/xws/xsd/mp", name = "mpcbResponseMessage")
    public JAXBElement<MpcbResponseMessage> createMpcbResponseMessage(MpcbResponseMessage value) {
        return new JAXBElement<MpcbResponseMessage>(_MpcbResponseMessage_QNAME, MpcbResponseMessage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClearingConfirmMessage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ftn.uns.ac.rs/xws/xsd/mp", name = "clearingConfirmMessage")
    public JAXBElement<ClearingConfirmMessage> createClearingConfirmMessage(ClearingConfirmMessage value) {
        return new JAXBElement<ClearingConfirmMessage>(_ClearingConfirmMessage_QNAME, ClearingConfirmMessage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Mt900 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ftn.uns.ac.rs/xws/xsd/mt900", name = "mt900")
    public JAXBElement<Mt900> createMt900(Mt900 value) {
        return new JAXBElement<Mt900>(_Mt900_QNAME, Mt900 .class, null, value);
    }

}
