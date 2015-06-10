package portikla.security;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;

public class KeyStoreReader {

	public KeyStore readKeyStore(String path, char[] password) {
		KeyStore ks = null;

		try {
			ks = KeyStore.getInstance("JKS", "SUN");
			BufferedInputStream in;
			in = new BufferedInputStream(new FileInputStream(path));
			ks.load(in, password);
		} catch (NoSuchAlgorithmException | CertificateException
				| KeyStoreException | NoSuchProviderException | IOException e) {
			ks = null;
			e.printStackTrace();
		}

		return ks;
	}
}
