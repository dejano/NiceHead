
package rs.ac.uns.ftn.xws.ws.mpcb;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.6.5
 * 2015-05-19T11:46:58.904+02:00
 * Generated source version: 2.6.5
 */

@WebFault(name = "mpExceptionEnum", targetNamespace = "http://www.ftn.uns.ac.rs/xws/xsd/mp")
public class MpException extends Exception {
    
    private rs.ac.uns.ftn.xws.generated.MpExceptionEnum mpExceptionEnum;

    public MpException() {
        super();
    }
    
    public MpException(String message) {
        super(message);
    }
    
    public MpException(String message, Throwable cause) {
        super(message, cause);
    }

    public MpException(String message, rs.ac.uns.ftn.xws.generated.MpExceptionEnum mpExceptionEnum) {
        super(message);
        this.mpExceptionEnum = mpExceptionEnum;
    }

    public MpException(String message, rs.ac.uns.ftn.xws.generated.MpExceptionEnum mpExceptionEnum, Throwable cause) {
        super(message, cause);
        this.mpExceptionEnum = mpExceptionEnum;
    }

    public rs.ac.uns.ftn.xws.generated.MpExceptionEnum getFaultInfo() {
        return this.mpExceptionEnum;
    }
}
