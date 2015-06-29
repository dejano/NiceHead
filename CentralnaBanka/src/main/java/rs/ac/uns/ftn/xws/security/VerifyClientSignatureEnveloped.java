package rs.ac.uns.ftn.xws.security;

import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.keys.KeyInfo;
import org.apache.xml.security.keys.keyresolver.implementations.RSAKeyValueResolver;
import org.apache.xml.security.keys.keyresolver.implementations.X509CertificateResolver;
import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.signature.XMLSignatureException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import rs.ac.uns.ftn.xws.misc.DocumentUtil;

//Vrsi proveru potpisa
public class VerifyClientSignatureEnveloped {
	
	static {
		Security.addProvider(new BouncyCastleProvider());
		org.apache.xml.security.Init.init();
	}

	public static String getCommonName(Document doc) throws XMLSecurityException {
		String ret = null;

		NodeList signatures = doc.getElementsByTagNameNS("http://www.w3.org/2000/09/xmldsig#",
				"Signature");
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
			if (keyInfo.containsX509Data() && keyInfo.itemX509Data(0).containsCertificate()) {
				X509Certificate cert = keyInfo.itemX509Data(0).itemCertificate(0)
						.getX509Certificate();
				
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

	public static Document getUnsigned(Document doc) {
		Document ret = null;

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			ret = builder.newDocument();

			Node docNode = ret.importNode(doc.getFirstChild(), true);

			ret.appendChild(docNode);

			Element element = (Element) ret.getElementsByTagNameNS(
					"http://www.w3.org/2000/09/xmldsig#", "Signature").item(0);
			element.getParentNode().removeChild(element);
			
			DocumentUtil.printDocument(ret);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	public static boolean verifySignature(Document doc) {
		try {
			// Pronalazi se prvi Signature element
			NodeList signatures = doc.getElementsByTagNameNS("http://www.w3.org/2000/09/xmldsig#",
					"Signature");
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
				if (keyInfo.containsX509Data() && keyInfo.itemX509Data(0).containsCertificate()) {
					Certificate cert = keyInfo.itemX509Data(0).itemCertificate(0)
							.getX509Certificate();

					// ako postoji sertifikat, provera potpisa
					if (cert != null)
						return signature.checkSignatureValue((X509Certificate) cert);
					// TODO @bandjur proveri iz CRListe CrlDataDao za company
					// sertifikate
					// else if (){
					// X509Certificate x509 = ((X509Certificate) cert);
					// CrlDataDao.isCrlin( x509.getSerialNumber())
					// }
					else
						return false;
				} else
					return false;
			} else
				return false;

		} catch (XMLSignatureException e) {
			e.printStackTrace();
			return false;
		} catch (XMLSecurityException e) {
			e.printStackTrace();
			return false;
		}
	}
}
