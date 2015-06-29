
package rs.ac.uns.ftn.xmlbsep.beans.jaxb.generated.payment;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the rs.ac.uns.ftn.xmlbsep.beans.jaxb.generated.payment package. 
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

    private final static QName _PoExceptionEnum_QNAME = new QName("http://www.ftn.uns.ac.rs/xws/xsd/paymentOrder", "poExceptionEnum");
    private final static QName _BankDetails_QNAME = new QName("http://www.ftn.uns.ac.rs/xws/xsd/common", "bankDetails");
    private final static QName _PaymentOrder_QNAME = new QName("http://www.ftn.uns.ac.rs/xws/xsd/paymentOrder", "paymentOrder");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: rs.ac.uns.ftn.xmlbsep.beans.jaxb.generated.payment
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PaymentOrder }
     * 
     */
    public PaymentOrder createPaymentOrder() {
        return new PaymentOrder();
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
     * Create an instance of {@link Payment }
     * 
     */
    public Payment createPayment() {
        return new Payment();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PoExceptionEnum }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ftn.uns.ac.rs/xws/xsd/paymentOrder", name = "poExceptionEnum")
    public JAXBElement<PoExceptionEnum> createPoExceptionEnum(PoExceptionEnum value) {
        return new JAXBElement<PoExceptionEnum>(_PoExceptionEnum_QNAME, PoExceptionEnum.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BankDetails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ftn.uns.ac.rs/xws/xsd/common", name = "bankDetails")
    public JAXBElement<BankDetails> createBankDetails(BankDetails value) {
        return new JAXBElement<BankDetails>(_BankDetails_QNAME, BankDetails.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PaymentOrder }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ftn.uns.ac.rs/xws/xsd/paymentOrder", name = "paymentOrder")
    public JAXBElement<PaymentOrder> createPaymentOrder(PaymentOrder value) {
        return new JAXBElement<PaymentOrder>(_PaymentOrder_QNAME, PaymentOrder.class, null, value);
    }

}
