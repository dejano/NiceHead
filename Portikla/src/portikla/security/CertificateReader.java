package portikla.security;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Collection;
import java.util.Iterator;

public class CertificateReader {
	public void readFromBase64EncFile(String path) {
		try {
			FileInputStream fis = new FileInputStream(path);
			BufferedInputStream bis = new BufferedInputStream(fis);

			CertificateFactory cf = CertificateFactory.getInstance("X.509");

			while (bis.available() > 0) {
				Certificate cert = cf.generateCertificate(bis);
				System.out.println(cert);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readFromBinEncFile(String path) {
		try {
			FileInputStream fis = new FileInputStream(path);
			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			Collection<?> c = cf.generateCertificates(fis);
			Iterator<?> i = c.iterator();
			while (i.hasNext()) {
				Certificate cert = (Certificate) i.next();
				System.out.println(cert);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		}
	}
}
