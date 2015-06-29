
package rs.ac.uns.ftn.xws.ws.bsws;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.6.5
 * 2015-06-02T02:38:31.907+02:00
 * Generated source version: 2.6.5
 */

@WebFault(name = "bsExceptionEnum", targetNamespace = "http://www.ftn.uns.ac.rs/xws/xsd/bs")
public class BsException extends Exception {
    
    private rs.ac.uns.ftn.xws.generated.bs.BsExceptionEnum bsExceptionEnum;

    public BsException() {
        super();
    }
    
    public BsException(String message) {
        super(message);
    }
    
    public BsException(String message, Throwable cause) {
        super(message, cause);
    }

    public BsException(String message, rs.ac.uns.ftn.xws.generated.bs.BsExceptionEnum bsExceptionEnum) {
        super(message);
        this.bsExceptionEnum = bsExceptionEnum;
    }

    public BsException(String message, rs.ac.uns.ftn.xws.generated.bs.BsExceptionEnum bsExceptionEnum, Throwable cause) {
        super(message, cause);
        this.bsExceptionEnum = bsExceptionEnum;
    }

    public rs.ac.uns.ftn.xws.generated.bs.BsExceptionEnum getFaultInfo() {
        return this.bsExceptionEnum;
    }
}