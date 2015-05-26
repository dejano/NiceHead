
package rs.ac.uns.ftn.xmlbsep.beans.jaxb.generated;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the rs.ac.uns.ftn.xmlbsep.gen.invoice package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: rs.ac.uns.ftn.xmlbsep.gen.invoice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Invoice }
     * 
     */
    public Invoice createInvoice() {
        return new Invoice();
    }

    /**
     * Create an instance of {@link Item }
     * 
     */
    public Item createItem() {
        return new Item();
    }

    /**
     * Create an instance of {@link Invoice.InvoiceHeader }
     * 
     */
    public Invoice.InvoiceHeader createInvoiceInvoiceHeader() {
        return new Invoice.InvoiceHeader();
    }

    /**
     * Create an instance of {@link Item.Discount }
     * 
     */
    public Item.Discount createItemDiscount() {
        return new Item.Discount();
    }

    /**
     * Create an instance of {@link Item.Unit }
     * 
     */
    public Item.Unit createItemUnit() {
        return new Item.Unit();
    }

    /**
     * Create an instance of {@link TsupplierBuyer }
     * 
     */
    public TsupplierBuyer createTsupplierBuyer() {
        return new TsupplierBuyer();
    }

    /**
     * Create an instance of {@link Tcurrency }
     * 
     */
    public Tcurrency createTcurrency() {
        return new Tcurrency();
    }

    /**
     * Create an instance of {@link Tbill }
     * 
     */
    public Tbill createTbill() {
        return new Tbill();
    }

    /**
     * Create an instance of {@link Invoice.InvoiceHeader.Price }
     * 
     */
    public Invoice.InvoiceHeader.Price createInvoiceInvoiceHeaderPrice() {
        return new Invoice.InvoiceHeader.Price();
    }

    /**
     * Create an instance of {@link Invoice.InvoiceHeader.Payment }
     * 
     */
    public Invoice.InvoiceHeader.Payment createInvoiceInvoiceHeaderPayment() {
        return new Invoice.InvoiceHeader.Payment();
    }

}
