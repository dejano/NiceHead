package rs.ac.uns.ftn.xws.handler.client;

import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.ws.handler.LogicalHandler;
import javax.xml.ws.handler.LogicalMessageContext;
import javax.xml.ws.handler.MessageContext;

import org.w3c.dom.Document;

import rs.ac.uns.ftn.xws.misc.CertMap;
import rs.ac.uns.ftn.xws.misc.DocumentUtil;
import rs.ac.uns.ftn.xws.security.DecryptKEK;
import rs.ac.uns.ftn.xws.security.EncryptKEK;
import rs.ac.uns.ftn.xws.security.VerifyClientSignatureEnveloped;

public class ClientCryptoHandler implements
		LogicalHandler<LogicalMessageContext> {

	@Override
	public boolean handleMessage(LogicalMessageContext context) {

		System.out.println("\n*** Handler za kriptovanje kod Klijenta ***");

		Boolean outbound = (Boolean) context
				.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		Source source = context.getMessage().getPayload();
		Document document = DocumentUtil.convertToDocument(source);

		if (!outbound) {
			System.err.println("\n-- Kriptovanje --");
			try {
				// TODO extract doc from wrap when secwrapper is applied
				String cert = CertMap.getCert(VerifyClientSignatureEnveloped.getUnsigned(document));
				
				Document encryptedDoc = EncryptKEK.encryptDocument(document, cert);
				DocumentUtil.printDocument(encryptedDoc);
				context.getMessage().setPayload(new DOMSource(encryptedDoc));
			} catch (Exception e) {
			}
		} else {
			System.err.println("\n-- Dekriptovanje --");

			try {
				Document decryptedDoc = DecryptKEK.decryptDocument(document);
				DocumentUtil.printDocument(decryptedDoc);
				context.getMessage().setPayload(new DOMSource(decryptedDoc));
			} catch (Exception e) {
			}
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
