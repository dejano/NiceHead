package rs.ac.uns.ftn.xmlbsep.rest;


import rs.ac.uns.ftn.xmlbsep.service.InvoiceServiceLocal;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.*;
import java.util.Properties;

/**
 * Created by dejan on 14.5.2015..
 */

@Path("/partneri")
@Consumes({ "application/xml" })
@Produces({ "application/xml" })
public class PartnerController {

    @GET
    @Path("/")
    public String getAll() {
        return "Return all partners.";
    }

    @GET
    @Path("/{partnerId}")
    public String get(@PathParam("partnerId") long partnerId) {
        return "Return partner with " + partnerId + " ID.";
    }

    // unable to resolve dependency injection!
//    @Path("/{partnerId}/fakture")
//    public InvoiceController getInvoiceResource() {
//        return new InvoiceController();
//    }
}
