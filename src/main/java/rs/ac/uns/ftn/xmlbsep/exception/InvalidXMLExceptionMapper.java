package rs.ac.uns.ftn.xmlbsep.exception;

import rs.ac.uns.ftn.xmlbsep.beans.jaxb.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidXMLExceptionMapper implements ExceptionMapper<InvalidXMLException> {
    public Response toResponse(InvalidXMLException exception) {
        String message = exception.getMessage();
        if (message == null) {
            message = "Provided xml file isn't valid.";
        }
        ErrorMessage invalidXMLMessage = new ErrorMessage(message, 400);
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(invalidXMLMessage)
                .build();
    }
}
