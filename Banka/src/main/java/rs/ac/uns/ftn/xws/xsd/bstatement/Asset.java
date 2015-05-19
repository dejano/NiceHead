
package rs.ac.uns.ftn.xws.xsd.bstatement;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import rs.ac.uns.ftn.xws.generated.AccountDetails;


/**
 * <p>Java class for Asset complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Asset">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="debtor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="paymentPurpose" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="acceptor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orderDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="valueDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="debtorAccountDetails" type="{http://www.ftn.uns.ac.rs/xws/xsd/common}AccountDetails"/>
 *         &lt;element name="acceptorAccountDetails" type="{http://www.ftn.uns.ac.rs/xws/xsd/common}AccountDetails"/>
 *         &lt;element name="value" type="{http://www.ftn.uns.ac.rs/xws/xsd/common}Balance"/>
 *         &lt;element name="direction" type="{http://www.ftn.uns.ac.rs/xws/xsd/common}Character"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Asset", propOrder = {
    "debtor",
    "paymentPurpose",
    "acceptor",
    "orderDate",
    "valueDate",
    "debtorAccountDetails",
    "acceptorAccountDetails",
    "value",
    "direction"
})
public class Asset {

    @XmlElement(required = true)
    protected String debtor;
    @XmlElement(required = true)
    protected String paymentPurpose;
    @XmlElement(required = true)
    protected String acceptor;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar orderDate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar valueDate;
    @XmlElement(required = true)
    protected AccountDetails debtorAccountDetails;
    @XmlElement(required = true)
    protected AccountDetails acceptorAccountDetails;
    @XmlElement(required = true)
    protected BigDecimal value;
    @XmlElement(required = true)
    protected String direction;

    /**
     * Gets the value of the debtor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDebtor() {
        return debtor;
    }

    /**
     * Sets the value of the debtor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDebtor(String value) {
        this.debtor = value;
    }

    /**
     * Gets the value of the paymentPurpose property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentPurpose() {
        return paymentPurpose;
    }

    /**
     * Sets the value of the paymentPurpose property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentPurpose(String value) {
        this.paymentPurpose = value;
    }

    /**
     * Gets the value of the acceptor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcceptor() {
        return acceptor;
    }

    /**
     * Sets the value of the acceptor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcceptor(String value) {
        this.acceptor = value;
    }

    /**
     * Gets the value of the orderDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOrderDate() {
        return orderDate;
    }

    /**
     * Sets the value of the orderDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOrderDate(XMLGregorianCalendar value) {
        this.orderDate = value;
    }

    /**
     * Gets the value of the valueDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getValueDate() {
        return valueDate;
    }

    /**
     * Sets the value of the valueDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setValueDate(XMLGregorianCalendar value) {
        this.valueDate = value;
    }

    /**
     * Gets the value of the debtorAccountDetails property.
     * 
     * @return
     *     possible object is
     *     {@link AccountDetails }
     *     
     */
    public AccountDetails getDebtorAccountDetails() {
        return debtorAccountDetails;
    }

    /**
     * Sets the value of the debtorAccountDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountDetails }
     *     
     */
    public void setDebtorAccountDetails(AccountDetails value) {
        this.debtorAccountDetails = value;
    }

    /**
     * Gets the value of the acceptorAccountDetails property.
     * 
     * @return
     *     possible object is
     *     {@link AccountDetails }
     *     
     */
    public AccountDetails getAcceptorAccountDetails() {
        return acceptorAccountDetails;
    }

    /**
     * Sets the value of the acceptorAccountDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountDetails }
     *     
     */
    public void setAcceptorAccountDetails(AccountDetails value) {
        this.acceptorAccountDetails = value;
    }

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setValue(BigDecimal value) {
        this.value = value;
    }

    /**
     * Gets the value of the direction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirection() {
        return direction;
    }

    /**
     * Sets the value of the direction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirection(String value) {
        this.direction = value;
    }

}
