package portikla.gui.dialog;

import javax.swing.JDialog;

public abstract class AbstractDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	protected boolean resultOk;

	public boolean isResultOk() {
		return resultOk;
	}
}
