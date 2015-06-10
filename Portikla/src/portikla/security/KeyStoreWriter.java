package portikla.security;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;

public class KeyStoreWriter {

	public void write(KeyStore keyStore, String keyStoreFilename,
			char[] keyStorePassword, String alias, PrivateKey privateKey,
			char[] password, Certificate certificate) {
		try {
			keyStore.setKeyEntry(alias, privateKey, password,
					new Certificate[] { certificate });
			keyStore.store(new FileOutputStream(keyStoreFilename), keyStorePassword);
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}