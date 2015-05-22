package rs.ac.uns.ftn.xws.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for BankDetails complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="BankDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="swiftCode" type="{http://www.ftn.uns.ac.rs/xws/xsd/common}SwiftCode"/>
 *         &lt;element name="bankClearingAccountNumber" type="{http://www.ftn.uns.ac.rs/xws/xsd/common}AccountNumber"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BankDetails", propOrder = { "swiftCode",
		"bankClearingAccountNumber" })
public class BankDetails {

	@XmlElement(required = true)
	protected String swiftCode;
	@XmlElement(required = true)
	protected String bankClearingAccountNumber;

	/**
	 * Gets the value of the swiftCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSwiftCode() {
		return swiftCode;
	}

	/**
	 * Sets the value of the swiftCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSwiftCode(String value) {
		this.swiftCode = value;
	}

	/**
	 * Gets the value of the bankClearingAccountNumber property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBankClearingAccountNumber() {
		return bankClearingAccountNumber;
	}

	/**
	 * Sets the value of the bankClearingAccountNumber property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBankClearingAccountNumber(String value) {
		this.bankClearingAccountNumber = value;
	}

}
