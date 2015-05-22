//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.05.22 at 03:05:24 PM CEST 
//


package rs.ac.uns.ftn.xws.orders;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the rs.ac.uns.ftn.xws.orders package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Orders_QNAME = new QName("http://www.ftn.uns.ac.rs/xws/xsd/orders", "orders");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: rs.ac.uns.ftn.xws.orders
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link OrdersData }
     * 
     */
    public OrdersData createOrdersData() {
        return new OrdersData();
    }

    /**
     * Create an instance of {@link OrdersData.Orders }
     * 
     */
    public OrdersData.Orders createOrdersDataOrders() {
        return new OrdersData.Orders();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrdersData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ftn.uns.ac.rs/xws/xsd/orders", name = "orders")
    public JAXBElement<OrdersData> createOrders(OrdersData value) {
        return new JAXBElement<OrdersData>(_Orders_QNAME, OrdersData.class, null, value);
    }

}
