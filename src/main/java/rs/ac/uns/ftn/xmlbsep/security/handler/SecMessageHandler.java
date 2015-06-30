package rs.ac.uns.ftn.xmlbsep.security.handler;

import java.util.Calendar;

import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.ws.handler.LogicalHandler;
import javax.xml.ws.handler.LogicalMessageContext;
import javax.xml.ws.handler.MessageContext;

import org.w3c.dom.Document;
import rs.ac.uns.ftn.xmlbsep.dao.impl.TokensDao;
import rs.ac.uns.ftn.xmlbsep.util.DocumentUtil;
import rs.ac.uns.ftn.xmlbsep.util.SecWrapper;


// TODO add to chain and create one for client one for ws?
public class SecMessageHandler implements LogicalHandler<LogicalMessageContext> {

	@Override
	public boolean handleMessage(LogicalMessageContext context) {
		boolean ret = true;

		System.out.println("\n*** Handler za wrap/unwrap poruka ***");

		Boolean outbound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

		Document document;
		try {
			Source source = context.getMessage().getPayload();
			document = DocumentUtil.convertToDocument(source);
			if(document == null || document.getFirstChild() == null){
				return true;
			}
		} catch (Exception e) {
			return true;
		}

		if (outbound) {
			System.out.println("\n-- Wrap --");

			Document wrappedDocument = SecWrapper.wrap(document);
			try {
				DocumentUtil.printDocument(wrappedDocument);
			} catch (Exception e) {
			}
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
				try {
					DocumentUtil.printDocument(document);
				} catch (Exception e) {
				}
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