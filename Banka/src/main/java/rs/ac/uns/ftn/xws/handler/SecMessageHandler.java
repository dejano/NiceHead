package rs.ac.uns.ftn.xws.handler;

import java.util.Calendar;

import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.ws.handler.LogicalHandler;
import javax.xml.ws.handler.LogicalMessageContext;
import javax.xml.ws.handler.MessageContext;

import org.w3c.dom.Document;

import rs.ac.uns.ftn.xws.dao.TokensDao;
import rs.ac.uns.ftn.xws.misc.DocumentUtil;
import rs.ac.uns.ftn.xws.misc.SecWrapper;

// TODO add to chain and create one for client one for ws?
public class SecMessageHandler implements LogicalHandler<LogicalMessageContext> {

	@Override
	public boolean handleMessage(LogicalMessageContext context) {
		boolean ret = false;

		System.out.println("\n*** Handler za wrap/unwrap poruka ***");

		Boolean outbound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		Source source = context.getMessage().getPayload();
		Document document = DocumentUtil.convertToDocument(source);

		if (outbound) {
			System.out.println("\n-- Wrap --");

			Document wrappedDocument = SecWrapper.wrap(document);
			context.getMessage().setPayload(new DOMSource(wrappedDocument));
		} else {
			System.out.println("\n-- Unwrap --");

			String token = SecWrapper.getToken(document);
			
			boolean used = TokensDao.isAlreadyUsed(token);
			ret = !used;

			boolean timeout = Calendar.getInstance().getTime().getTime()
					- SecWrapper.getTimestamp(document) > 10000L;
			ret &= !timeout;

			if (ret) {
				TokensDao.insertToken(token);
				
				Document unwrappedDocument = SecWrapper.unwrap(document);
				context.getMessage().setPayload(new DOMSource(unwrappedDocument));
			}
		}

		return ret;
	}

	@Override
	public void close(MessageContext arg0) {

	}

	@Override
	public boolean handleFault(LogicalMessageContext arg0) {
		return true;
	}
}