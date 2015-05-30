package rs.ac.uns.ftn.xws.ws.client.bankDetails;

import javax.xml.ws.WebFault;

@WebFault(name = "noBankCodeMessage", targetNamespace = "http://www.ftn.uns.ac.rs/xws/ws/bankDetails")
public class NoBankCodeException extends Exception {

	private static final long serialVersionUID = 3813262265478767814L;

	private java.lang.String noBankCodeMessage;

	public NoBankCodeException() {
		super();
	}

	public NoBankCodeException(String message) {
		super(message);
	}

	public NoBankCodeException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoBankCodeException(String message,
			java.lang.String noBankCodeMessage) {
		super(message);
		this.noBankCodeMessage = noBankCodeMessage;
	}

	public NoBankCodeException(String message,
			java.lang.String noBankCodeMessage, Throwable cause) {
		super(message, cause);
		this.noBankCodeMessage = noBankCodeMessage;
	}

	public java.lang.String getFaultInfo() {
		return this.noBankCodeMessage;
	}
}
