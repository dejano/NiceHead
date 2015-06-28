package rs.ac.uns.ftn.xws.handler.ws;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.ws.handler.LogicalHandler;
import javax.xml.ws.handler.LogicalMessageContext;
import javax.xml.ws.handler.MessageContext;

import org.w3c.dom.Document;

import rs.ac.uns.ftn.xws.misc.DocumentUtil;
import rs.ac.uns.ftn.xws.security.DecryptKEK;
import rs.ac.uns.ftn.xws.security.EncryptKEK;
import rs.ac.uns.ftn.xws.ws.bankdetails.BdDocumentImpl;


public class WSCryptoHandler implements LogicalHandler<LogicalMessageContext> {

	private static final Logger LOG = Logger.getLogger(WSCryptoHandler.class
			.getName());
	
	@Override
	public boolean handleMessage(LogicalMessageContext context) {

		LOG.info("\n*** Handler za kriptovanje kod Web Servisa ***");

		Boolean outbound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		Source source = context.getMessage().getPayload();
		Document document = DocumentUtil.convertToDocument(source);
		if (outbound) {
			LOG.info("\n-- Kriptovanje --");
			try {
			Document encryptedDoc = EncryptKEK.encryptDocument(document);
			context.getMessage().setPayload(new DOMSource(encryptedDoc));
				DocumentUtil.printDocument(encryptedDoc);
			} catch (Exception e) {
				LOG.log(Level.SEVERE, e.getMessage(), e);
			}
		} else {
			LOG.info("\n-- Dekriptovanje --");	
			System.err.println("\n-- ISPIS ENKRIPTOVANOG DOKUMENTA PRE SAM DEKRIPCIJE --");	
			Document decryptedDoc = null;
			try {
			DocumentUtil.printDocument(document); // dodato
			
			decryptedDoc = DecryptKEK.decryptDocument(document);
			DocumentUtil.printDocument(decryptedDoc);
			
			} catch (Exception e) {
				LOG.log(Level.SEVERE, e.getMessage(), e);
				LOG.info("E GARI DESIO SE EXCEPTION PRILIKOM DEKRIPCIJE");
			}
			context.getMessage().setPayload(new DOMSource(decryptedDoc));
		}
		return true;
	}

	@Override
	public boolean handleFault(LogicalMessageContext context) {
		return true;
	}

	@Override
	public void close(MessageContext context) {
	}
}
