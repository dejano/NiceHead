package rs.ac.uns.ftn.xmlbsep.security.handler;

import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.ws.handler.LogicalHandler;
import javax.xml.ws.handler.LogicalMessageContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.xml.security.exceptions.XMLSecurityException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import rs.ac.uns.ftn.xmlbsep.security.crypto.SignEnveloped;
import rs.ac.uns.ftn.xmlbsep.security.crypto.VerifyClientSignatureEnveloped;
import rs.ac.uns.ftn.xmlbsep.util.CertMap;
import rs.ac.uns.ftn.xmlbsep.util.DocumentUtil;
import rs.ac.uns.ftn.xmlbsep.util.SecWrapper;


public class ClientSignatureHandler implements
		LogicalHandler<LogicalMessageContext> {

	@Override
	public boolean handleMessage(LogicalMessageContext context) {
		System.out
				.println("\n*** Handler za digitalno potpisivanje kod Klijenta ***");

		Boolean isResponse = (Boolean) context
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

		if (isResponse) {
			System.err.println("\nDokument koji je stigao");
			try {
				DocumentUtil.printDocument(document);
			} catch (Exception e) {
			}

			System.err.println("\n-- Potpisivanje --");

			try {
				Document signedDocument = SignEnveloped.signDocument(document);
				Source signedSource = new DOMSource(signedDocument);
				context.getMessage().setPayload(signedSource);
				DocumentUtil.printDocument(signedDocument);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			String cn = null;
			try {
				cn = VerifyClientSignatureEnveloped.getCommonName(document);
			} catch (XMLSecurityException e1) {
				e1.printStackTrace();
			}

			System.err.println("\nValidacija i skidanje potpisa...");

			try {
				boolean signatureValid = VerifyClientSignatureEnveloped
						.verifySignature(document);

//				if (!signatureValid) {
//					return false; // potpis nije validan
//				}

				// uklanjanje potpisa
				Element element = (Element) document.getElementsByTagNameNS(
						"http://www.w3.org/2000/09/xmldsig#", "Signature")
						.item(0);
				element.getParentNode().removeChild(element);

				DocumentUtil.printDocument(document);
			} catch (Exception e) {
			}

			CertMap.add(SecWrapper.unwrap(document), cn);

			context.getMessage().setPayload(new DOMSource(document));
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
