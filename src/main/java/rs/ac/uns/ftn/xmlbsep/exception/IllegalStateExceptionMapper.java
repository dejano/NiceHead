package rs.ac.uns.ftn.xmlbsep.exception;

import rs.ac.uns.ftn.xmlbsep.beans.jaxb.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class IllegalStateExceptionMapper implements ExceptionMapper<IllegalStateException> {
    @Override
    public Response toResponse(IllegalStateException exception) {
        ErrorMessage error = new ErrorMessage(exception.getMessage(), 401);
        return Response.status(Response.Status.FORBIDDEN).build();
    }
}
