package rs.ac.uns.ftn.xmlbsep.ws.messageid;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public final class MessageIdDocument_MessageIdDocumentPort_Client {

	private MessageIdDocument_MessageIdDocumentPort_Client() {
	}

	public static void main(String args[]) throws Exception {
		System.out.println(getMessageId());
	}

	public static String getMessageId() throws Exception {
		URL wsdl = new URL("http://localhost:8080/cb/services/MessageIdDocument?wsdl");

		QName serviceName = new QName("http://www.ftn.uns.ac.rs/xws/ws/messageId",
				"MessageIdDocumentService");
		QName portName = new QName("http://www.ftn.uns.ac.rs/xws/ws/messageId",
				"MessageIdDocumentPort");

		Service service = Service.create(wsdl, serviceName);

		MessageIdDocument messageIdService = service.getPort(portName, MessageIdDocument.class);

		return messageIdService.getMessageId();
	}

}
