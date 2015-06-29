package rs.ac.uns.ftn.xws.misc;

import java.util.ResourceBundle;

public class BankConstants {

	public static final String PROP_FILE_PATH =//"main/resources/" +
			"bank";

	public static final String BANK_NAME = ResourceBundle.getBundle(PROP_FILE_PATH).getString(
			"bank.name");

	public static final String KEYSTORE_FILE_PATH = 
//			"../webapps/" + BANK_NAME + "/WEB-INF/classes/" +
			"src/" +
			"main/resources/" + BANK_NAME + ".jks";

	public static final String[] CERT_FILEPATH = new String[] {
//			"../webapps/cb/WEB-INF/classes/" +
			"src/" +
			"main/resources/", ".cer" };

	public static final String BANK_CODE = ResourceBundle.getBundle(PROP_FILE_PATH).getString(
			"bank.code");

	public static final String XSD_PATH = "../webapps/" + BANK_NAME + "/WEB-INF/xsd/";

}
