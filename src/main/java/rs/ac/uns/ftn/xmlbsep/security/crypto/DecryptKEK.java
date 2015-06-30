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
import java.security.cert.CertificateException;
import java.util.ResourceBundle;

import org.apache.xml.security.encryption.XMLCipher;
import org.apache.xml.security.encryption.XMLEncryptionException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import rs.ac.uns.ftn.xmlbsep.util.CompanyConstants;


//Dekriptuje tajni kljuc privatnim kljucem
//Tajnim kljucem dekriptuje podatke
public class DecryptKEK {
	// private static final String KEY_STORE_FILE = "./data/primer.jks";
	private static final String KEY_STORE_FILE = CompanyConstants.KEYSTORE_FILE_PATH;

	static {
		Security.addProvider(new BouncyCastleProvider());
		org.apache.xml.security.Init.init();
	}

	public static Document decryptDocument(Document doc) throws Exception {
		// ucitava se privatni kljuc
		PrivateKey pk = readPrivateKey();
		// dekriptuje se dokument
		return decrypt(doc, pk);
	}

	/**
	 * Ucitava privatni kljuc is KS fajla alias primer
	 * 
	 * @throws java.io.IOException
	 * @throws java.security.cert.CertificateException
	 * @throws Exception
	 */
	private static PrivateKey readPrivateKey() throws Exception {
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

	/**
	 * Kriptuje sadrzaj prvog elementa odsek
	 * 
	 * @throws Exception
	 */
	private static Document decrypt(Document doc, PrivateKey privateKey) throws Exception {
		XMLCipher xmlCipher = XMLCipher.getInstance();
		// inicijalizacija za dekriptovanje
		xmlCipher.init(XMLCipher.DECRYPT_MODE, null);
		// postavlja se kljuc za dekriptovanje tajnog kljuca
		xmlCipher.setKEK(privateKey);

		// trazi se prvi EncryptedData element
		NodeList encDataList = doc.getElementsByTagNameNS("http://www.w3.org/2001/04/xmlenc#",
				"EncryptedData");
		Element encData = (Element) encDataList.item(0);

		// dekriptuje se
		// pri cemu se prvo dekriptuje tajni kljuc, pa onda njime podaci
		xmlCipher.doFinal(doc, encData);

		return doc;
	}
}
