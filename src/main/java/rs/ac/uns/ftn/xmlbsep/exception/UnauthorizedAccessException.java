package rs.ac.uns.ftn.xmlbsep.exception;

import javax.ejb.ApplicationException;

@ApplicationException
public class UnauthorizedAccessException extends RuntimeException {

    public UnauthorizedAccessException(String message) {
        super(message);
    }
}
