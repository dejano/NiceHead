
package rs.ac.uns.ftn.xmlbsep.beans.jaxb.generated;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *         &lt;element name="invoice_header">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="supplier" type="{http://www.ftn.uns.ac.rs/xmlbsep/company/invoice}Tsupplier_buyer"/>
 *                   &lt;element name="buyer" type="{http://www.ftn.uns.ac.rs/xmlbsep/company/invoice}Tsupplier_buyer"/>
 *                   &lt;element name="bill" type="{http://www.ftn.uns.ac.rs/xmlbsep/company/invoice}Tbill"/>
 *                   &lt;element name="price">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="services">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                   &lt;totalDigits value="15"/>
 *                                   &lt;fractionDigits value="2"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="goods">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                   &lt;fractionDigits value="2"/>
 *                                   &lt;totalDigits value="15"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="total_taxes">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                   &lt;totalDigits value="15"/>
 *                                   &lt;fractionDigits value="2"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="total_discount">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                   &lt;totalDigits value="15"/>
 *                                   &lt;fractionDigits value="2"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="total">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                   &lt;totalDigits value="15"/>
 *                                   &lt;fractionDigits value="2"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="payment">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="currency" type="{http://www.ftn.uns.ac.rs/xmlbsep/company/invoice}Tcurrency"/>
 *                             &lt;element name="amount">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                   &lt;totalDigits value="15"/>
 *                                   &lt;fractionDigits value="2"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="account_number">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;pattern value="\d{3}-\d{13}-\d{2}"/>
 *                                   &lt;length value="20"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="id" use="required">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                       &lt;minLength value="1"/>
 *                       &lt;maxLength value="50"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element ref="{http://www.ftn.uns.ac.rs/xmlbsep/company/invoice}item" maxOccurs="unbounded"/>
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
    "invoiceHeader",
    "item"
})
@XmlRootElement(name = "invoice", namespace = "http://www.ftn.uns.ac.rs/xmlbsep/company/invoice")
public class Invoice {

    @XmlElement(name = "invoice_header", namespace = "http://www.ftn.uns.ac.rs/xmlbsep/company/invoice", required = true)
    protected Invoice.InvoiceHeader invoiceHeader;
    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/xmlbsep/company/invoice", required = true)
    protected List<Item> item;

    /**
     * Gets the value of the invoiceHeader property.
     * 
     * @return
     *     possible object is
     *     {@link Invoice.InvoiceHeader }
     *     
     */
    public Invoice.InvoiceHeader getInvoiceHeader() {
        return invoiceHeader;
    }

    /**
     * Sets the value of the invoiceHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link Invoice.InvoiceHeader }
     *     
     */
    public void setInvoiceHeader(Invoice.InvoiceHeader value) {
        this.invoiceHeader = value;
    }

