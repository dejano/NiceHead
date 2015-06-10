package portikla.gui.mainframe;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

import portikla.gui.action.CreateCertificateFileAction;
import portikla.gui.action.GenSelfSignedCertAction;
import portikla.gui.action.GenSignedCertAction;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final String TITLE = "Portikla";

	public MainFrame() {
		this.setTitle(TITLE);
		this.setSize(new Dimension(273, 146));
		this.getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		this.addWindowListener(new MainFrameWindowListener());

		JButton btnGenSelfSignedCert = new JButton(
				new GenSelfSignedCertAction());
		btnGenSelfSignedCert.setBounds(10, 11, 244, 23);
		this.getContentPane().add(btnGenSelfSignedCert);

		JButton btnGenSignedCert = new JButton(new GenSignedCertAction());
		btnGenSignedCert.setBounds(10, 45, 244, 23);
		this.getContentPane().add(btnGenSignedCert);

		JButton btnCreateCertificateFile = new JButton(
				new CreateCertificateFileAction());
		btnCreateCertificateFile.setBounds(10, 79, 244, 23);
		getContentPane().add(btnCreateCertificateFile);
	}

	class MainFrameWindowListener extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	}
}
