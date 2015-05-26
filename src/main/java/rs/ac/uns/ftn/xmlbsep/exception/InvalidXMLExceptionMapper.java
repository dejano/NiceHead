package rs.ac.uns.ftn.xmlbsep.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
public class InvalidXMLExceptionMapper implements ExceptionMapper<InvalidXMLException> {
    public Response toResponse(InvalidXMLException exception) {
        System.out.println("InvalidXMLExceptionMapped.toResponse");
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
