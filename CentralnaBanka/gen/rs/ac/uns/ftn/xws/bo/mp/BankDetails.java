
package rs.ac.uns.ftn.xws.bo.mp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BankDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BankDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="swiftCode" type="{http://www.ftn.uns.ac.rs/xws/bo/mp}SwiftCode"/>
 *         &lt;element name="bankClearingAccount" type="{http://www.ftn.uns.ac.rs/xws/bo/mp}BankClearingAccount"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BankDetails", propOrder = {
    "swiftCode",
    "bankClearingAccount"
})
public class BankDetails {

    @XmlElement(required = true)
    protected String swiftCode;
    @XmlElement(required = true)
    protected String bankClearingAccount;

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
     * Gets the value of the bankClearingAccount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankClearingAccount() {
        return bankClearingAccount;
    }

    /**
     * Sets the value of the bankClearingAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankClearingAccount(String value) {
        this.bankClearingAccount = value;
    }

}
