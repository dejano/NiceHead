package portikla.gui.dialog;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import org.bouncycastle.util.Arrays;

public class NewPasswordDialog extends AbstractDialog {

	private static final long serialVersionUID = 1L;

	public enum NewPasswordDialogType{
		CERTIFICATE, KEYSTORE
	}
	
	private static final String CERT_PASSWORD_DIALOG_TITLE = "Certificate password";
	private static final String KEYSTORE_PASSWORD_DIALOG_TITLE = "Key store password";
	
	private static final String PASSWORDS_NOT_EQUAL_MESSAGE = "Passwords not equal!";
	private static final String EMPTY_PASSWORD_MESSAGE = "Password cannot be empty!";

	private JPasswordField tfPassword1;
	private JPasswordField tfPassword2;

	public NewPasswordDialog(NewPasswordDialogType type) {
		String title = null;
		
		switch(type){
		case CERTIFICATE:
			title = CERT_PASSWORD_DIALOG_TITLE;
			break;
		case KEYSTORE:
			title = KEYSTORE_PASSWORD_DIALOG_TITLE;
			break;
		}
		
		this.setTitle(title);
		
		this.setResizable(false);
		this.setSize(307, 132);
		this.setModal(true);
		this.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(10, 17, 110, 14);
		panel.add(lblPassword);

		tfPassword1 = new JPasswordField();
		tfPassword1.setBounds(130, 14, 159, 20);
		panel.add(tfPassword1);
		tfPassword1.setColumns(10);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent paramActionEvent) {
				if (tfPassword1.getPassword().length == 0) {
					JOptionPane.showMessageDialog(NewPasswordDialog.this,
							EMPTY_PASSWORD_MESSAGE, "Warning!",
							JOptionPane.WARNING_MESSAGE);
				} else if (!Arrays.areEqual(tfPassword1.getPassword(),
						tfPassword2.getPassword())) {
					JOptionPane.showMessageDialog(NewPasswordDialog.this,
							PASSWORDS_NOT_EQUAL_MESSAGE, "Warning!",
							JOptionPane.WARNING_MESSAGE);
				} else {
					resultOk = true;
					NewPasswordDialog.this.setVisible(false);
				}
			}
		});
		btnOk.setBounds(127, 73, 71, 23);
		panel.add(btnOk);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent paramActionEvent) {
				resultOk = false;
				NewPasswordDialog.this.setVisible(false);
			}
		});
		btnCancel.setBounds(208, 73, 81, 23);
		panel.add(btnCancel);

		JLabel lblRepeatPassword = new JLabel("Repeat password :");
		lblRepeatPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRepeatPassword.setBounds(10, 45, 110, 14);
		panel.add(lblRepeatPassword);

		tfPassword2 = new JPasswordField();
		tfPassword2.setColumns(10);
		tfPassword2.setBounds(130, 42, 159, 20);
		panel.add(tfPassword2);
	}

	public char[] getPassword() {
		return tfPassword1.getPassword();
	}
}
