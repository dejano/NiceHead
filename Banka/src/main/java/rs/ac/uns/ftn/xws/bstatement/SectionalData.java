//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.05.22 at 03:05:24 PM CEST 
//


package rs.ac.uns.ftn.xws.bstatement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element ref="{http://www.ftn.uns.ac.rs/xws/xsd/bstatement}sectionalHeader"/>
 *         &lt;element ref="{http://www.ftn.uns.ac.rs/xws/xsd/bstatement}assetsData"/>
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
    "sectionalHeader",
    "assetsData"
})
@XmlRootElement(name = "sectionalData")
public class SectionalData {

    @XmlElement(required = true)
    protected SectionalHeader sectionalHeader;
    @XmlElement(required = true)
    protected AssetsData assetsData;

    /**
     * Gets the value of the sectionalHeader property.
     * 
     * @return
     *     possible object is
     *     {@link SectionalHeader }
     *     
     */
    public SectionalHeader getSectionalHeader() {
        return sectionalHeader;
    }

    /**
     * Sets the value of the sectionalHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link SectionalHeader }
     *     
     */
    public void setSectionalHeader(SectionalHeader value) {
        this.sectionalHeader = value;
    }

    /**
     * Gets the value of the assetsData property.
     * 
     * @return
     *     possible object is
     *     {@link AssetsData }
     *     
     */
    public AssetsData getAssetsData() {
        return assetsData;
    }

    /**
     * Sets the value of the assetsData property.
     * 
     * @param value
     *     allowed object is
     *     {@link AssetsData }
     *     
     */
    public void setAssetsData(AssetsData value) {
        this.assetsData = value;
    }

}
