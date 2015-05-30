package rs.ac.uns.ftn.xmlbsep.beans.jaxb;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by dejan on 26.5.2015..
 */
@XmlRootElement
public class ErrorMessage {

    private String message;
    private int errorCode;

    public ErrorMessage() {
    }

    public ErrorMessage(String message, int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
