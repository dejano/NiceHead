package rs.ac.uns.ftn.xws.handler.client.mpcb;

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

public class ClientCryptoHandler implements LogicalHandler<LogicalMessageContext> {

	@Override
	public boolean handleMessage(LogicalMessageContext context) {

		System.out.println("\n*** Handler za kriptovanje kod Klijenta ***");

		Boolean outbound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		Source source = context.getMessage().getPayload();
		Document document = DocumentUtil.convertToDocument(source);

		if (outbound) {
			System.out.println("\n-- Kriptovanje --");

			// TODO extract doc from wrap when secwrapper is applied
			try {
				String cert = CertMap.getCert(VerifyClientSignatureEnveloped.getUnsigned(document));
				System.out.println(cert);
				Document encryptedDoc = EncryptKEK.encryptDocument(document, cert);
				System.out.println("!!!");
				DocumentUtil.printDocument(encryptedDoc);
				System.out.println("!!!");
				context.getMessage().setPayload(new DOMSource(encryptedDoc));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("\n-- Dekriptovanje --");

			Document decryptedDoc = null;
			try {
				DocumentUtil.printDocument(document);
				decryptedDoc = DecryptKEK.decryptDocument(document);
				DocumentUtil.printDocument(decryptedDoc);
			} catch (Exception e) {
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
