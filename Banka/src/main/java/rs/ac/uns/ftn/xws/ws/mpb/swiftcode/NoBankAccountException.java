package rs.ac.uns.ftn.xws.ws.mpb.swiftcode;

import javax.xml.ws.WebFault;

@WebFault(name = "noBankAccountException", targetNamespace = "http://www.ftn.uns.ac.rs/xws/ws/swiftCode")
public class NoBankAccountException extends Exception {

	private static final long serialVersionUID = 9058324186227998754L;

	private java.lang.String noBankAccountException;

	public NoBankAccountException() {
		super();
	}

	public NoBankAccountException(String message) {
		super(message);
	}

	public NoBankAccountException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoBankAccountException(String message,
			java.lang.String noBankAccountException) {
		super(message);
		this.noBankAccountException = noBankAccountException;
	}

	public NoBankAccountException(String message,
			java.lang.String noBankAccountException, Throwable cause) {
		super(message, cause);
		this.noBankAccountException = noBankAccountException;
	}

	public java.lang.String getFaultInfo() {
		return this.noBankAccountException;
	}
}
