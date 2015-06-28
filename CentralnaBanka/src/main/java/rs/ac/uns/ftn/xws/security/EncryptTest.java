package rs.ac.uns.ftn.xws.security;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.security.cert.X509Certificate;

import org.apache.xml.security.encryption.EncryptedData;
import org.apache.xml.security.encryption.EncryptedKey;
import org.apache.xml.security.encryption.XMLCipher;
import org.apache.xml.security.encryption.XMLEncryptionException;
import org.apache.xml.security.keys.KeyInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import rs.ac.uns.ftn.xws.misc.CentralBankConstants;

//Generise tajni kljuc
//Kriptije sadrzaj dokumenta tajnim kljucem
//Kriptuje tajni kljuc javnim kljucem
//Kriptovani tajni kljuc se stavlja kao KeyInfo kriptovanog elementa
public class EncryptTest {
	private static final String KEY_STORE_FILE = CentralBankConstants.KEYSTORE_FILE_PATH;

	static {
		Security.addProvider(new BouncyCastleProvider());
		org.apache.xml.security.Init.init();
	}

	public static Document encryptDocument(Document doc) {
		// generise tajni kljuc
		SecretKey secretKey = generateDataEncryptionKey();
		// ucitava sertifikat za kriptovanje tajnog kljuca (svojim javnim
		// kljucem)
		// Certificate cert = readCertificate();
		// X509Certificate cert = readBankaOneCertificate();
		X509Certificate cert = readMyCertificate();
		// kriptuje se dokument
		return encrypt(doc, secretKey, cert);
	}

	private static X509Certificate readBankaOneCertificate() {

		X509Certificate cert;
		InputStream inStream;
		try {
			String path = CentralBankConstants.BANKA1_CERT_FILEPATH;
			// inStream = new FileInputStream("KEY_STORE_FILE");
			inStream = new FileInputStream(path);
			cert = X509Certificate.getInstance(inStream);
			inStream.close();
			if (cert != null) {
				return cert;
			} else {
				return null;
			}
		} catch (FileNotFoundException e) {
			return null;
		} catch (javax.security.cert.CertificateException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	private static X509Certificate readMyCertificate() {
		X509Certificate cert;
		InputStream inStream;

		try {
			String path = CentralBankConstants.CB_CERT_FILEPATH;
			// inStream = new FileInputStream("KEY_STORE_FILE");
			inStream = new FileInputStream(path);
			cert = X509Certificate.getInstance(inStream);
			inStream.close();
			if (cert != null) {
				return cert;
			} else {
				return null;
			}
		} catch (FileNotFoundException e) {
			return null;
		} catch (javax.security.cert.CertificateException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	// /**
	// * Ucitava sertifikat is KS fajla
	// * alias primer
	// */
	// private static Certificate readCertificate() {
	// try {
	// //kreiramo instancu KeyStore
	// KeyStore ks = KeyStore.getInstance("JKS", "SUN");
	// //ucitavamo podatke
	// BufferedInputStream in = new BufferedInputStream(new
	// FileInputStream(KEY_STORE_FILE));
	// ks.load(in, "primer".toCharArray());
	//
	// if(ks.isKeyEntry("primer")) {
	// Certificate cert = ks.getCertificate("primer");
	// return cert;
	//
	// }
	// else
	// return null;
	//
	// } catch (KeyStoreException e) {
	// e.printStackTrace();
	// return null;
	// } catch (NoSuchProviderException e) {
	// e.printStackTrace();
	// return null;
	// } catch (FileNotFoundException e) {
	// e.printStackTrace();
	// return null;
	// } catch (NoSuchAlgorithmException e) {
	// e.printStackTrace();
	// return null;
	// } catch (CertificateException e) {
	// e.printStackTrace();
	// return null;
	// } catch (IOException e) {
	// e.printStackTrace();
	// return null;
	// }
	// }

	/**
	 * Generise tajni kljuc
	 */
	private static SecretKey generateDataEncryptionKey() {

		try {
			// KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
			// //Triple DES
			// return keyGenerator.generateKey();
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES"); // AES
			keyGenerator.init(128);
			return keyGenerator.generateKey();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Kriptuje sadrzaj prvog elementa odsek
	 */
	// private static Document encrypt(Document doc, SecretKey key, Certificate
	// certificate) {
	private static Document encrypt(Document doc, SecretKey key,
			X509Certificate certificate) {

		try {
			// cipher za kriptovanje tajnog kljuca,
			// Koristi se Javni RSA kljuc za kriptovanje
			XMLCipher keyCipher = XMLCipher.getInstance(XMLCipher.RSA_v1dot5);
			// inicijalizacija za kriptovanje tajnog kljuca javnim RSA kljucem
			keyCipher.init(XMLCipher.WRAP_MODE, certificate.getPublicKey());
			EncryptedKey encryptedKey = keyCipher.encryptKey(doc, key);

			// cipher za kriptovanje XML-a
			// XMLCipher xmlCipher = XMLCipher.getInstance(XMLCipher.TRIPLEDES);
			XMLCipher xmlCipher = XMLCipher.getInstance(XMLCipher.AES_128);
			// inicijalizacija za kriptovanje
			xmlCipher.init(XMLCipher.ENCRYPT_MODE, key);

			// u EncryptedData elementa koji se kriptuje kao KeyInfo stavljamo
			// kriptovan tajni kljuc
			EncryptedData encryptedData = xmlCipher.getEncryptedData();
			// kreira se KeyInfo
			KeyInfo keyInfo = new KeyInfo(doc);
			keyInfo.addKeyName("Kriptovani tajni kljuc");
			// postavljamo kriptovani kljuc
			keyInfo.add(encryptedKey);
			// postavljamo KeyInfo za element koji se kriptuje
			encryptedData.setKeyInfo(keyInfo);

			// trazi se element ciji sadrzaj se kriptuje
			Element root = (Element) doc.getChildNodes().item(0);

			xmlCipher.doFinal(doc, root, true); // kriptuje sa sadrzaj

			return doc;

		} catch (XMLEncryptionException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
