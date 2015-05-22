
package rs.ac.uns.ftn.xws.bstatement;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for SectionalHeader complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SectionalHeader">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="accountNumber" type="{http://www.ftn.uns.ac.rs/xws/xsd/common}AccountNumber"/>
 *         &lt;element name="orderDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="sectionalNumber" type="{http://www.ftn.uns.ac.rs/xws/xsd/common}SectionalNumber"/>
 *         &lt;element name="previousState" type="{http://www.ftn.uns.ac.rs/xws/xsd/common}Balance"/>
 *         &lt;element name="positiveChangesCount">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *               &lt;minInclusive value="0"/>
 *               &lt;maxInclusive value="99"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="postiveOverallAmount" type="{http://www.ftn.uns.ac.rs/xws/xsd/common}Balance"/>
 *         &lt;element name="negativeChangesCount">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *               &lt;minInclusive value="0"/>
 *               &lt;maxInclusive value="99"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="negativeOverallAmount" type="{http://www.ftn.uns.ac.rs/xws/xsd/common}Balance"/>
 *         &lt;element name="newState" type="{http://www.ftn.uns.ac.rs/xws/xsd/common}Balance"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SectionalHeader", propOrder = {
    "accountNumber",
    "orderDate",
    "sectionalNumber",
    "previousState",
    "positiveChangesCount",
    "postiveOverallAmount",
    "negativeChangesCount",
    "negativeOverallAmount",
    "newState"
})
public class SectionalHeader {

    @XmlElement(required = true)
    protected String accountNumber;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar orderDate;
    protected int sectionalNumber;
    @XmlElement(required = true)
    protected BigDecimal previousState;
    protected int positiveChangesCount;
    @XmlElement(required = true)
    protected BigDecimal postiveOverallAmount;
    protected int negativeChangesCount;
    @XmlElement(required = true)
    protected BigDecimal negativeOverallAmount;
    @XmlElement(required = true)
    protected BigDecimal newState;

    /**
     * Gets the value of the accountNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets the value of the accountNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountNumber(String value) {
        this.accountNumber = value;
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
     * Gets the value of the sectionalNumber property.
     * 
     */
    public int getSectionalNumber() {
        return sectionalNumber;
    }

    /**
     * Sets the value of the sectionalNumber property.
     * 
     */
    public void setSectionalNumber(int value) {
        this.sectionalNumber = value;
    }

    /**
     * Gets the value of the previousState property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPreviousState() {
        return previousState;
    }

    /**
     * Sets the value of the previousState property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPreviousState(BigDecimal value) {
        this.previousState = value;
    }

    /**
     * Gets the value of the positiveChangesCount property.
     * 
     */
    public int getPositiveChangesCount() {
        return positiveChangesCount;
    }

    /**
     * Sets the value of the positiveChangesCount property.
     * 
     */
    public void setPositiveChangesCount(int value) {
        this.positiveChangesCount = value;
    }

    /**
     * Gets the value of the postiveOverallAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPostiveOverallAmount() {
        return postiveOverallAmount;
    }

    /**
     * Sets the value of the postiveOverallAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPostiveOverallAmount(BigDecimal value) {
        this.postiveOverallAmount = value;
    }

    /**
     * Gets the value of the negativeChangesCount property.
     * 
     */
    public int getNegativeChangesCount() {
        return negativeChangesCount;
    }

    /**
     * Sets the value of the negativeChangesCount property.
     * 
     */
    public void setNegativeChangesCount(int value) {
        this.negativeChangesCount = value;
    }

    /**
     * Gets the value of the negativeOverallAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNegativeOverallAmount() {
        return negativeOverallAmount;
    }

    /**
     * Sets the value of the negativeOverallAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNegativeOverallAmount(BigDecimal value) {
        this.negativeOverallAmount = value;
    }

    /**
     * Gets the value of the newState property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNewState() {
        return newState;
    }

    /**
     * Sets the value of the newState property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNewState(BigDecimal value) {
        this.newState = value;
    }

}
