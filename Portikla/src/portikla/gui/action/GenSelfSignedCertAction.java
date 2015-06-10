package portikla.gui.action;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.bouncycastle.asn1.x500.X500Name;

import portikla.gui.dialog.AliasDialog;
import portikla.gui.dialog.NewPasswordDialog;
import portikla.gui.dialog.X500NameDialog;
import portikla.gui.dialog.NewPasswordDialog.NewPasswordDialogType;
import portikla.security.CertificateGenerator;
import portikla.security.KeyStoreWriter;

public class GenSelfSignedCertAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	private static final String ACTION_NAME = "Generate self-signed certificate...";

	private static final String FILENAME_FILTER_DESCRIPTION = "KeyStore(*.jks)";
	private static final String FILENAME_FILTER_EXTENSIONS = "jks";

	public GenSelfSignedCertAction() {
		this.putValue(NAME, ACTION_NAME);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		X500NameDialog dialog = new X500NameDialog(true);
		dialog.setVisible(true);

		if (dialog.isResultOk()) {
			X500Name x500Name = dialog.getX500Name();
			int daysValid = dialog.getDaysValid();
			String serialNumber = dialog.getSerialNumber();

			if (x500Name != null) {
				String alias = getAlias();

				if (alias != null) {
					char[] certPassword = getNewPassword(NewPasswordDialogType.CERTIFICATE);

					if (certPassword != null) {
						char[] ksPassword = getNewPassword(NewPasswordDialogType.KEYSTORE);

						if (ksPassword != null) {
							saveKeyStore(ksPassword, x500Name, alias,
									certPassword, daysValid, serialNumber);
						}
					}
				}
			}
		}

	}

	private String getAlias() {
		AliasDialog dialog = new AliasDialog();
		dialog.setVisible(true);

		return dialog.isResultOk() ? dialog.getAlias() : null;
	}

	private char[] getNewPassword(NewPasswordDialogType type) {
		NewPasswordDialog dialog = new NewPasswordDialog(type);
		dialog.setVisible(true);

		return dialog.isResultOk() ? dialog.getPassword() : null;
	}

	private void saveKeyStore(char[] ksPassword, X500Name x500Name,
			String alias, char[] certPassword, int daysValid,
			String serialNumber) {
		CertificateGenerator cg = new CertificateGenerator();

		KeyPair keyPair = cg.generateKeyPair();

		X509Certificate cert = cg.generateSelfSignedCertificate(x500Name,
				keyPair, daysValid, serialNumber);

		JFileChooser saveDialog = new JFileChooser();
		saveDialog.setSelectedFile(new File(alias + "."
				+ FILENAME_FILTER_EXTENSIONS));
		saveDialog.setFileFilter(new FileNameExtensionFilter(
				FILENAME_FILTER_DESCRIPTION, FILENAME_FILTER_EXTENSIONS));

		int saveDialogRetVal = saveDialog.showSaveDialog(null);

		if (saveDialogRetVal == JFileChooser.APPROVE_OPTION) {
			KeyStore keyStore;
			KeyStoreWriter keyStoreWriter = new KeyStoreWriter();

			File file = saveDialog.getSelectedFile();
			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			try {
				keyStore = KeyStore.getInstance("JKS");
				keyStore.load(null, ksPassword);

				keyStoreWriter.write(keyStore, file.getPath(), ksPassword,
						alias, keyPair.getPrivate(), certPassword, cert);
			} catch (KeyStoreException e1) {
				e1.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (CertificateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
