package rs.ac.uns.ftn.xmlbsep.rest;


import rs.ac.uns.ftn.xmlbsep.beans.jaxb.User;
import rs.ac.uns.ftn.xmlbsep.dao.UserDaoLocal;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.UnknownHostException;

public class AuthController {

    @EJB
    private UserDaoLocal userDao;

    @POST
    @Path("/auth")
    public Response login(@FormParam("username") String username, @FormParam("password") String password) {
        User user = userDao.login(username, password);
        System.out.println(user);
        if (user != null) {
            user.setPassword("");
            return Response.status(Response.Status.OK).entity(user).build();
        }

        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @GET
    @Path("/logout")
    public Response logout() {
        userDao.logout();
        return Response.status(Response.Status.OK).build();
    }
}
