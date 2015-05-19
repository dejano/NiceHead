
package rs.ac.uns.ftn.xws.generated;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Mt900 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Mt900">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ftn.uns.ac.rs/xws/xsd/common}Message">
 *       &lt;sequence>
 *         &lt;element name="debtorBankDetails" type="{http://www.ftn.uns.ac.rs/xws/xsd/common}BankDetails"/>
 *         &lt;element name="paymentOrderId">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;length value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="amount">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *               &lt;totalDigits value="15"/>
 *               &lt;fractionDigits value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="currencyDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="currencyCode" type="{http://www.ftn.uns.ac.rs/xws/xsd/common}CurrencyCode"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Mt900", namespace = "http://www.ftn.uns.ac.rs/xws/xsd/mt900", propOrder = {
    "debtorBankDetails",
    "paymentOrderId",
    "amount",
    "currencyDate",
    "currencyCode"
})
public class Mt900
    extends Message
{

    @XmlElement(required = true)
    protected BankDetails debtorBankDetails;
    @XmlElement(required = true)
    protected String paymentOrderId;
    @XmlElement(required = true)
    protected BigDecimal amount;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar currencyDate;
    @XmlElement(required = true)
    protected String currencyCode;

    /**
     * Gets the value of the debtorBankDetails property.
     * 
     * @return
     *     possible object is
     *     {@link BankDetails }
     *     
     */
    public BankDetails getDebtorBankDetails() {
        return debtorBankDetails;
    }

    /**
     * Sets the value of the debtorBankDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link BankDetails }
     *     
     */
    public void setDebtorBankDetails(BankDetails value) {
        this.debtorBankDetails = value;
    }

    /**
     * Gets the value of the paymentOrderId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentOrderId() {
        return paymentOrderId;
    }

    /**
     * Sets the value of the paymentOrderId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentOrderId(String value) {
        this.paymentOrderId = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmount(BigDecimal value) {
        this.amount = value;
    }

    /**
     * Gets the value of the currencyDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCurrencyDate() {
        return currencyDate;
    }

    /**
     * Sets the value of the currencyDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCurrencyDate(XMLGregorianCalendar value) {
        this.currencyDate = value;
    }

    /**
     * Gets the value of the currencyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * Sets the value of the currencyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyCode(String value) {
        this.currencyCode = value;
    }

}
