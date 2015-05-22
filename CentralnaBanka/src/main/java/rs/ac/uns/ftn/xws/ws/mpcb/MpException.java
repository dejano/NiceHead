package rs.ac.uns.ftn.xws.ws.mpcb;

import javax.xml.ws.WebFault;

import rs.ac.uns.ftn.xws.generated.MpExceptionEnum;

@WebFault(name = "mpExceptionEnum", targetNamespace = "http://www.ftn.uns.ac.rs/xws/xsd/mp")
public class MpException extends Exception {

	private static final long serialVersionUID = 1L;

	private MpExceptionEnum mpExceptionEnum;

	public MpException() {
		super();
	}

	public MpException(String message) {
		super(message);
	}

	public MpException(String message, Throwable cause) {
		super(message, cause);
	}

	public MpException(String message,
			MpExceptionEnum mpExceptionEnum) {
		super(message);
		this.mpExceptionEnum = mpExceptionEnum;
	}

	public MpException(String message,
			MpExceptionEnum mpExceptionEnum,
			Throwable cause) {
		super(message, cause);
		this.mpExceptionEnum = mpExceptionEnum;
	}

	public MpExceptionEnum getFaultInfo() {
		return this.mpExceptionEnum;
	}
}
