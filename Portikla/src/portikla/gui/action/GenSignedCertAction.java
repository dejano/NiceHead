package portikla.gui.action;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.asn1.x500.style.IETFUtils;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;

import portikla.gui.dialog.AliasDialog;
import portikla.gui.dialog.KeyStorePasswordDialog;
import portikla.gui.dialog.NewPasswordDialog;
import portikla.gui.dialog.NewPasswordDialog.NewPasswordDialogType;
import portikla.gui.dialog.SelectCertificateDialog;
import portikla.gui.dialog.X500NameDialog;
import portikla.util.DialogUtil;

public class GenSignedCertAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	private static final String ACTION_NAME = "Generate signed certificate...";

	public GenSignedCertAction() {
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
				char[] keyStorePassword = keyStoreDialog.getPassword();

				// select one of the certificates in the key store which
				// will be used as CA certificate
				SelectCertificateDialog selectCertDialog = new SelectCertificateDialog(
						keyStore);
				selectCertDialog.setVisible(true);

				if (selectCertDialog.isResultOk()) {
					// get selected certificate and its private key from dialog
					X509Certificate caCert = selectCertDialog.getCertificate();

					// TODO @dejan get CRL url
					try {
						X500Name x500name = new JcaX509CertificateHolder(caCert)
								.getSubject();
						RDN[] rdns = x500name.getRDNs(new ASN1ObjectIdentifier(
								"2.5.4.13"));
						if (rdns != null && rdns.length > 0) {
							RDN cn = rdns[0];
							System.out.println(IETFUtils.valueToString(cn
									.getFirst().getValue()));
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
					PrivateKey caPrivateKey = selectCertDialog.getPrivateKey();
					X500Name caX500Name = new X500Name(caCert
							.getSubjectX500Principal().getName());

					// enter subject data for new certificate
					X500NameDialog dialog = new X500NameDialog(false);
					dialog.setVisible(true);

					if (dialog.isResultOk()) {
						// get subject data from dialog
						X500Name newX500Name = dialog.getX500Name();
						int daysValid = dialog.getDaysValid();
						String serialNumber = dialog.getSerialNumber();

						// enter new certificate's alias
						AliasDialog aliasDialog = new AliasDialog(keyStore);
						aliasDialog.setVisible(true);

						if (aliasDialog.isResultOk()) {
							// get new certificate's alias from dialog
							String newAlias = aliasDialog.getAlias();

							// enter new certificate's password
							NewPasswordDialog newCertPasswordDialog = new NewPasswordDialog(
									NewPasswordDialogType.CERTIFICATE);
							newCertPasswordDialog.setVisible(true);

							if (newCertPasswordDialog.isResultOk()) {
								// get new certificate's password from dialog
								char[] newPassword = newCertPasswordDialog
										.getPassword();

								int result = JOptionPane
										.showConfirmDialog(null,
												"Save new certificate to separate key store?");

								if (result == JOptionPane.YES_OPTION) {
									// get new file path
									String newKeyStorePath = DialogUtil
											.saveKeyStoreDialog(newAlias);

									if (newKeyStorePath != null) {
										// enter new key store's password
										NewPasswordDialog newKeyStorePasswordDialog = new NewPasswordDialog(
												NewPasswordDialogType.KEYSTORE);
										newKeyStorePasswordDialog
												.setVisible(true);

										if (newKeyStorePasswordDialog
												.isResultOk()) {
											// get new key store's password from
											// dialog
											char[] newKeyStorePassword = newKeyStorePasswordDialog
													.getPassword();
											KeyStore newKeyStore;

											try {
												newKeyStore = KeyStore
														.getInstance("JKS");
												newKeyStore.load(null,
														newKeyStorePassword);

												// save key store to file system
												DialogUtil.saveKeyStore(
														newKeyStore,
														newKeyStorePath,
														newKeyStorePassword,
														caX500Name,
														caPrivateKey,
														newX500Name, newAlias,
														newPassword, daysValid,
														serialNumber);
											} catch (NoSuchAlgorithmException e) {
												e.printStackTrace();
											} catch (CertificateException e) {
												e.printStackTrace();
											} catch (IOException e) {
												e.printStackTrace();
											} catch (KeyStoreException e) {
												e.printStackTrace();
											}
										}
									}
								} else if (result == JOptionPane.NO_OPTION) {
									// update key store file on file system
									DialogUtil.saveKeyStore(keyStore,
											keyStorePath, keyStorePassword,
											caX500Name, caPrivateKey,
											newX500Name, newAlias, newPassword,
											daysValid, serialNumber);
								}
							}
						}
					}
				}
			}
		}
	}

}
