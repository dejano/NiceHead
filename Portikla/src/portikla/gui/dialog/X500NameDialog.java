package portikla.gui.dialog;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.netbeans.validation.api.Problem;
import org.netbeans.validation.api.builtin.stringvalidation.StringValidators;
import org.netbeans.validation.api.ui.swing.SwingValidationGroup;

public class X500NameDialog extends AbstractDialog {

	private static final long serialVersionUID = 1L;

	protected static final String FORM_INVALID_TITLE = "Form invalid";

	private JTextField tfSerialNumber;
	private JTextField tfOrgUnit;
	private JTextField tfOrgName;
	private JTextField tfCountry;
	private JTextField tfEmail;
	private JTextField tfSurname;
	private JTextField tfGivenName;
	private JTextField tfCommonName;
	private JTextField tfDaysValid;
	private JTextField tfCrl;

	private SwingValidationGroup validationGroup;

	private X500Name x500Name;

	private boolean selfSigned = false;

	@SuppressWarnings("unchecked")
	public X500NameDialog(boolean selfSigned) {
		this.selfSigned = selfSigned;

		this.setTitle("Certificate details");
		this.setResizable(false);
		this.setSize(327, 377);
		this.setModal(true);
		this.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lbSerialNumber = new JLabel("Serial number :");
		lbSerialNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lbSerialNumber.setBounds(10, 14, 127, 14);
		panel.add(lbSerialNumber);

		JLabel lblOrganisationUnitou = new JLabel("Organisation unit :");
		lblOrganisationUnitou.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOrganisationUnitou.setBounds(10, 163, 127, 14);
		panel.add(lblOrganisationUnitou);

		JLabel lblNewLabel = new JLabel("Organisation name :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 188, 127, 14);
		panel.add(lblNewLabel);

		tfSerialNumber = new JTextField();
		// tfSerialNumber.setEditable(false);
		// try {
		// tfSerialNumber.setText(String.valueOf(SecureRandom.getInstance("SHA1PRNG").nextInt()));
		// } catch (NoSuchAlgorithmException e) {
		// e.printStackTrace();
		// }
		tfSerialNumber.setBounds(147, 11, 159, 20);
		panel.add(tfSerialNumber);
		tfSerialNumber.setColumns(10);

		tfOrgUnit = new JTextField();
		tfOrgUnit.setBounds(147, 160, 159, 20);
		panel.add(tfOrgUnit);
		tfOrgUnit.setColumns(10);

		tfOrgName = new JTextField();
		tfOrgName.setBounds(147, 185, 159, 20);
		panel.add(tfOrgName);
		tfOrgName.setColumns(10);

		JLabel lblCountrtc = new JLabel("Country :");
		lblCountrtc.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCountrtc.setBounds(10, 216, 127, 14);
		panel.add(lblCountrtc);

		tfCountry = new JTextField();
		tfCountry.setColumns(10);
		tfCountry.setBounds(147, 213, 159, 20);
		panel.add(tfCountry);

		JLabel lblEmaile = new JLabel("Email :");
		lblEmaile.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmaile.setBounds(10, 244, 127, 14);
		panel.add(lblEmaile);

		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(147, 241, 159, 20);
		panel.add(tfEmail);

		JLabel lblGivenNamen = new JLabel("Surname :");
		lblGivenNamen.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGivenNamen.setBounds(10, 107, 127, 14);
		panel.add(lblGivenNamen);

		tfSurname = new JTextField();
		tfSurname.setColumns(10);
		tfSurname.setBounds(147, 104, 159, 20);
		panel.add(tfSurname);

		JLabel lblGivenName = new JLabel("Given name :");
		lblGivenName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGivenName.setBounds(10, 135, 127, 14);
		panel.add(lblGivenName);

		tfGivenName = new JTextField();
		tfGivenName.setColumns(10);
		tfGivenName.setBounds(147, 132, 159, 20);
		panel.add(tfGivenName);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent paramActionEvent) {
				Problem problem = validationGroup.performValidation();

				if (problem != null) {
					JOptionPane.showMessageDialog(X500NameDialog.this,
							problem.getMessage(), FORM_INVALID_TITLE,
							JOptionPane.ERROR_MESSAGE);
				} else {
					X500NameDialog.this.createX500Name();
					resultOk = true;
					X500NameDialog.this.setVisible(false);
				}
			}
		});
		btnOk.setBounds(144, 315, 71, 23);
		panel.add(btnOk);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent paramActionEvent) {
				resultOk = false;
				X500NameDialog.this.setVisible(false);
			}
		});
		btnCancel.setBounds(225, 315, 81, 23);
		panel.add(btnCancel);

		JLabel lblCommonName = new JLabel("Common name :");
		lblCommonName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCommonName.setBounds(10, 76, 127, 14);
		panel.add(lblCommonName);

		tfCommonName = new JTextField();
		tfCommonName.setColumns(10);
		tfCommonName.setBounds(147, 73, 159, 20);
		panel.add(tfCommonName);

		JLabel lblDaysValid = new JLabel("Days valid :");
		lblDaysValid.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDaysValid.setBounds(10, 45, 127, 14);
		panel.add(lblDaysValid);

		tfDaysValid = new JTextField();
		tfDaysValid.setColumns(10);
		tfDaysValid.setBounds(147, 42, 159, 20);
		panel.add(tfDaysValid);

		if (!selfSigned) {
			JLabel lblIssuerCrUrl = new JLabel("CRL location :");
			lblIssuerCrUrl.setHorizontalAlignment(SwingConstants.RIGHT);
			lblIssuerCrUrl.setBounds(10, 272, 127, 14);
			panel.add(lblIssuerCrUrl);

			tfCrl = new JTextField();
			tfCrl.setName("Email field");
			tfCrl.setColumns(10);
			tfCrl.setBounds(147, 269, 159, 20);
			panel.add(tfCrl);
		}

		validationGroup = SwingValidationGroup.create();

		tfEmail.setName("Email field");
		validationGroup.add(tfEmail, StringValidators.REQUIRE_NON_EMPTY_STRING,
				StringValidators.EMAIL_ADDRESS, StringValidators.NO_WHITESPACE);

		tfCountry.setName("Country field");
		validationGroup.add(tfCountry, StringValidators.NO_WHITESPACE,
				StringValidators.REQUIRE_NON_EMPTY_STRING,
				StringValidators.numberRange(2, 2),
				StringValidators.MAY_NOT_START_WITH_DIGIT);

		tfOrgName.setName("Organisation name field");
		validationGroup.add(tfOrgName,
				StringValidators.REQUIRE_NON_EMPTY_STRING);

		tfOrgUnit.setName("Organisation unit field");
		validationGroup.add(tfOrgUnit,
				StringValidators.REQUIRE_NON_EMPTY_STRING);

		tfGivenName.setName("Given name field");
		validationGroup.add(tfGivenName,
				StringValidators.REQUIRE_NON_EMPTY_STRING);

		tfSurname.setName("Surname field");
		validationGroup.add(tfSurname,
				StringValidators.REQUIRE_NON_EMPTY_STRING);

		tfCommonName.setName("Common name field");
		validationGroup.add(tfCommonName,
				StringValidators.REQUIRE_NON_EMPTY_STRING);

		tfDaysValid.setName("Days valid field");
		validationGroup.add(tfDaysValid,
				StringValidators.REQUIRE_NON_EMPTY_STRING,
				StringValidators.NO_WHITESPACE,
				StringValidators.REQUIRE_VALID_INTEGER,
				StringValidators.REQUIRE_NON_NEGATIVE_NUMBER);

		tfSerialNumber.setName("Serial number field");
		validationGroup.add(tfSerialNumber,
				StringValidators.REQUIRE_NON_NEGATIVE_NUMBER,
				StringValidators.REQUIRE_VALID_INTEGER,
				StringValidators.NO_WHITESPACE,
				StringValidators.REQUIRE_NON_EMPTY_STRING);

		if (!selfSigned) {
			tfSerialNumber.setName("CRL field");
			validationGroup.add(tfCrl,
					StringValidators.REQUIRE_NON_EMPTY_STRING,
					StringValidators.URL_MUST_BE_VALID,
					StringValidators.NO_WHITESPACE);
		}
	}

	private void createX500Name() {
		X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);

		builder.addRDN(BCStyle.CN, tfCommonName.getText());
		builder.addRDN(BCStyle.SURNAME, tfSurname.getText());
		builder.addRDN(BCStyle.GIVENNAME, tfGivenName.getText());
		builder.addRDN(BCStyle.O, tfOrgName.getText());
		builder.addRDN(BCStyle.OU, tfOrgUnit.getText());
		builder.addRDN(BCStyle.C, tfCountry.getText().toUpperCase());
		builder.addRDN(BCStyle.E, tfEmail.getText());

		if (!selfSigned) {
			builder.addRDN(new ASN1ObjectIdentifier("2.5.4.13"), tfCrl.getText());
		}

		this.x500Name = builder.build();
	}

	public X500Name getX500Name() {
		return x500Name;
	}

	public String getSerialNumber() {
		return tfSerialNumber.getText();
	}

	public int getDaysValid() {
		return Integer.parseInt(tfDaysValid.getText());
	}
}
