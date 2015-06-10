package portikla.util;

import java.io.File;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.bouncycastle.asn1.x500.X500Name;

import portikla.security.CertificateGenerator;
import portikla.security.KeyStoreWriter;

public class DialogUtil {

	private static final String CERTIFICATE_FILENAME_FILTER_DESCRIPTION = "Certificate(*.cer)";
	private static final String CERTIFICATE_FILENAME_FILTER_EXTENSIONS = "cer";
	

	private static final String KEY_STORE_FILENAME_FILTER_DESCRIPTION = "KeyStore(*.jks)";
	private static final String KEY_STORE_FILENAME_FILTER_EXTENSIONS = "jks";
	
	public static String openKeyStoreDialog() {
		String path = null;

		JFileChooser openDialog = new JFileChooser();
		openDialog.setFileFilter(new FileNameExtensionFilter(
				KEY_STORE_FILENAME_FILTER_DESCRIPTION, KEY_STORE_FILENAME_FILTER_EXTENSIONS));

		int openDialogRetVal = openDialog.showOpenDialog(null);

		if (openDialogRetVal == JFileChooser.APPROVE_OPTION) {
			File file = openDialog.getSelectedFile();
			path = file.getPath();
		}

		return path;
	}

	public static String saveKeyStoreDialog(String alias) {
		String path = null;
		
		JFileChooser saveDialog = new JFileChooser();
		saveDialog.setSelectedFile(new File(alias + "."
				+ KEY_STORE_FILENAME_FILTER_EXTENSIONS));
		saveDialog.setFileFilter(new FileNameExtensionFilter(
				KEY_STORE_FILENAME_FILTER_DESCRIPTION, KEY_STORE_FILENAME_FILTER_EXTENSIONS));

		int saveDialogRetVal = saveDialog.showSaveDialog(null);

		File file = null;
		if (saveDialogRetVal == JFileChooser.APPROVE_OPTION) {
			file = saveDialog.getSelectedFile();
			
			path = file.getPath();
			
			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return path;
	}
	
	public static String saveCertificateDialog(String alias) {
		JFileChooser saveDialog = new JFileChooser();
		saveDialog.setSelectedFile(new File(alias + "."
				+ CERTIFICATE_FILENAME_FILTER_EXTENSIONS));
		saveDialog.setFileFilter(new FileNameExtensionFilter(
				CERTIFICATE_FILENAME_FILTER_DESCRIPTION, CERTIFICATE_FILENAME_FILTER_EXTENSIONS));

		int saveDialogRetVal = saveDialog.showSaveDialog(null);

		File file = null;
		if (saveDialogRetVal == JFileChooser.APPROVE_OPTION) {
			file = saveDialog.getSelectedFile();
			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return file.getPath();
	}

	public static void saveKeyStore(KeyStore keyStore, String keyStorePath,
			char[] keyStorePassword, X500Name issuerX500Name,
			PrivateKey caPrivateKey, X500Name subjectX500Name, String alias,
			char[] password, int daysValid, String serialNumber) {
		CertificateGenerator cg = new CertificateGenerator();

		KeyPair newKeyPair = cg.generateKeyPair();

		X509Certificate cert = cg.generateSignedCertificate(issuerX500Name,
				caPrivateKey, subjectX500Name, newKeyPair, daysValid, serialNumber);

		KeyStoreWriter keyStoreWriter = new KeyStoreWriter();

		keyStoreWriter.write(keyStore, keyStorePath, keyStorePassword, alias,
				newKeyPair.getPrivate(), password, cert);
	}
	
	private DialogUtil() {
	}
}
