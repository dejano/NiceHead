package rs.ac.uns.ftn.xmlbsep.rest;


import javax.ws.rs.*;

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

}
