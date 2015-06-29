package rs.ac.uns.ftn.xmlbsep.rest;


import rs.ac.uns.ftn.xmlbsep.beans.jaxb.ResultWrapper;
import rs.ac.uns.ftn.xmlbsep.beans.jaxb.generated.partner.Partner;
import rs.ac.uns.ftn.xmlbsep.dao.PartnerDaoLocal;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Path("/partneri")
@Consumes({"application/xml"})
@Produces({"application/xml"})
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
    public Response get(@PathParam("partnerId") String partnerId) throws IOException, JAXBException {
        Partner partner = partnerDao.getPartner(partnerId);

        if (partner == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        
        return Response.status(Response.Status.OK).entity(partner).build();
    }

}
