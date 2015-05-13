
package rs.ac.uns.ftn.xws.bo.mt103;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import rs.ac.uns.ftn.xws.bo.mp.AccountDetails;
import rs.ac.uns.ftn.xws.bo.mp.BankDetails;
import rs.ac.uns.ftn.xws.bo.mp.CurrencyDetails;
import rs.ac.uns.ftn.xws.bo.mp.Message;


/**
 * <p>Java class for Mt103 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Mt103">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ftn.uns.ac.rs/xws/bo/mp}Message">
 *       &lt;sequence>
 *         &lt;element name="podaci_o_banci_duznika" type="{http://www.ftn.uns.ac.rs/xws/bo/mp}BankDetails"/>
 *         &lt;element name="debtorBankDetails" type="{http://www.ftn.uns.ac.rs/xws/bo/mp}BankDetails" form="qualified"/>
 *         &lt;element name="debtor">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="255"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="paymentPurpose">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="255"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="creditor">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="255"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="orderDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="debtorAccountDetails" type="{http://www.ftn.uns.ac.rs/xws/bo/mp}AccountDetails"/>
 *         &lt;element name="creditorAccountDetails" type="{http://www.ftn.uns.ac.rs/xws/bo/mp}AccountDetails"/>
 *         &lt;element name="amount">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *               &lt;totalDigits value="17"/>
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
@XmlType(name = "Mt103", propOrder = {
    "podaciOBanciDuznika",
    "debtorBankDetails",
    "debtor",
    "paymentPurpose",
    "creditor",
    "orderDate",
    "debtorAccountDetails",
    "creditorAccountDetails",
    "amount",
    "currencyDetails"
})
public class Mt103
    extends Message
{

    @XmlElement(name = "podaci_o_banci_duznika", required = true)
    protected BankDetails podaciOBanciDuznika;
    @XmlElement(required = true)
    protected BankDetails debtorBankDetails;
    @XmlElement(required = true)
    protected String debtor;
    @XmlElement(required = true)
    protected String paymentPurpose;
    @XmlElement(required = true)
    protected String creditor;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar orderDate;
    @XmlElement(required = true)
    protected AccountDetails debtorAccountDetails;
    @XmlElement(required = true)
    protected AccountDetails creditorAccountDetails;
    @XmlElement(required = true)
    protected BigDecimal amount;
    @XmlElement(required = true)
    protected CurrencyDetails currencyDetails;

    /**
     * Gets the value of the podaciOBanciDuznika property.
     * 
     * @return
     *     possible object is
     *     {@link BankDetails }
     *     
     */
    public BankDetails getPodaciOBanciDuznika() {
        return podaciOBanciDuznika;
    }

    /**
     * Sets the value of the podaciOBanciDuznika property.
     * 
     * @param value
     *     allowed object is
     *     {@link BankDetails }
     *     
     */
    public void setPodaciOBanciDuznika(BankDetails value) {
        this.podaciOBanciDuznika = value;
    }

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
     * Gets the value of the creditor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditor() {
        return creditor;
    }

    /**
     * Sets the value of the creditor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditor(String value) {
        this.creditor = value;
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
     * Gets the value of the creditorAccountDetails property.
     * 
     * @return
     *     possible object is
     *     {@link AccountDetails }
     *     
     */
    public AccountDetails getCreditorAccountDetails() {
        return creditorAccountDetails;
    }

    /**
     * Sets the value of the creditorAccountDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountDetails }
     *     
     */
    public void setCreditorAccountDetails(AccountDetails value) {
        this.creditorAccountDetails = value;
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
