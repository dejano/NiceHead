package rs.ac.uns.ftn.xmlbsep.rest;

import org.apache.log4j.Logger;
import rs.ac.uns.ftn.xmlbsep.beans.jaxb.ContainerWrapper;
import rs.ac.uns.ftn.xmlbsep.beans.jaxb.generated.invoice.Invoice;
import rs.ac.uns.ftn.xmlbsep.beans.jaxb.generated.invoice.Item;
import rs.ac.uns.ftn.xmlbsep.dao.InvoiceDaoLocal;
import rs.ac.uns.ftn.xmlbsep.dao.PartnerDaoLocal;
import rs.ac.uns.ftn.xmlbsep.validation.ValidXMLSchema;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

/**
 * Created by dejan on 14.5.2015..
 */
@Path("/partneri/{partnerId}/fakture/{invoiceId}/stavke")
public class InvoiceItemController {

    private static Logger log = Logger.getLogger(Item.class);

    @EJB
    private InvoiceDaoLocal invoiceDao;
    @EJB
    private PartnerDaoLocal partnerDao;

    @GET
    public Response getItems(@PathParam("partnerId") String partnerId, @PathParam("invoiceId") int invoiceId) throws Exception {
        if (!partnerDao.isPartner(partnerId)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        System.out.println("InvoiceItemController.getItems");
        List<Item> invoices = null;
        boolean notFound = false;
        ContainerWrapper wrapper = new ContainerWrapper();
        try {
            invoices = invoiceDao.getItems(partnerId, String.valueOf(invoiceId));
            if (invoices == null || invoices.size() == 0) {
                notFound = true;
            }
            wrapper.setResult(invoices);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            throw e;
        }
        return notFound ? Response.status(Response.Status.NOT_FOUND).build() : Response.status(Response.Status.OK).entity(wrapper).build();
    }

    @POST
    @ValidXMLSchema(value = "/xsd/item.xsd", clazz = Item.class)
    public Response saveItem(Item item, @PathParam("partnerId") String partnerId, @PathParam("invoiceId") int invoiceId, @Context UriInfo uriInfo) throws Throwable {
        if (!partnerDao.isPartner(partnerId)) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }

        Invoice invoice = invoiceDao.findInvoiceBy(partnerId, String.valueOf(invoiceId));

        if (invoice == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        int itemId = 0;
        try {
            itemId = invoiceDao.createItem(partnerId, (long) invoiceId, item);
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            throw e;
        }
        return Response.status(Response.Status.CREATED).contentLocation(uriInfo.getAbsolutePathBuilder().path(String.valueOf(itemId)).build()).build();
    }

    @GET
    @Path("/{itemId}")
    public Response getItem(@PathParam("partnerId") String partnerId, @PathParam("invoiceId") int invoiceId, @PathParam("itemId") int itemId) throws Exception {
        if (!partnerDao.isPartner(partnerId)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Item retVal = null;
        try {
            retVal = invoiceDao.getItem(partnerId, (long) invoiceId, (long) itemId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            throw e;
        }
        return retVal == null ? Response.status(Response.Status.NOT_FOUND).build() : Response.status(Response.Status.OK).entity(retVal).build();
    }

    @PUT
    @Path("/{itemId}")
    public Response updateItem(Item item, @PathParam("partnerId") String partnerId, @PathParam("invoiceId") int invoiceId, @PathParam("itemId") int itemId) throws Throwable {
        if (!partnerDao.isPartner(partnerId)) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }

        Invoice invoice = invoiceDao.findInvoiceBy(partnerId, String.valueOf(invoiceId));

        if (invoice == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try {
            item.setId(itemId);
            invoiceDao.updateItem(partnerId, (long) invoiceId, item);
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            throw e;
        }
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/{itemId}")
    public Response deleteItem(@PathParam("partnerId") String partnerId, @PathParam("invoiceId") int invoiceId, @PathParam("itemId") int itemId) throws Exception {
        if (!partnerDao.isPartner(partnerId)) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }

        try {
            invoiceDao.removeItem(partnerId, (long) invoiceId, (long) itemId);
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            throw e;
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
