package rs.ac.uns.ftn.xmlbsep.exception;

import rs.ac.uns.ftn.xmlbsep.beans.jaxb.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UnauthorizedAccessExceptionMapper implements ExceptionMapper<UnauthorizedAccessException> {
    @Override
    public Response toResponse(UnauthorizedAccessException exception) {
        ErrorMessage error = new ErrorMessage(exception.getMessage(), 403);
        return Response.status(Response.Status.FORBIDDEN).build();
    }
}
