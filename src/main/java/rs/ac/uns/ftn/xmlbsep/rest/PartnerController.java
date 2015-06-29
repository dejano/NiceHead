package rs.ac.uns.ftn.xmlbsep.rest;


import rs.ac.uns.ftn.xmlbsep.beans.jaxb.ResultWrapper;
import rs.ac.uns.ftn.xmlbsep.beans.jaxb.generated.partner.Partner;
import rs.ac.uns.ftn.xmlbsep.dao.PartnerDaoLocal;
import rs.ac.uns.ftn.xmlbsep.security.Authenticate;
import rs.ac.uns.ftn.xmlbsep.security.HasPermission;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

@Path("/partneri")
@Consumes({ "application/xml" })
@Produces({ "application/xml" })
public class PartnerController {

    @EJB
    private PartnerDaoLocal partnerDao;

    @GET
    @Path("/")
    public Response getAll() throws IOException, JAXBException {
        ResultWrapper wrapper = new ResultWrapper();

        try {
            List<Partner> result = partnerDao.getPartners();
            wrapper.setResult(result);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return Response.status(Response.Status.OK).entity(wrapper).build();
    }

    @GET
    @Path("/{partnerId}")
    public String get(@PathParam("partnerId") long partnerId) {
        return "Return partner with " + partnerId + " ID.";
    }

}
