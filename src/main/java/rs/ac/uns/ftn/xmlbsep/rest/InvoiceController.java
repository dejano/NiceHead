package rs.ac.uns.ftn.xmlbsep.rest;

import org.xml.sax.SAXException;
import rs.ac.uns.ftn.xmlbsep.beans.jaxb.generated.Invoice;
import rs.ac.uns.ftn.xmlbsep.service.InvoiceServiceLocal;
import rs.ac.uns.ftn.xmlbsep.util.HTTPConnectionInterceptor;
import rs.ac.uns.ftn.xmlbsep.validation.ValidXMLSchema;
import rs.ac.uns.ftn.xmlbsep.validation.ValidXMLSchemaInterceptor;

import javax.ejb.EJB;
import javax.interceptor.Interceptors;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Consumes({"application/xml", "application/json"})
@Produces({ "application/xml" })
@Path("/partneri/{PartnerId}/fakture")
//@Interceptors(HTTPConnectionInterceptor.class)
public class InvoiceController {

    @EJB
    private InvoiceServiceLocal invoiceService;

    @POST
    public Response store(@ValidXMLSchema("/xsd/invoice.xsd") Invoice invoice, @PathParam("partnerId") int partnerId, @Context UriInfo uriInfo) {
        System.out.println("InvoiceController.store");
            try {
                invoiceService.save(invoice);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JAXBException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }
//        throw new InvalidXMLException("test");
//        return Response.status(Response.Status.CREATED).contentLocation(uriInfo.getAbsolutePathBuilder().path(id).build()).build();
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    public List<Invoice> getInvoices(@PathParam("partnerId") int partnerId) {
//        System.out.println(invoiceService.size());
//        return invoiceService.getAll();
//        invoiceService.baseX();
        return null;
    }

    @GET
    @Path("/{invoiceId}")
    public Invoice getInvoice(@PathParam("partnerId") int partnerId, @PathParam("invoiceId") int invoiceId) {
//        System.out.println(invoiceService.size());
//        return invoiceService.get(String.valueOf(invoiceId));
        return null;
    }

    @Path("/{invoiceId}/stavke")
    public InvoiceItemController getInvoiceItem() {
        return new InvoiceItemController();
    }
}
