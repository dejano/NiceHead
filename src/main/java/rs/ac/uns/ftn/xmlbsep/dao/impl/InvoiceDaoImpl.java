package rs.ac.uns.ftn.xmlbsep.dao.impl;

import rs.ac.uns.ftn.xmlbsep.beans.jaxb.generated.invoice.Invoice;
import rs.ac.uns.ftn.xmlbsep.beans.jaxb.generated.invoice.Item;
import rs.ac.uns.ftn.xmlbsep.dao.InvoiceDao;
import rs.ac.uns.ftn.xmlbsep.dao.InvoiceDaoLocal;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Stateless()
@Local(InvoiceDaoLocal.class)
@Remote(InvoiceDao.class)
public class InvoiceDaoImpl extends GenericDao<Invoice, Long> implements InvoiceDaoLocal {

    public static final String contextPath = "rs.ac.uns.ftn.xmlbsep.beans.jaxb.generated.invoice";

    public static final String schemaName = "invoice";

    private static final String XBASE_NAMESPACE_QUERY = "declare default element namespace \"http://www.ftn.uns.ac.rs/xmlbsep/company/invoice\";";

    private static final String queryFindPib = "//invoice [//supplier/pib = '%s']";
    private static final String QUERY_FIND_INVOICE_BY_SUPPLIER_PIB_AND_ID = "//invoice [//supplier/pib = '%s' and //invoice/@id = '%s']";

    public InvoiceDaoImpl() {
        super(contextPath, schemaName);
    }

    public List<Invoice> findAllWherePartnersId(String partnerId) throws IOException, JAXBException {
        InputStream isPartner = findBy(String.format(XBASE_NAMESPACE_QUERY + queryFindPib, partnerId), true);
        List<Invoice> invoices = new ArrayList<Invoice>();
        parseMultipleResults(invoices, isPartner);
        return invoices;
    }

    public Invoice findInvoiceBy(String partnerId, String invoiceId) throws IOException, JAXBException {
        InputStream inputStream = findBy(String.format(XBASE_NAMESPACE_QUERY + QUERY_FIND_INVOICE_BY_SUPPLIER_PIB_AND_ID, partnerId, invoiceId), true);
        return parseResult(inputStream);
//        try {
//            return JAXB.unmarshal(inputStream, Invoice.class);
//        } catch(Exception e) {
//            return null;
//        }
    }

    public List<Item> getItems(String partnerId, String invoiceId) throws IOException, JAXBException {
        Invoice invoice = findInvoiceBy(partnerId, invoiceId);
        return invoice == null ? null : invoice.getItem();
    }

    public int createItem(String partnerId, Long invoiceId, Item item) throws IOException, JAXBException {
        Invoice invoice = findInvoiceBy(partnerId, String.valueOf(invoiceId));

        if (invoice == null) {
            throw new IllegalArgumentException("Invoice with provided id does not exist.");
        }

        int itemId = Integer.parseInt(String.valueOf(em.getIdentity()));
        item.setId(itemId);
        invoice.getItem().add(item);

        merge(invoice, invoiceId);
        return itemId;
    }

    public Item getItem(String partnerId, Long invoiceId, Long itemId) throws IOException, JAXBException {
        Invoice invoice = findInvoiceBy(partnerId, String.valueOf(invoiceId));

        if (invoice != null) {
            for (Item item : invoice.getItem())
                if (item.getId() == itemId)
                    return item;
        }
        return null;
    }

    public void removeItem(String partnerId, Long invoiceId, Long itemId) throws IOException, JAXBException, IllegalArgumentException {
        Invoice invoice = findInvoiceBy(partnerId, String.valueOf(invoiceId));

        if (invoice == null) {
            throw new IllegalArgumentException("Invoice with provided id does not exist.");
        }
        boolean foundItem = false;
        for (Iterator<Item> iter = invoice.getItem().listIterator(); iter.hasNext(); ) {
            Item item = iter.next();
            if (item.getId() == itemId) {
                iter.remove();
                foundItem = true;
                break;
            }
        }

        if (!foundItem) {
            throw new IllegalArgumentException("Item with provided id does not exist.");
        }

        merge(invoice, invoiceId);
    }

    public void updateItem(String partnerId, long invoiceId, Item newItem) throws IOException, JAXBException {
        Invoice invoice = findInvoiceBy(partnerId, String.valueOf(invoiceId));

        if (invoice == null) {
            throw new IllegalArgumentException("Invoice with provided id does not exist.");
        }
        boolean foundItem = false;
        for (Iterator<Item> iter = invoice.getItem().listIterator(); iter.hasNext(); ) {
            Item item = iter.next();
            if (item.getId() == newItem.getId()) {
                iter.remove();
                foundItem = true;
                break;
            }
        }

        if (!foundItem) {
            throw new IllegalArgumentException("Item with provided id does not exist.");
        }

        invoice.getItem().add(newItem);
        merge(invoice, invoiceId);
    }
}
