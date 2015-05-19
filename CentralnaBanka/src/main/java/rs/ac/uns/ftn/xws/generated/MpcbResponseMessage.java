
package rs.ac.uns.ftn.xws.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MpcbResponseMessage complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MpcbResponseMessage">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mt900" type="{http://www.ftn.uns.ac.rs/xws/xsd/mt900}Mt900"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MpcbResponseMessage", propOrder = {
    "mt900"
})
public class MpcbResponseMessage {

    @XmlElement(required = true)
    protected Mt900 mt900;

    /**
     * Gets the value of the mt900 property.
     * 
     * @return
     *     possible object is
     *     {@link Mt900 }
     *     
     */
    public Mt900 getMt900() {
        return mt900;
    }

    /**
     * Sets the value of the mt900 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Mt900 }
     *     
     */
    public void setMt900(Mt900 value) {
        this.mt900 = value;
    }

}
