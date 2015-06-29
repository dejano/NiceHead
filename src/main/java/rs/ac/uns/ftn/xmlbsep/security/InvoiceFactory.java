package rs.ac.uns.ftn.xmlbsep.security;

import rs.ac.uns.ftn.xmlbsep.beans.jaxb.generated.invoice.Invoice;
import rs.ac.uns.ftn.xmlbsep.exception.UnauthorizedAccessException;


public interface InvoiceFactory {
    void merge(Invoice invoice, Invoice oldInvoice) throws UnauthorizedAccessException, IllegalStateException;
}
