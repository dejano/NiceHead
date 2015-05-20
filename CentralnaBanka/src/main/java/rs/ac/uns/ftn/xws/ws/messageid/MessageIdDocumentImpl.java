package rs.ac.uns.ftn.xws.ws.messageid;

import java.util.logging.Logger;

import javax.ejb.Stateless;

import rs.ac.uns.ftn.xws.dao.MessageIdDao;

@Stateless
@javax.jws.WebService(serviceName = "MessageIdDocumentService", portName = "MessageIdDocumentPort", targetNamespace = "http://www.ftn.uns.ac.rs/xws/ws/messageId", wsdlLocation = "file:/C:/Users/nikola42/Documents/Fakultet/XWS/projekat/NiceHead/CentralnaBanka/WEB-INF/wsdl/messageId.wsdl", endpointInterface = "rs.ac.uns.ftn.xws.ws.messageid.MessageIdDocument")
public class MessageIdDocumentImpl implements MessageIdDocument {

	private static final Logger LOG = Logger
			.getLogger(MessageIdDocumentImpl.class.getName());

	public String getMessageId() {
		LOG.info("getMessageId called");
		MessageIdDao mid = new MessageIdDao();

		return mid.getMessageId();
	}

}
