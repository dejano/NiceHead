package rs.ac.uns.ftn.xmlbsep.exception;

import rs.ac.uns.ftn.xmlbsep.beans.jaxb.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ThrowableExceptionMapper implements ExceptionMapper<Throwable> {
    public Response toResponse(Throwable exception) {
        ErrorMessage invalidXMLMessage = new ErrorMessage(exception.getMessage(), 500);
        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(invalidXMLMessage)
                .build();
    }
}
