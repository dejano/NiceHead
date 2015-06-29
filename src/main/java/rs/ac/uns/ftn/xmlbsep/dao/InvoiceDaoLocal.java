package rs.ac.uns.ftn.xmlbsep.dao;

import rs.ac.uns.ftn.xmlbsep.beans.jaxb.generated.invoice.Invoice;
import rs.ac.uns.ftn.xmlbsep.beans.jaxb.generated.invoice.Item;
import rs.ac.uns.ftn.xmlbsep.security.InvoiceState;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface InvoiceDaoLocal extends GenericDaoLocal<Invoice, Long> {

    List<Invoice> findAllWherePartnersId(String id, InvoiceState status) throws IOException, JAXBException;
    List<Invoice> findAllWherePartnersId(String id) throws IOException, JAXBException;

    List<Invoice> findSentWherePartnersId(String partnerId) throws IOException, JAXBException;
    List<Invoice> findReceivedWherePartnersId() throws IOException, JAXBException;

    public Invoice findInvoiceBy(String partnerId, String invoiceId) throws IOException, JAXBException;
    List<Item> getItems(String partnerId, String invoiceId) throws IOException, JAXBException;
    int createItem(String partnerId, Long invoiceId, Item item) throws IOException, JAXBException;
    Item getItem(String partnerId, Long invoiceId, Long itemId) throws IOException, JAXBException;
    void removeItem(String partnerId, Long invoiceId, Long itemId) throws IOException, JAXBException, IllegalArgumentException;

    void updateItem(String partnerId, long invoiceId, Item newItem) throws IOException, JAXBException;
}
