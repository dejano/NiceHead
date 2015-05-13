
package rs.ac.uns.ftn.xws.ws.mpb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import rs.ac.uns.ftn.xws.bo.mt103.Mt103;
import rs.ac.uns.ftn.xws.bo.mt910.Mt910;


/**
 * <p>Java class for RtgsConfirmType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RtgsConfirmType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mt910" type="{http://www.ftn.uns.ac.rs/xws/bo/mt910}Mt910"/>
 *         &lt;element name="mt103" type="{http://www.ftn.uns.ac.rs/xws/bo/mt103}Mt103"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RtgsConfirmType", propOrder = {
    "mt910",
    "mt103"
})
public class RtgsConfirmType {

    @XmlElement(required = true)
    protected Mt910 mt910;
    @XmlElement(required = true)
    protected Mt103 mt103;

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
     * Gets the value of the mt103 property.
     * 
     * @return
     *     possible object is
     *     {@link Mt103 }
     *     
     */
    public Mt103 getMt103() {
        return mt103;
    }

    /**
     * Sets the value of the mt103 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Mt103 }
     *     
     */
    public void setMt103(Mt103 value) {
        this.mt103 = value;
    }

}
