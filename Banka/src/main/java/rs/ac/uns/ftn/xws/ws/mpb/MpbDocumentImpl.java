package rs.ac.uns.ftn.xws.ws.mpb;

import java.util.logging.Logger;

import javax.ejb.Stateless;

import rs.ac.uns.ftn.xws.generated.ClearingConfirmMessage;
import rs.ac.uns.ftn.xws.generated.RtgsConfirmMessage;

@Stateless
@javax.jws.WebService(serviceName = "MpbDocumentService", portName = "MpbDocumentPort", targetNamespace = "http://www.ftn.uns.ac.rs/xws/ws/mpb", wsdlLocation = "file:/C:/Users/nikola42/Documents/Fakultet/XWS/projekat/NiceHead/Banka/WEB-INF/wsdl/mpb.wsdl", endpointInterface = "rs.ac.uns.ftn.xws.ws.mpb.MpbDocument")
public class MpbDocumentImpl implements MpbDocument {

	private static final Logger LOG = Logger.getLogger(MpbDocumentImpl.class
			.getName());

	public void rtgsConfirm(RtgsConfirmMessage rtgsConfirmPart) {
		LOG.info("Executing operation rtgsConfirm");
		System.out.println(rtgsConfirmPart);
		try {
		} catch (java.lang.Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

	public void clearingConfirm(ClearingConfirmMessage clearingConfirmPart) {
		LOG.info("Executing operation clearingConfirm");
		System.out.println(clearingConfirmPart);
		try {
		} catch (java.lang.Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

}
