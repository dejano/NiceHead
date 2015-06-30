package rs.ac.uns.ftn.xmlbsep.security.crypto;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Security;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ResourceBundle;

import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.signature.XMLSignatureException;
import org.apache.xml.security.transforms.TransformationException;
import org.apache.xml.security.transforms.Transforms;
import org.apache.xml.security.utils.Constants;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import rs.ac.uns.ftn.xmlbsep.util.CompanyConstants;


//Potpisuje dokument, koristi se enveloped tip
public class SignEnveloped {
	private static final String KEY_STORE_FILE = CompanyConstants.KEYSTORE_FILE_PATH;

	static {
		Security.addProvider(new BouncyCastleProvider());
		org.apache.xml.security.Init.init();
	}

	public static Document signDocument(Document doc) throws UnrecoverableKeyException,
			KeyStoreException, NoSuchProviderException, NoSuchAlgorithmException,
			CertificateException, IOException, XMLSecurityException {
		// ucitava privatni kljuc
		PrivateKey pk = readPrivateKey();
		// ucitava sertifikat
		Certificate cert = readCertificate();
		// potpisuje
		return signDocument(doc, pk, cert);
	}

	/**
	 * Ucitava sertifikat is KS fajla alias primer
	 * 
	 * @throws java.security.NoSuchProviderException
	 * @throws java.security.KeyStoreException
	 * @throws java.io.IOException
	 * @throws java.security.cert.CertificateException
	 * @throws java.security.NoSuchAlgorithmException
	 */
	private static Certificate readCertificate() throws KeyStoreException, NoSuchProviderException,
			NoSuchAlgorithmException, CertificateException, IOException {
		// kreiramo instancu KeyStore
		KeyStore ks = KeyStore.getInstance("JKS", "SUN");
		// ucitavamo podatke
		BufferedInputStream in = new BufferedInputStream(SignEnveloped.class.getResourceAsStream(CompanyConstants.KEYSTORE_FILE_PATH));

		String bankAlias = ResourceBundle.getBundle(CompanyConstants.PROP_FILE_PATH).getString(
				"company.alias");
		String keyStorePassword = ResourceBundle.getBundle(CompanyConstants.PROP_FILE_PATH).getString(
				"company.password");

		ks.load(in, keyStorePassword.toCharArray());

		if (ks.isKeyEntry(bankAlias)) {
			Certificate cert = ks.getCertificate(bankAlias);
			return cert;
		} else
			return null;

	}

	/**
	 * Ucitava privatni kljuc is KS fajla alias primer
	 * 
	 * @throws java.security.NoSuchProviderException
	 * @throws java.security.KeyStoreException
	 * @throws java.io.IOException
	 * @throws java.security.cert.CertificateException
	 * @throws java.security.NoSuchAlgorithmException
	 * @throws java.security.UnrecoverableKeyException
	 */
	private static PrivateKey readPrivateKey() throws KeyStoreException, NoSuchProviderException,
			NoSuchAlgorithmException, CertificateException, IOException, UnrecoverableKeyException {
		// kreiramo instancu KeyStore
		KeyStore ks = KeyStore.getInstance("JKS", "SUN");
		// ucitavamo podatke
		BufferedInputStream in = new BufferedInputStream(SignEnveloped.class.getResourceAsStream(CompanyConstants.KEYSTORE_FILE_PATH));

		String bankAlias = ResourceBundle.getBundle(CompanyConstants.PROP_FILE_PATH).getString(
				"company.alias");
		String keyStorePassword = ResourceBundle.getBundle(CompanyConstants.PROP_FILE_PATH).getString(
				"company.password");

		ks.load(in, keyStorePassword.toCharArray());

		if (ks.isKeyEntry(bankAlias)) {
			PrivateKey pk = (PrivateKey) ks.getKey(bankAlias, keyStorePassword.toCharArray());
			return pk;
		} else
			return null;
	}

	private static Document signDocument(Document doc, PrivateKey privateKey, Certificate cert)
			throws XMLSecurityException {
		Element rootEl = doc.getDocumentElement();

		// kreira se signature objekat
		XMLSignature sig = new XMLSignature(doc, null, XMLSignature.ALGO_ID_SIGNATURE_RSA_SHA1);
		// kreiraju se transformacije nad dokumentom
		Transforms transforms = new Transforms(doc);

		// iz potpisa uklanja Signature element
		// Ovo je potrebno za enveloped tip po specifikaciji
		transforms.addTransform(Transforms.TRANSFORM_ENVELOPED_SIGNATURE);
		// normalizacija
		transforms.addTransform(Transforms.TRANSFORM_C14N_WITH_COMMENTS);

		// potpisuje se citav dokument (URI "")
		sig.addDocument("", transforms, Constants.ALGO_ID_DIGEST_SHA1);

		// U KeyInfo se postavalja Javni kljuc samostalno i citav sertifikat
		sig.addKeyInfo(cert.getPublicKey());
		sig.addKeyInfo((X509Certificate) cert);

		// poptis je child root elementa
		rootEl.appendChild(sig.getElement());

		// potpisivanje
		sig.sign(privateKey);

		return doc;
	}
}
