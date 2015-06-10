package portikla.gui.dialog;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.KeyStore;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import portikla.security.KeyStoreReader;

public class KeyStorePasswordDialog extends AbstractDialog {

	private static final long serialVersionUID = 1L;

	private static final String TITLE = "Key store password";

	private static final String EMPTY_PASSWORD_MESSAGE = "Password cannot be empty!";

	protected static final Object WRONG_PASSWORD_MESSAGE = "Wrong password!";

	private JPasswordField tfPassword;

	private String path;

	private KeyStore keyStore;

	public KeyStorePasswordDialog(String path) {
		this.path = path;

		this.setTitle(TITLE);
		this.setResizable(false);
		this.setSize(277, 105);
		this.setModal(true);
		this.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(10, 14, 81, 14);
		panel.add(lblPassword);

		tfPassword = new JPasswordField();
		tfPassword.setBounds(101, 11, 159, 20);
		panel.add(tfPassword);
		tfPassword.setColumns(10);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent paramActionEvent) {
				if (tfPassword.getPassword().length == 0) {
					JOptionPane.showMessageDialog(KeyStorePasswordDialog.this,
							EMPTY_PASSWORD_MESSAGE, "Warning!",
							JOptionPane.WARNING_MESSAGE);
				} else {
					boolean passwordOk = false;

					KeyStoreReader reader = new KeyStoreReader();
					keyStore = reader.readKeyStore(
							KeyStorePasswordDialog.this.path,
							tfPassword.getPassword());

					if (keyStore != null)
						passwordOk = true;
					else
						JOptionPane.showMessageDialog(null,
								WRONG_PASSWORD_MESSAGE, "Warning!",
								JOptionPane.WARNING_MESSAGE);

					if (passwordOk) {
						resultOk = true;
						KeyStorePasswordDialog.this.setVisible(false);
					}
				}
			}
		});
		btnOk.setBounds(98, 42, 71, 23);
		panel.add(btnOk);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent paramActionEvent) {
				resultOk = false;
				KeyStorePasswordDialog.this.setVisible(false);
			}
		});
		btnCancel.setBounds(179, 42, 81, 23);
		panel.add(btnCancel);
	}

	public KeyStore getKeyStore() {
		return this.keyStore;
	}
	
	public char[] getPassword(){
		return tfPassword.getPassword();
	}
}
