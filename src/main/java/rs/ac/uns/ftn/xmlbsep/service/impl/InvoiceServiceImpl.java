package rs.ac.uns.ftn.xmlbsep.service.impl;

import org.xml.sax.SAXException;
import rs.ac.uns.ftn.xmlbsep.beans.jaxb.generated.Invoice;
import rs.ac.uns.ftn.xmlbsep.exception.InvalidXMLException;
import rs.ac.uns.ftn.xmlbsep.service.InvoiceService;
import rs.ac.uns.ftn.xmlbsep.service.InvoiceServiceLocal;
import rs.ac.uns.ftn.xmlbsep.service.RESTUtil;
import rs.ac.uns.ftn.xmlbsep.service.XBaseREST;
import rs.ac.uns.ftn.xmlbsep.util.XMLUtil;
import rs.ac.uns.ftn.xmlbsep.validation.ValidXMLSchemaInterceptor;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.validation.constraints.NotNull;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;


@Stateless()
@Local(InvoiceServiceLocal.class)
@Remote(InvoiceService.class)
//@Interceptors(ValidXMLSchemaInterceptor.class)
public class InvoiceServiceImpl implements InvoiceServiceLocal {

    @EJB
    private XBaseREST xBaseREST;

    public Invoice getOne(Long id) {
        return null;
    }

    public List<Invoice> findAll() {
        return null;
    }


    public Invoice save(Invoice invoice) throws InvalidXMLException {
        System.out.println("InvoiceServiceImpl.save");
//        throw new InvalidXMLException("test");
//        try {
//            XMLUtil.validate(invoice, "/xsd/invoice.xsd");
////            xBaseREST.insert("asd", "asda", null);
//            RESTUtil.createSchema("invoice");
//            RESTUtil.createResource("invoice", "test1.xml", XMLUtil.createInputStreamFromJaxObject(invoice));
//        } catch (Exception e) {
//            throw new InvalidXMLException(e.getMessage());
//        }
        return invoice;
    }

    public void remove(Long id) throws IllegalArgumentException {

    }
}
