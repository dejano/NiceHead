package rs.ac.uns.ftn.xws.ws.po;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.jws.WebService;

import rs.ac.uns.ftn.xws.generated.po.PaymentOrder;

@Stateless
@WebService(serviceName = "PoDocumentService", portName = "PoDocumentPort", targetNamespace = "http://www.ftn.uns.ac.rs/xws/ws/po", wsdlLocation = "file:/C:/Users/nikola42/Documents/Fakultet/XWS/projekat/NiceHead/Banka/WEB-INF/wsdl/po.wsdl", endpointInterface = "rs.ac.uns.ftn.xws.ws.po.PoDocument")
public class PoDocumentImpl implements PoDocument {

	private static final Logger LOG = Logger.getLogger(PoDocumentImpl.class
			.getName());

	public void paymentOrderHandle(PaymentOrder paymentOrderPart)
			throws PoException {
		LOG.info("Executing operation paymentOrderHandle");

		// TODO validate payment order and throw exception if needed
		// TODO add values in PoExceptionEnum

		// TODO baki : save payment order
	}

}
