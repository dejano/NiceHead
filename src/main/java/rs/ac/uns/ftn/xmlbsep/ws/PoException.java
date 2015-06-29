package rs.ac.uns.ftn.xmlbsep.ws;

import rs.ac.uns.ftn.xmlbsep.beans.jaxb.generated.payment.PoExceptionEnum;

import javax.xml.ws.WebFault;

@WebFault(name = "poExceptionEnum",
        targetNamespace = "http://www.ftn.uns.ac.rs/xws/xsd/paymentOrder")
public class PoException extends Exception {

    private static final long serialVersionUID = 1L;

    private PoExceptionEnum poExceptionEnum;

    public PoException() {
        super();
    }

    public PoException(String message) {
        super(message);
    }

    public PoException(String message, Throwable cause) {
        super(message, cause);
    }

    public PoException(String message,
                       PoExceptionEnum poExceptionEnum) {
        super(message);
        this.poExceptionEnum = poExceptionEnum;
    }

    public PoException(String message,
                       PoExceptionEnum poExceptionEnum, Throwable cause) {
        super(message, cause);
        this.poExceptionEnum = poExceptionEnum;
    }

    public PoExceptionEnum getFaultInfo() {
        return this.poExceptionEnum;
    }
}
