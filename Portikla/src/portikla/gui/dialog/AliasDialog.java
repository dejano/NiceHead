package portikla.gui.dialog;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.KeyStore;
import java.security.KeyStoreException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AliasDialog extends AbstractDialog {

	private static final long serialVersionUID = 1L;

	private static final String EMPTY_ALIAS_MESSAGE = "Alias cannot be empty!";

	protected static final Object USED_ALIAS_MESSAGE = "Alias already used!";

	private JTextField tfAlias;

	private KeyStore keyStore;

	public AliasDialog(KeyStore keyStore) {
		this();

		this.keyStore = keyStore;
	}

	public AliasDialog() {
		this.setTitle("Set certificate alias");
		this.setResizable(false);
		this.setSize(268, 100);
		this.setModal(true);
		this.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblAlias = new JLabel("Alias :");
		lblAlias.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAlias.setBounds(10, 14, 71, 14);
		panel.add(lblAlias);

		tfAlias = new JTextField();
		tfAlias.setBounds(91, 11, 159, 20);
		panel.add(tfAlias);
		tfAlias.setColumns(10);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent paramActionEvent) {
				if (tfAlias.getText().isEmpty()) {
					JOptionPane.showMessageDialog(AliasDialog.this,
							EMPTY_ALIAS_MESSAGE, "Warning!",
							JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						if (keyStore != null && keyStore.containsAlias(tfAlias.getText())) {
							JOptionPane.showMessageDialog(AliasDialog.this,
									USED_ALIAS_MESSAGE, "Warning!",
									JOptionPane.WARNING_MESSAGE);
						} else {
							resultOk = true;
							AliasDialog.this.setVisible(false);
						}
					} catch (KeyStoreException e) {
						e.printStackTrace();
					}
				}
			}
		});
		btnOk.setBounds(91, 42, 71, 23);
		panel.add(btnOk);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent paramActionEvent) {
				resultOk = false;
				AliasDialog.this.setVisible(false);
			}
		});
		btnCancel.setBounds(169, 42, 81, 23);
		panel.add(btnCancel);
	}

	public String getAlias() {
		return tfAlias.getText();
	}
}
