package rs.ac.uns.ftn.xws.security;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.keys.KeyInfo;
import org.apache.xml.security.keys.keyresolver.implementations.RSAKeyValueResolver;
import org.apache.xml.security.keys.keyresolver.implementations.X509CertificateResolver;
import org.apache.xml.security.signature.XMLSignature;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import rs.ac.uns.ftn.xws.misc.DocumentUtil;
import rs.ac.uns.ftn.xws.ws.crl.CrlDocument_CrlDocumentPort_Client;

//Vrsi proveru potpisa
public class VerifyClientSignatureEnveloped {
	static {
		Security.addProvider(new BouncyCastleProvider());
		org.apache.xml.security.Init.init();
	}

	public static String getCommonName(Document doc)
			throws XMLSecurityException {
		String ret = null;

		NodeList signatures = doc.getElementsByTagNameNS(
				"http://www.w3.org/2000/09/xmldsig#", "Signature");
		Element signatureEl = (Element) signatures.item(0);

		// kreira se signature objekat od elementa
		XMLSignature signature = new XMLSignature(signatureEl, null);
		// preuzima se key info
		KeyInfo keyInfo = signature.getKeyInfo();
		// ako postoji
		if (keyInfo != null) {
			// registruju se resolver-i za javni kljuc i sertifikat
			keyInfo.registerInternalKeyResolver(new RSAKeyValueResolver());
			keyInfo.registerInternalKeyResolver(new X509CertificateResolver());

			// ako sadrzi sertifikat
			if (keyInfo.containsX509Data()
					&& keyInfo.itemX509Data(0).containsCertificate()) {
				X509Certificate cert = keyInfo.itemX509Data(0)
						.itemCertificate(0).getX509Certificate();

				String dn = cert.getSubjectX500Principal().getName();
				String[] split = dn.split(",");
				for (String x : split) {
					if (x.contains("CN=")) {
						ret = x.trim().substring(3);
					}
				}
			}
		}

		return ret;
	}

	public static Document getUnsigned(Document doc) throws Exception {
		Document ret = null;

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		ret = builder.newDocument();

		Node docNode = ret.importNode(doc.getFirstChild(), true);

		ret.appendChild(docNode);

		Element element = (Element) ret.getElementsByTagNameNS(
				"http://www.w3.org/2000/09/xmldsig#", "Signature").item(0);
		element.getParentNode().removeChild(element);

		DocumentUtil.printDocument(ret);

		return ret;
	}

	public static boolean verifySignature(Document doc)
			throws XMLSecurityException, MalformedURLException {
		boolean retBool = false;

		// Pronalazi se prvi Signature element
		NodeList signatures = doc.getElementsByTagNameNS(
				"http://www.w3.org/2000/09/xmldsig#", "Signature");
		Element signatureEl = (Element) signatures.item(0);

		// kreira se signature objekat od elementa
		XMLSignature signature = new XMLSignature(signatureEl, null);
		// preuzima se key info
		KeyInfo keyInfo = signature.getKeyInfo();
		// ako postoji
		if (keyInfo != null) {
			// registruju se resolver-i za javni kljuc i sertifikat
			keyInfo.registerInternalKeyResolver(new RSAKeyValueResolver());
			keyInfo.registerInternalKeyResolver(new X509CertificateResolver());

			// ako sadrzi sertifikat
			if (keyInfo.containsX509Data()
					&& keyInfo.itemX509Data(0).containsCertificate()) {
				Certificate cert = keyInfo.itemX509Data(0).itemCertificate(0)
						.getX509Certificate();
				BigInteger certSerialNumber = ((X509Certificate) cert)
						.getSerialNumber();
				// ako postoji sertifikat, provera potpisa
				if (cert != null) {
//					if (CrlDocument_CrlDocumentPort_Client
//							.isCertificateRevoked(certSerialNumber))
//						retBool = false;// return false;
//					else 
						if (signature
							.checkSignatureValue((X509Certificate) cert))
						retBool = true;// return true;
				}
			}
		}
		return retBool;
	}
}
