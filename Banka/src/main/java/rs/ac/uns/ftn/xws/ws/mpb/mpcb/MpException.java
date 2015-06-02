
package rs.ac.uns.ftn.xws.ws.mpb.mpcb;

import javax.xml.ws.WebFault;


@WebFault(name = "mpExceptionEnum", targetNamespace = "http://www.ftn.uns.ac.rs/xws/xsd/mp")
public class MpException extends Exception {
    
	private static final long serialVersionUID = 1L;
	
	private rs.ac.uns.ftn.xws.generated.mp.MpExceptionEnum mpExceptionEnum;

    public MpException() {
        super();
    }
    
    public MpException(String message) {
        super(message);
    }
    
    public MpException(String message, Throwable cause) {
        super(message, cause);
    }

    public MpException(String message, rs.ac.uns.ftn.xws.generated.mp.MpExceptionEnum mpExceptionEnum) {
        super(message);
        this.mpExceptionEnum = mpExceptionEnum;
    }

    public MpException(String message, rs.ac.uns.ftn.xws.generated.mp.MpExceptionEnum mpExceptionEnum, Throwable cause) {
        super(message, cause);
        this.mpExceptionEnum = mpExceptionEnum;
    }

    public rs.ac.uns.ftn.xws.generated.mp.MpExceptionEnum getFaultInfo() {
        return this.mpExceptionEnum;
    }
}
