package rs.ac.uns.ftn.xmlbsep.security.handler;

import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.ws.handler.LogicalHandler;
import javax.xml.ws.handler.LogicalMessageContext;
import javax.xml.ws.handler.MessageContext;

import org.w3c.dom.Document;
import rs.ac.uns.ftn.xmlbsep.security.crypto.DecryptKEK;
import rs.ac.uns.ftn.xmlbsep.security.crypto.EncryptKEK;
import rs.ac.uns.ftn.xmlbsep.security.crypto.VerifyClientSignatureEnveloped;
import rs.ac.uns.ftn.xmlbsep.util.CertMap;
import rs.ac.uns.ftn.xmlbsep.util.DocumentUtil;
import rs.ac.uns.ftn.xmlbsep.util.SecWrapper;


public class ClientCryptoHandler implements
		LogicalHandler<LogicalMessageContext> {

	@Override
	public boolean handleMessage(LogicalMessageContext context) {
		System.out.println("\n*** Handler za kriptovanje kod Klijenta ***");

		Boolean outbound = (Boolean) context
				.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

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
			System.err.println("\n-- Kriptovanje --");
			try {
				Document unwrapped = SecWrapper.unwrap(VerifyClientSignatureEnveloped
						.getUnsigned(document));
				String cert = CertMap.getCert(unwrapped);

				Document encryptedDoc = EncryptKEK.encryptDocument(document,
						cert);
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
				e.printStackTrace();
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
