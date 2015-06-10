package portikla.security;

import java.io.FileWriter;
import java.io.IOException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;

import org.bouncycastle.openssl.PEMWriter;

import portikla.util.Base64;
import sun.security.provider.X509Factory;

public class CertificateWriter {

	public void writeToBase64EncFile(String path, X509Certificate certificate) {
		try {
			FileWriter fw = new FileWriter(path);

			char[] encoded = Base64.encode(certificate.getEncoded());

			fw.write(X509Factory.BEGIN_CERT);
			fw.write(encoded);
			fw.write(X509Factory.END_CERT);
			fw.close();
		} catch (CertificateEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writePemFile(String path, X509Certificate certificate) {
		try {
			PEMWriter pw = new PEMWriter(new FileWriter(path));
			pw.writeObject(certificate);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
