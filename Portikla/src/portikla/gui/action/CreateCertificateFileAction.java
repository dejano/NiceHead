package portikla.gui.action;

import java.awt.event.ActionEvent;
import java.security.KeyStore;
import java.security.cert.X509Certificate;

import javax.swing.AbstractAction;

import portikla.gui.dialog.KeyStorePasswordDialog;
import portikla.gui.dialog.SelectCertificateDialog;
import portikla.security.CertificateWriter;
import portikla.util.DialogUtil;

public class CreateCertificateFileAction extends AbstractAction {
	private static final long serialVersionUID = 1L;

	private static final String ACTION_NAME = "Create certificate file...";

	public CreateCertificateFileAction() {
		this.putValue(NAME, ACTION_NAME);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// select key store from file system and get its file path
		String keyStorePath = DialogUtil.openKeyStoreDialog();

		if (keyStorePath != null) {
			// enter selected key store password
			KeyStorePasswordDialog keyStoreDialog = new KeyStorePasswordDialog(
					keyStorePath);
			keyStoreDialog.setVisible(true);

			if (keyStoreDialog.isResultOk()) {
				// get key store and key store password
				KeyStore keyStore = keyStoreDialog.getKeyStore();

				// select one of the certificates in the key store
				SelectCertificateDialog selectCertDialog = new SelectCertificateDialog(
						keyStore);
				selectCertDialog.setVisible(true);

				if (selectCertDialog.isResultOk()) {
					// get selected certificate from dialog
					X509Certificate cert = selectCertDialog.getCertificate();

					// get path to new file
					String newCertPath = DialogUtil
							.saveCertificateDialog(selectCertDialog
									.getSelectedCertificateAlias());

					if (newCertPath != null) {
						// write the certificate as a file
						CertificateWriter cw = new CertificateWriter();
						cw.writePemFile(newCertPath, cert);
					}
				}
			}
		}
	}
}
