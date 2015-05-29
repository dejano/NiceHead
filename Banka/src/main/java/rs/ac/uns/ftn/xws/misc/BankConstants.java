package rs.ac.uns.ftn.xws.misc;

import java.util.ResourceBundle;

public class BankConstants {

	public static final String PROP_FILE_PATH = "main/resources/" + 
			"bank";

	public static final String BANK_NAME = ResourceBundle.getBundle(
			PROP_FILE_PATH).getString("bank.name");
	
}
