package rs.ac.uns.ftn.xws.handler.client;

import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.ws.handler.LogicalHandler;
import javax.xml.ws.handler.LogicalMessageContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.xml.security.exceptions.XMLSecurityException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import rs.ac.uns.ftn.xws.misc.CertMap;
import rs.ac.uns.ftn.xws.misc.DocumentUtil;
import rs.ac.uns.ftn.xws.security.SignEnveloped;
import rs.ac.uns.ftn.xws.security.VerifyClientSignatureEnveloped;

public class ClientSignatureHandler implements LogicalHandler<LogicalMessageContext> {

	@Override
	public boolean handleMessage(LogicalMessageContext context) {
		System.out.println("\n*** Handler za digitalno potpisivanje kod Klijenta ***");

		Boolean isResponse = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		Source source = context.getMessage().getPayload();
		Document document = DocumentUtil.convertToDocument(source);

		if (!isResponse) {
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
			}
		} else {
			String cn = null;
			try {
				cn = VerifyClientSignatureEnveloped.getCommonName(document);
			} catch (XMLSecurityException e1) {
				e1.printStackTrace();
			}

			System.err.println("\nValidacija i skidanje potpisa...");

			boolean signatureValid;

			signatureValid = VerifyClientSignatureEnveloped.verifySignature(document);

			if (!signatureValid) {
				return false; // potpis nije validan
			}
			
			// uklanjanje potpisa
			Element element = (Element) document.getElementsByTagNameNS(
					"http://www.w3.org/2000/09/xmldsig#", "Signature").item(0);
			element.getParentNode().removeChild(element);
			
			try {
				DocumentUtil.printDocument(document);
			} catch (Exception e) {
			}

			CertMap.add(document, cn);

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
