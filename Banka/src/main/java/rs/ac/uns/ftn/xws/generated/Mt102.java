
package rs.ac.uns.ftn.xws.generated;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Mt102 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Mt102">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ftn.uns.ac.rs/xws/xsd/common}Message">
 *       &lt;sequence>
 *         &lt;element name="debtorBankDetails" type="{http://www.ftn.uns.ac.rs/xws/xsd/common}BankDetails"/>
 *         &lt;element name="creditorBankDetails" type="{http://www.ftn.uns.ac.rs/xws/xsd/common}BankDetails" form="qualified"/>
 *         &lt;element name="totalAmount">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *               &lt;totalDigits value="17"/>
 *               &lt;fractionDigits value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="currencyDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="currencyCode" type="{http://www.ftn.uns.ac.rs/xws/xsd/common}CurrencyCode"/>
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="payments">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="payments" type="{http://www.ftn.uns.ac.rs/xws/xsd/mt102}Payment" maxOccurs="unbounded" form="qualified"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Mt102", namespace = "http://www.ftn.uns.ac.rs/xws/xsd/mt102", propOrder = {
    "debtorBankDetails",
    "creditorBankDetails",
    "totalAmount",
    "currencyDate",
    "currencyCode",
    "date",
    "payments"
})
public class Mt102
    extends Message
{

    @XmlElement(required = true)
    protected BankDetails debtorBankDetails;
    @XmlElement(required = true)
    protected BankDetails creditorBankDetails;
    @XmlElement(required = true)
    protected BigDecimal totalAmount;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar currencyDate;
    @XmlElement(required = true)
    protected String currencyCode;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar date;
    @XmlElement(required = true)
    protected Mt102 .Payments payments;

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
     * Gets the value of the totalAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * Sets the value of the totalAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalAmount(BigDecimal value) {
        this.totalAmount = value;
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

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }

    /**
     * Gets the value of the payments property.
     * 
     * @return
     *     possible object is
     *     {@link Mt102 .Payments }
     *     
     */
    public Mt102 .Payments getPayments() {
        return payments;
    }

    /**
     * Sets the value of the payments property.
     * 
     * @param value
     *     allowed object is
     *     {@link Mt102 .Payments }
     *     
     */
    public void setPayments(Mt102 .Payments value) {
        this.payments = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="payments" type="{http://www.ftn.uns.ac.rs/xws/xsd/mt102}Payment" maxOccurs="unbounded" form="qualified"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "payments"
    })
    public static class Payments {

        @XmlElement(namespace = "http://www.ftn.uns.ac.rs/xws/xsd/mt102", required = true)
        protected List<Payment> payments;

        /**
         * Gets the value of the payments property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the payments property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPayments().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Payment }
         * 
         * 
         */
        public List<Payment> getPayments() {
            if (payments == null) {
                payments = new ArrayList<Payment>();
            }
            return this.payments;
        }

    }

}
