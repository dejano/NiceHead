package rs.ac.uns.ftn.xws.handler.ws;

import java.util.logging.Level;
import java.util.logging.Logger;

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

public class WSSignatureHandler implements LogicalHandler<LogicalMessageContext> {

	private static final Logger LOG = Logger.getLogger(WSCryptoHandler.class.getName());

	@Override
	public boolean handleMessage(LogicalMessageContext context) {
		LOG.info("\n*** Handler za digitalno potpisivanje kod Web Servisa ***");

		Boolean isResponse = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		Source source = context.getMessage().getPayload();
		Document document = DocumentUtil.convertToDocument(source);

		if (isResponse) {
			LOG.info("\nDokument koji je stigao");
			try {
				DocumentUtil.printDocument(document);
			} catch (Exception e) {
			}
			LOG.info("\n-- Potpisivanje --");

			try {
				Document signedDocument = SignEnveloped.signDocument(document);
				Source signedSource = new DOMSource(signedDocument);
				context.getMessage().setPayload(signedSource);
				DocumentUtil.printDocument(signedDocument);
			} catch (Exception e) {
				LOG.log(Level.SEVERE, e.getMessage(), e);
			}
		} else {
			LOG.info("\nValidacija i skidanje potpisa...");

			boolean signatureValid;
			signatureValid = VerifyClientSignatureEnveloped.verifySignature(document);

			if (!signatureValid) {
				return false; // potpis nije validan
			}

			try {
				String cn = VerifyClientSignatureEnveloped.getCommonName(document);
				LOG.info("common name" + cn);

				// uklanjanje potpisa
				Element element = (Element) document.getElementsByTagNameNS(
						"http://www.w3.org/2000/09/xmldsig#", "Signature").item(0);
				element.getParentNode().removeChild(element);

				LOG.info("" + DocumentUtil.getHashCode(document));
				CertMap.add(document, cn);
			} catch (XMLSecurityException e1) {
				LOG.log(Level.SEVERE, e1.getMessage(), e1);
			}

			try {
				DocumentUtil.printDocument(document);

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
