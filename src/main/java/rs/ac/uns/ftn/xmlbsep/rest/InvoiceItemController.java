package rs.ac.uns.ftn.xmlbsep.rest;

import javax.validation.constraints.Size;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Created by dejan on 14.5.2015..
 */
public class InvoiceItemController {

    @GET
    public String getItems(@Size(min=5) @PathParam("partnerId") int partnerId,@Size(min=3) @PathParam("invoiceId") int invoiceId) {
        return "Get invoice items for invoice with id " + invoiceId + " for partner with id " + partnerId;
    }

    @POST
    public Response saveItem(@PathParam("partnerId") int partnerId, @PathParam("invoiceId") int invoiceId) {
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{itemId}")
    public String getItem(@PathParam("partnerId") int partnerId, @PathParam("invoiceId") int invoiceId, @PathParam("itemId") int itemId) {
        return "Get invoice item " + itemId + " for invoice with id " + invoiceId + " for partner with id " + partnerId;
    }

    @PUT
    @Path("/{itemId}")
    public String updateItem(@PathParam("partnerId") int partnerId, @PathParam("invoiceId") int invoiceId, @PathParam("itemId") int itemId) {
        return "Get invoice item " + itemId + " for invoice with id " + invoiceId + " for partner with id " + partnerId;
    }

    @DELETE
    @Path("/{itemId}")
    public String deleteItem(@PathParam("partnerId") int partnerId, @PathParam("invoiceId") int invoiceId, @PathParam("itemId") int itemId) {
        return "Get invoice item " + itemId + " for invoice with id " + invoiceId + " for partner with id " + partnerId;
    }

}
