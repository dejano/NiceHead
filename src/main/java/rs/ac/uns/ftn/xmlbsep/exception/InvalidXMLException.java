package rs.ac.uns.ftn.xmlbsep.exception;

import javax.ejb.ApplicationException;

/**
 * Created by dejan on 25.5.2015..
 */
@ApplicationException
public class InvalidXMLException extends RuntimeException {

    public InvalidXMLException(String message, int errorCode) {
        super(message);
    }
}
