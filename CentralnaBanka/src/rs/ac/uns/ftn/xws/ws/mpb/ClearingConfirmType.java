
package rs.ac.uns.ftn.xws.ws.mpb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import rs.ac.uns.ftn.xws.bo.mt102.Mt102;
import rs.ac.uns.ftn.xws.bo.mt910.Mt910;


/**
 * <p>Java class for ClearingConfirmType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ClearingConfirmType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mt910" type="{http://www.ftn.uns.ac.rs/xws/bo/mt910}Mt910"/>
 *         &lt;element name="mt102" type="{http://www.ftn.uns.ac.rs/xws/bo/mt102}Mt102"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClearingConfirmType", propOrder = {
    "mt910",
    "mt102"
})
public class ClearingConfirmType {

    @XmlElement(required = true)
    protected Mt910 mt910;
    @XmlElement(required = true)
    protected Mt102 mt102;

    /**
     * Gets the value of the mt910 property.
     * 
     * @return
     *     possible object is
     *     {@link Mt910 }
     *     
     */
    public Mt910 getMt910() {
        return mt910;
    }

    /**
     * Sets the value of the mt910 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Mt910 }
     *     
     */
    public void setMt910(Mt910 value) {
        this.mt910 = value;
    }

    /**
     * Gets the value of the mt102 property.
     * 
     * @return
     *     possible object is
     *     {@link Mt102 }
     *     
     */
    public Mt102 getMt102() {
        return mt102;
    }

    /**
     * Sets the value of the mt102 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Mt102 }
     *     
     */
    public void setMt102(Mt102 value) {
        this.mt102 = value;
    }

}
