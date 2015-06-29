package rs.ac.uns.ftn.xmlbsep.rest;


import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;
import rs.ac.uns.ftn.xmlbsep.beans.jaxb.Config;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.UnknownHostException;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/config")
public class ConfigController {

    @POST
    @Path("/create")
    public Response config(Config config) {
        System.out.println("ConfigController.config");
        System.out.println(config);
        final Morphia morphia = new Morphia();
        morphia.mapPackage("rs.ac.uns.ftn.xmlbsep.beans.jaxb");
        try {
            final Datastore datastore = morphia.createDatastore(new MongoClient(), "company");
            datastore.ensureIndexes();

            Key<Config> keys = datastore.save(config);
            System.out.println(keys.toString());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response get() {
        final Morphia morphia = new Morphia();
        System.out.println("ConfigController.factory");
        morphia.mapPackage("rs.ac.uns.ftn.xmlbsep.beans.jaxb");
        try {
            final Datastore datastore = morphia.createDatastore(new MongoClient(), "company");
            datastore.ensureIndexes();

            List<Config> configs = datastore.find(Config.class).asList();
            if (configs.size() > 0) {
                System.out.println(configs.get(0));
                return Response.status(Response.Status.OK).entity(configs.get(0)).build();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
