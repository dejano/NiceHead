
package rs.ac.uns.ftn.xws.ws.po;

import javax.xml.ws.WebFault;


@WebFault(name = "poExceptionEnum", targetNamespace = "http://www.ftn.uns.ac.rs/xws/xsd/po")
public class PoException extends Exception {
    
	private static final long serialVersionUID = 1L;
	
	private rs.ac.uns.ftn.xws.generated.po.PoExceptionEnum poExceptionEnum;

    public PoException() {
        super();
    }
    
    public PoException(String message) {
        super(message);
    }
    
    public PoException(String message, Throwable cause) {
        super(message, cause);
    }

    public PoException(String message, rs.ac.uns.ftn.xws.generated.po.PoExceptionEnum poExceptionEnum) {
        super(message);
        this.poExceptionEnum = poExceptionEnum;
    }

    public PoException(String message, rs.ac.uns.ftn.xws.generated.po.PoExceptionEnum poExceptionEnum, Throwable cause) {
        super(message, cause);
        this.poExceptionEnum = poExceptionEnum;
    }

    public rs.ac.uns.ftn.xws.generated.po.PoExceptionEnum getFaultInfo() {
        return this.poExceptionEnum;
    }
}
