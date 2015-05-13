
package rs.ac.uns.ftn.xws.bo.mt910;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import rs.ac.uns.ftn.xws.bo.mp.BankDetails;
import rs.ac.uns.ftn.xws.bo.mp.CurrencyDetails;
import rs.ac.uns.ftn.xws.bo.mp.Message;


/**
 * <p>Java class for Mt910 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Mt910">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ftn.uns.ac.rs/xws/bo/mp}Message">
 *       &lt;sequence>
 *         &lt;element name="creditorBankDetails" type="{http://www.ftn.uns.ac.rs/xws/bo/mp}BankDetails"/>
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
 *         &lt;element name="currencyDetails" type="{http://www.ftn.uns.ac.rs/xws/bo/mp}CurrencyDetails"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Mt910", propOrder = {
    "creditorBankDetails",
    "paymentOrderId",
    "amount",
    "currencyDetails"
})
public class Mt910
    extends Message
{

    @XmlElement(required = true)
    protected BankDetails creditorBankDetails;
    @XmlElement(required = true)
    protected String paymentOrderId;
    @XmlElement(required = true)
    protected BigDecimal amount;
    @XmlElement(required = true)
    protected CurrencyDetails currencyDetails;

    /**
     * Gets the value of the creditorBankDetails property.
     * 
     * @return
     *     possible object is
     *     {@link BankDetails }
     *     
     */
    public BankDetails getCreditorBankDetails() {
        return creditorBankDetails;
    }

    /**
     * Sets the value of the creditorBankDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link BankDetails }
     *     
     */
    public void setCreditorBankDetails(BankDetails value) {
        this.creditorBankDetails = value;
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
     * Gets the value of the currencyDetails property.
     * 
     * @return
     *     possible object is
     *     {@link CurrencyDetails }
     *     
     */
    public CurrencyDetails getCurrencyDetails() {
        return currencyDetails;
    }

    /**
     * Sets the value of the currencyDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link CurrencyDetails }
     *     
     */
    public void setCurrencyDetails(CurrencyDetails value) {
        this.currencyDetails = value;
    }

}
