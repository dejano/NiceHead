package rs.ac.uns.ftn.xws.handler.ws;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.ws.handler.LogicalHandler;
import javax.xml.ws.handler.LogicalMessageContext;
import javax.xml.ws.handler.MessageContext;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import rs.ac.uns.ftn.xws.misc.CertMap;
import rs.ac.uns.ftn.xws.misc.DocumentUtil;
import rs.ac.uns.ftn.xws.misc.SecWrapper;
import rs.ac.uns.ftn.xws.security.SignEnveloped;
import rs.ac.uns.ftn.xws.security.VerifyClientSignatureEnveloped;

public class WSSignatureHandler implements
		LogicalHandler<LogicalMessageContext> {

	private static final Logger LOG = Logger.getLogger(WSCryptoHandler.class
			.getName());

	@Override
	public boolean handleMessage(LogicalMessageContext context) {
		System.out
				.println("\n*** Handler za digitalno potpisivanje kod Web Servisa ***");

		Boolean isResponse = (Boolean) context
				.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		Source source = context.getMessage().getPayload();

		Document document;
		try {
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
				DocumentUtil.printDocument(signedDocument);
				context.getMessage().setPayload(signedSource);
			} catch (Exception e) {
				LOG.log(Level.SEVERE, e.getMessage(), e);
			}
		} else {
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
				// TODO bandjur certmap
				CertMap.add(SecWrapper.unwrap(document),
						VerifyClientSignatureEnveloped.getCommonName(document));
			} catch (Exception e) {
			}

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
