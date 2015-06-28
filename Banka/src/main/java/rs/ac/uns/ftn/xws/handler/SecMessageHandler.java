package rs.ac.uns.ftn.xws.handler;

import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.ws.handler.LogicalHandler;
import javax.xml.ws.handler.LogicalMessageContext;
import javax.xml.ws.handler.MessageContext;

import org.w3c.dom.Document;

import rs.ac.uns.ftn.xws.misc.DocumentUtil;
import rs.ac.uns.ftn.xws.misc.SecWrapper;
import rs.ac.uns.ftn.xws.security.DecryptKEK;
import rs.ac.uns.ftn.xws.security.EncryptKEK;

public class SecMessageHandler implements LogicalHandler<LogicalMessageContext> {

	@Override
	public boolean handleMessage(LogicalMessageContext context) {
		System.out.println("\n*** Handler za wrap/unwrap poruka ***");

		Boolean outbound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		Source source = context.getMessage().getPayload();
		Document document = DocumentUtil.convertToDocument(source);
		
		if (outbound) {
			System.err.println("\n-- Wrap --");
			
			Document wrappedDocument = SecWrapper.wrap(document);
			context.getMessage().setPayload(new DOMSource(wrappedDocument));
		} else {
			System.err.println("\n-- Unwrap --");

			Document unwrappedDocument = SecWrapper.unwrap(document);
			context.getMessage().setPayload(new DOMSource(unwrappedDocument));
		}
		
		return true;
	}

	@Override
	public void close(MessageContext arg0) {

	}

	@Override
	public boolean handleFault(LogicalMessageContext arg0) {
		return true;
	}
}