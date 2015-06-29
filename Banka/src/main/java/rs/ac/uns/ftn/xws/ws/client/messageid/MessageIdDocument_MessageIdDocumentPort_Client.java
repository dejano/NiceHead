package rs.ac.uns.ftn.xws.ws.client.messageid;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.handler.Handler;

import rs.ac.uns.ftn.xws.generated.cmn.Message;
import rs.ac.uns.ftn.xws.handler.client.mpcb.ClientCryptoHandler;
import rs.ac.uns.ftn.xws.handler.client.mpcb.ClientSignatureHandler;
import rs.ac.uns.ftn.xws.misc.BankConstants;

public final class MessageIdDocument_MessageIdDocumentPort_Client {

	private MessageIdDocument_MessageIdDocumentPort_Client() {
	}

	public static void main(String args[]) throws java.lang.Exception {
		try {
			URL wsdl = new URL(
					"http://localhost:8080/cb/services/MessageIdDocument?wsdl");

			QName serviceName = new QName(
					"http://www.ftn.uns.ac.rs/xws/ws/messageId",
					"MessageIdDocumentService");
			QName portName = new QName(
					"http://www.ftn.uns.ac.rs/xws/ws/messageId",
					"MessageIdDocumentPort");

			Service service = Service.create(wsdl, serviceName);
			
			MessageIdDocument messageIdService = service.getPort(portName,
					MessageIdDocument.class);

			System.out.println(messageIdService.getMessageId());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public static String getMessageId() throws Exception {
		String messageId = "";
		
		URL wsdl = new URL(
				"http://localhost:8080/cb/services/MessageIdDocument?wsdl");

		QName serviceName = new QName(
				"http://www.ftn.uns.ac.rs/xws/ws/messageId",
				"MessageIdDocumentService");
		QName portName = new QName(
				"http://www.ftn.uns.ac.rs/xws/ws/messageId",
				"MessageIdDocumentPort");

		Service service = Service.create(wsdl, serviceName);

		MessageIdDocument messageIdService = service.getPort(portName,
				MessageIdDocument.class);
		
		
		
//		ClientCryptoHandler crypto = new ClientCryptoHandler();
//		ClientSignatureHandler sing = new ClientSignatureHandler();
//		
//		@SuppressWarnings("rawtypes")
//		List<Handler> handlerChain = new ArrayList<Handler>();
//		//handlerChain.add(attack);
//		handlerChain.add(sing);
//		handlerChain.add(crypto);
//		
//		
//		((BindingProvider) messageIdService).getBinding().setHandlerChain(handlerChain);
		
		messageId  = messageIdService.getMessageId();
		
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(new Date());
		XMLGregorianCalendar currentTimestamp = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		
		return messageId;
	}

}
