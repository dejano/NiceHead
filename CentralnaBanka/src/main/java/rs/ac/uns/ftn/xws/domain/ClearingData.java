//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.05.29 at 05:41:31 PM CEST 
//


package rs.ac.uns.ftn.xws.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import rs.ac.uns.ftn.xws.generated.mp.Mt102;


/**
 * <p>Java class for ClearingData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ClearingData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.ftn.uns.ac.rs/xws/xsd/mp}mt102" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClearingData", propOrder = {
    "mt102"
})
public class ClearingData {

    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/xws/xsd/mp")
    protected List<Mt102> mt102;

    /**
     * Gets the value of the mt102 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mt102 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMt102().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Mt102 }
     * 
     * 
     */
    public List<Mt102> getMt102() {
        if (mt102 == null) {
            mt102 = new ArrayList<Mt102>();
        }
        return this.mt102;
    }

}
