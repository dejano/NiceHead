//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.05.24 at 06:58:04 PM CEST 
//


package rs.ac.uns.ftn.xws.domain;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Bank complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Bank">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="swiftCode" type="{http://www.ftn.uns.ac.rs/xws/xsd/common}SwiftCode"/>
 *         &lt;element name="bankClearingAccountNumber" type="{http://www.ftn.uns.ac.rs/xws/xsd/common}AccountNumber"/>
 *         &lt;element name="wsUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="balance">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *               &lt;minInclusive value="0"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Bank", propOrder = {
    "swiftCode",
    "bankClearingAccountNumber",
    "wsUrl",
    "balance"
})
public class Bank {

    @XmlElement(required = true)
    protected String swiftCode;
    @XmlElement(required = true)
    protected String bankClearingAccountNumber;
    @XmlElement(required = true)
    protected String wsUrl;
    @XmlElement(required = true)
    protected BigDecimal balance;

    /**
     * Gets the value of the swiftCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSwiftCode() {
        return swiftCode;
    }

    /**
     * Sets the value of the swiftCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSwiftCode(String value) {
        this.swiftCode = value;
    }

    /**
     * Gets the value of the bankClearingAccountNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankClearingAccountNumber() {
        return bankClearingAccountNumber;
    }

    /**
     * Sets the value of the bankClearingAccountNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankClearingAccountNumber(String value) {
        this.bankClearingAccountNumber = value;
    }

    /**
     * Gets the value of the wsUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWsUrl() {
        return wsUrl;
    }

    /**
     * Sets the value of the wsUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWsUrl(String value) {
        this.wsUrl = value;
    }

    /**
     * Gets the value of the balance property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * Sets the value of the balance property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBalance(BigDecimal value) {
        this.balance = value;
    }

}