package rs.ac.uns.ftn.xmlbsep.rest;

import rs.ac.uns.ftn.xmlbsep.beans.jaxb.ContainerWrapper;
import rs.ac.uns.ftn.xmlbsep.beans.jaxb.generated.invoice.Invoice;
import rs.ac.uns.ftn.xmlbsep.dao.InvoiceDaoLocal;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import org.apache.log4j.Logger;
import rs.ac.uns.ftn.xmlbsep.dao.PartnerDaoLocal;
import rs.ac.uns.ftn.xmlbsep.validation.ValidXMLSchema;

@Consumes({"application/xml", "application/json"})
@Produces({ "application/xml" })
@Path("/partneri/{partnerId}/fakture")
public class InvoiceController {

    private static Logger log = Logger.getLogger(Invoice.class);

    @EJB
    private InvoiceDaoLocal invoiceDao;
    @EJB
    private PartnerDaoLocal partnerDao;


    @POST
    @ValidXMLSchema(value = "/xsd/invoice.xsd", clazz = Invoice.class)
    public Response store( Invoice invoice, @PathParam("partnerId") String partnerId, @Context UriInfo uriInfo) throws Throwable {
        if (!partnerDao.isPartner(partnerId)) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }

        Invoice retVal = null;
        try {
            System.out.println("entity: "+invoice);
            retVal = invoiceDao.persist(invoice);
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            throw e;
        }

        return Response.status(Response.Status.CREATED).contentLocation(uriInfo.getAbsolutePathBuilder().path(String.valueOf(retVal.getId())).build()).build();
    }

    @GET
    public Response getInvoices(@PathParam("partnerId") String partnerId) throws Exception {
        if (!partnerDao.isPartner(partnerId)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        List<Invoice> invoices = null;
        boolean notFound = false;
        ContainerWrapper wrapper = new ContainerWrapper();
        try {
            invoices = invoiceDao.findAllWherePartnersId(partnerId);
            if (invoices.size() == 0) {
                notFound = true;
            }
            wrapper.setResult(invoices);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            throw e;
        }
        return notFound ? Response.status(Response.Status.NOT_FOUND).build() : Response.status(Response.Status.OK).entity(wrapper).build();
    }

    @GET
    @Path("/{invoiceId}")
    public Response getInvoice(@PathParam("partnerId") String partnerId, @PathParam("invoiceId") int invoiceId) throws Exception {
        if (!partnerDao.isPartner(partnerId)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Invoice retVal = null;
        try {
            retVal = invoiceDao.findInvoiceBy(partnerId, String.valueOf(invoiceId));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            throw e;
        }
        return retVal == null ? Response.status(Response.Status.NOT_FOUND).build() : Response.status(Response.Status.OK).entity(retVal).build();
    }

}
