//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.05.29 at 01:49:27 PM CEST 
//


package rs.ac.uns.ftn.xws.domain.pos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import rs.ac.uns.ftn.xws.generated.po.PaymentOrder;


/**
 * <p>Java class for PaymentOrders complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PaymentOrders">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.ftn.uns.ac.rs/xws/xsd/paymentOrder}paymentOrder"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PaymentOrders", propOrder = {
    "paymentOrder"
})
public class PaymentOrders {

    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/xws/xsd/paymentOrder", required = true)
    protected PaymentOrder paymentOrder;

    /**
     * Gets the value of the paymentOrder property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentOrder }
     *     
     */
    public PaymentOrder getPaymentOrder() {
        return paymentOrder;
    }

    /**
     * Sets the value of the paymentOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentOrder }
     *     
     */
    public void setPaymentOrder(PaymentOrder value) {
        this.paymentOrder = value;
    }

}
