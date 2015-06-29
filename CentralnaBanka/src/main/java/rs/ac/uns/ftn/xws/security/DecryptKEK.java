package rs.ac.uns.ftn.xws.security;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
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
import java.util.logging.Logger;

import org.apache.xml.security.encryption.XMLCipher;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import rs.ac.uns.ftn.xws.misc.CentralBankConstants;

//Dekriptuje tajni kljuc privatnim kljucem
//Tajnim kljucem dekriptuje podatke
public class DecryptKEK {

	private static final Logger LOG = Logger.getLogger(DecryptKEK.class
			.getName());

	private static final String KEY_STORE_FILE = CentralBankConstants.KEYSTORE_FILE_PATH;

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
	 * @throws NoSuchProviderException 
	 * @throws KeyStoreException 
	 * @throws NoSuchAlgorithmException 
	 * @throws UnrecoverableKeyException 
	 * @throws IOException 
	 * @throws CertificateException 
	 */
	private static PrivateKey readPrivateKey() throws KeyStoreException, NoSuchProviderException, UnrecoverableKeyException, NoSuchAlgorithmException, CertificateException, IOException {
			// kreiramo instancu KeyStore\
		
			KeyStore ks = KeyStore.getInstance("JKS", "SUN");
			// ucitavamo podatke
			BufferedInputStream in = new BufferedInputStream(
					new FileInputStream(KEY_STORE_FILE));
			

			String cbAlias = ResourceBundle.getBundle(
					CentralBankConstants.PROP_FILE_PATH).getString("cb.alias");
			String keyStorePassword = ResourceBundle.getBundle(
					CentralBankConstants.PROP_FILE_PATH).getString(
					"cb.password");

			LOG.info(cbAlias);
			LOG.info(keyStorePassword);

			ks.load(in, keyStorePassword.toCharArray());

			if (ks.isKeyEntry(cbAlias)) {
				PrivateKey pk = (PrivateKey) ks.getKey(cbAlias,
						keyStorePassword.toCharArray());
				LOG.info(pk.getAlgorithm());
				return pk;
			} else
				return null;

	}

	/**
	 * Kriptuje sadrzaj prvog elementa odsek
	 * @throws Exception 
	 */
	private static Document decrypt(Document doc, PrivateKey privateKey) throws Exception {

			// cipher za dekritpovanje XML-a
			XMLCipher xmlCipher = XMLCipher.getInstance();
			// inicijalizacija za dekriptovanje
			xmlCipher.init(XMLCipher.DECRYPT_MODE, null);
			// postavlja se kljuc za dekriptovanje tajnog kljuca
			xmlCipher.setKEK(privateKey);

			// trazi se prvi EncryptedData element
			NodeList encDataList = doc.getElementsByTagNameNS(
					"http://www.w3.org/2001/04/xmlenc#", "EncryptedData");
			Element encData = (Element) encDataList.item(0);

			// dekriptuje se
			// pri cemu se prvo dekriptuje tajni kljuc, pa onda njime podaci
			xmlCipher.doFinal(doc, encData);

			return doc;
	}
}