    /**
     * Gets the value of the item property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the item property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Item }
     * 
     * 
     */
    public List<Item> getItem() {
        if (item == null) {
            item = new ArrayList<Item>();
        }
        return this.item;
    }


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
     *         &lt;element name="supplier" type="{http://www.ftn.uns.ac.rs/xmlbsep/company/invoice}Tsupplier_buyer"/>
     *         &lt;element name="buyer" type="{http://www.ftn.uns.ac.rs/xmlbsep/company/invoice}Tsupplier_buyer"/>
     *         &lt;element name="bill" type="{http://www.ftn.uns.ac.rs/xmlbsep/company/invoice}Tbill"/>
     *         &lt;element name="price">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="services">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                         &lt;totalDigits value="15"/>
     *                         &lt;fractionDigits value="2"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="goods">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                         &lt;fractionDigits value="2"/>
     *                         &lt;totalDigits value="15"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="total_taxes">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                         &lt;totalDigits value="15"/>
     *                         &lt;fractionDigits value="2"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="total_discount">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                         &lt;totalDigits value="15"/>
     *                         &lt;fractionDigits value="2"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="total">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                         &lt;totalDigits value="15"/>
     *                         &lt;fractionDigits value="2"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="payment">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="currency" type="{http://www.ftn.uns.ac.rs/xmlbsep/company/invoice}Tcurrency"/>
     *                   &lt;element name="amount">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                         &lt;totalDigits value="15"/>
     *                         &lt;fractionDigits value="2"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="account_number">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;pattern value="\d{3}-\d{13}-\d{2}"/>
     *                         &lt;length value="20"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *       &lt;attribute name="id" use="required">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *             &lt;minLength value="1"/>
     *             &lt;maxLength value="50"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "supplier",
        "buyer",
        "bill",
        "price",
        "payment"
    })
    public static class InvoiceHeader {

        @XmlElement(namespace = "http://www.ftn.uns.ac.rs/xmlbsep/company/invoice", required = true)
        protected TsupplierBuyer supplier;
        @XmlElement(namespace = "http://www.ftn.uns.ac.rs/xmlbsep/company/invoice", required = true)
        protected TsupplierBuyer buyer;
        @XmlElement(namespace = "http://www.ftn.uns.ac.rs/xmlbsep/company/invoice", required = true)
        protected Tbill bill;
        @XmlElement(namespace = "http://www.ftn.uns.ac.rs/xmlbsep/company/invoice", required = true)
        protected Invoice.InvoiceHeader.Price price;
        @XmlElement(namespace = "http://www.ftn.uns.ac.rs/xmlbsep/company/invoice", required = true)
        protected Invoice.InvoiceHeader.Payment payment;
        @XmlAttribute(name = "id", required = true)
        protected String id;

        /**
         * Gets the value of the supplier property.
         * 
         * @return
         *     possible object is
         *     {@link TsupplierBuyer }
         *     
         */
        public TsupplierBuyer getSupplier() {
            return supplier;
        }

        /**
         * Sets the value of the supplier property.
         * 
         * @param value
         *     allowed object is
         *     {@link TsupplierBuyer }
         *     
         */
        public void setSupplier(TsupplierBuyer value) {
            this.supplier = value;
        }

        /**
         * Gets the value of the buyer property.
         * 
         * @return
         *     possible object is
         *     {@link TsupplierBuyer }
         *     
         */
        public TsupplierBuyer getBuyer() {
            return buyer;
        }

        /**
         * Sets the value of the buyer property.
         * 
         * @param value
         *     allowed object is
         *     {@link TsupplierBuyer }
         *     
         */
        public void setBuyer(TsupplierBuyer value) {
            this.buyer = value;
        }

        /**
         * Gets the value of the bill property.
         * 
         * @return
         *     possible object is
         *     {@link Tbill }
         *     
         */
        public Tbill getBill() {
            return bill;
        }

        /**
         * Sets the value of the bill property.
         * 
         * @param value
         *     allowed object is
         *     {@link Tbill }
         *     
         */
        public void setBill(Tbill value) {
            this.bill = value;
        }

        /**
         * Gets the value of the price property.
         * 
         * @return
         *     possible object is
         *     {@link Invoice.InvoiceHeader.Price }
         *     
         */
        public Invoice.InvoiceHeader.Price getPrice() {
            return price;
        }

        /**
         * Sets the value of the price property.
         * 
         * @param value
         *     allowed object is
         *     {@link Invoice.InvoiceHeader.Price }
         *     
         */
        public void setPrice(Invoice.InvoiceHeader.Price value) {
            this.price = value;
        }

        /**
         * Gets the value of the payment property.
         * 
         * @return
         *     possible object is
         *     {@link Invoice.InvoiceHeader.Payment }
         *     
         */
        public Invoice.InvoiceHeader.Payment getPayment() {
            return payment;
        }

        /**
         * Sets the value of the payment property.
         * 
         * @param value
         *     allowed object is
         *     {@link Invoice.InvoiceHeader.Payment }
         *     
         */
        public void setPayment(Invoice.InvoiceHeader.Payment value) {
            this.payment = value;
        }

        /**
         * Gets the value of the id property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getId() {
            return id;
        }

        /**
         * Sets the value of the id property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setId(String value) {
            this.id = value;
        }


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
         *         &lt;element name="currency" type="{http://www.ftn.uns.ac.rs/xmlbsep/company/invoice}Tcurrency"/>
         *         &lt;element name="amount">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *               &lt;totalDigits value="15"/>
         *               &lt;fractionDigits value="2"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="account_number">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;pattern value="\d{3}-\d{13}-\d{2}"/>
         *               &lt;length value="20"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
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
            "currency",
            "amount",
            "accountNumber"
        })
        public static class Payment {

            @XmlElement(namespace = "http://www.ftn.uns.ac.rs/xmlbsep/company/invoice", required = true)
            protected Tcurrency currency;
            @XmlElement(namespace = "http://www.ftn.uns.ac.rs/xmlbsep/company/invoice", required = true)
            protected BigDecimal amount;
            @XmlElement(name = "account_number", namespace = "http://www.ftn.uns.ac.rs/xmlbsep/company/invoice", required = true)
            protected String accountNumber;

            /**
             * Gets the value of the currency property.
             * 
             * @return
             *     possible object is
             *     {@link Tcurrency }
             *     
             */
            public Tcurrency getCurrency() {
                return currency;
            }

            /**
             * Sets the value of the currency property.
             * 
             * @param value
             *     allowed object is
             *     {@link Tcurrency }
             *     
             */
            public void setCurrency(Tcurrency value) {
                this.currency = value;
            }

            /**
             * Gets the value of the amount property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getAmount() {
                return amount;
            }

            /**
             * Sets the value of the amount property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setAmount(BigDecimal value) {
                this.amount = value;
            }

            /**
             * Gets the value of the accountNumber property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAccountNumber() {
                return accountNumber;
            }

            /**
             * Sets the value of the accountNumber property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAccountNumber(String value) {
                this.accountNumber = value;
            }

        }


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
         *         &lt;element name="services">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *               &lt;totalDigits value="15"/>
         *               &lt;fractionDigits value="2"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="goods">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *               &lt;fractionDigits value="2"/>
         *               &lt;totalDigits value="15"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="total_taxes">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *               &lt;totalDigits value="15"/>
         *               &lt;fractionDigits value="2"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="total_discount">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *               &lt;totalDigits value="15"/>
         *               &lt;fractionDigits value="2"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="total">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *               &lt;totalDigits value="15"/>
         *               &lt;fractionDigits value="2"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
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
            "services",
            "goods",
            "totalTaxes",
            "totalDiscount",
            "total"
        })
        public static class Price {

            @XmlElement(namespace = "http://www.ftn.uns.ac.rs/xmlbsep/company/invoice", required = true)
            protected BigDecimal services;
            @XmlElement(namespace = "http://www.ftn.uns.ac.rs/xmlbsep/company/invoice", required = true)
            protected BigDecimal goods;
            @XmlElement(name = "total_taxes", namespace = "http://www.ftn.uns.ac.rs/xmlbsep/company/invoice", required = true)
            protected BigDecimal totalTaxes;
            @XmlElement(name = "total_discount", namespace = "http://www.ftn.uns.ac.rs/xmlbsep/company/invoice", required = true)
            protected BigDecimal totalDiscount;
            @XmlElement(namespace = "http://www.ftn.uns.ac.rs/xmlbsep/company/invoice", required = true)
            protected BigDecimal total;

            /**
             * Gets the value of the services property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getServices() {
                return services;
            }

            /**
             * Sets the value of the services property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setServices(BigDecimal value) {
                this.services = value;
            }

            /**
             * Gets the value of the goods property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getGoods() {
                return goods;
            }

            /**
             * Sets the value of the goods property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setGoods(BigDecimal value) {
                this.goods = value;
            }

            /**
             * Gets the value of the totalTaxes property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getTotalTaxes() {
                return totalTaxes;
            }

            /**
             * Sets the value of the totalTaxes property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setTotalTaxes(BigDecimal value) {
                this.totalTaxes = value;
            }

            /**
             * Gets the value of the totalDiscount property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getTotalDiscount() {
                return totalDiscount;
            }

            /**
             * Sets the value of the totalDiscount property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setTotalDiscount(BigDecimal value) {
                this.totalDiscount = value;
            }

            /**
             * Gets the value of the total property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getTotal() {
                return total;
            }

            /**
             * Sets the value of the total property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setTotal(BigDecimal value) {
                this.total = value;
            }

        }

    }

}
