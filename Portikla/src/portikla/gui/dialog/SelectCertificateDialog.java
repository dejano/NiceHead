package portikla.gui.dialog;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

public class SelectCertificateDialog extends AbstractDialog {

	private static final long serialVersionUID = 1L;

	private static final String TITLE = "Certificate password";

	private static final String EMPTY_PASSWORD_MESSAGE = "Password cannot be empty!";
	private static final String WRONG_PASSWORD_MESSAGE = "Wrong password!";

	private JPasswordField tfPassword;
	private JComboBox<String> cmbAlias;

	private KeyStore keyStore;

	private PrivateKey key;

	public SelectCertificateDialog(KeyStore keyStore) {
		this.keyStore = keyStore;

		this.setTitle(TITLE);
		this.setResizable(false);
		this.setSize(330, 142);
		this.setModal(true);
		this.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(64, 52, 81, 14);
		panel.add(lblPassword);

		tfPassword = new JPasswordField();
		tfPassword.setBounds(155, 49, 159, 20);
		panel.add(tfPassword);
		tfPassword.setColumns(10);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent paramActionEvent) {
				if (tfPassword.getPassword().length == 0) {
					JOptionPane.showMessageDialog(SelectCertificateDialog.this,
							EMPTY_PASSWORD_MESSAGE, "Warning!",
							JOptionPane.WARNING_MESSAGE);
				} else {
					boolean passwordOk = true;

					try {
						key = (PrivateKey) keyStore.getKey(
								(String) cmbAlias.getSelectedItem(),
								tfPassword.getPassword());
					} catch (UnrecoverableKeyException e) {
						passwordOk = false;
					} catch (KeyStoreException e) {
						e.printStackTrace();
					} catch (NoSuchAlgorithmException e) {
						e.printStackTrace();
					}

					if (!passwordOk) {
						JOptionPane.showMessageDialog(
								SelectCertificateDialog.this,
								WRONG_PASSWORD_MESSAGE, "Warning!",
								JOptionPane.WARNING_MESSAGE);
						tfPassword.setText("");
					} else {
						resultOk = true;
						SelectCertificateDialog.this.setVisible(false);
					}
				}
			}
		});
		btnOk.setBounds(152, 80, 71, 23);
		panel.add(btnOk);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent paramActionEvent) {
				resultOk = false;
				SelectCertificateDialog.this.setVisible(false);
			}
		});
		btnCancel.setBounds(233, 80, 81, 23);
		panel.add(btnCancel);

		ArrayList<String> aliases = null;
		try {
			aliases = Collections.list(keyStore.aliases());
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
		cmbAlias = new JComboBox<String>(aliases.toArray(new String[aliases
				.size()]));
		cmbAlias.setBounds(155, 11, 159, 20);
		panel.add(cmbAlias);

		JLabel lblCertificateAlias = new JLabel("Certificate alias :");
		lblCertificateAlias.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCertificateAlias.setBounds(40, 14, 105, 14);
		panel.add(lblCertificateAlias);
	}

	public PrivateKey getPrivateKey() {
		return this.key;
	}

	public X509Certificate getCertificate() {
		X509Certificate cert = null;

		try {
			cert = (X509Certificate) keyStore.getCertificate((String) cmbAlias
					.getSelectedItem());
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}

		return cert;
	}

	public String getSelectedCertificateAlias() {
		return (String) cmbAlias.getSelectedItem();
	}
}
