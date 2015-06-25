package rs.ac.uns.ftn.xws.misc;

import java.util.ResourceBundle;

public class CentralBankConstants {

	public static final String PROP_FILE_PATH = "main/resources/"+
			 "centralBank";

	public static final String KEYSTORE_FILE_PATH = "src/main/resources/cb.jks";
	//public static final String KEYSTORE_FILE_PATH = "src/main/resources/myKeyStore.jks";
	
	public static final String SCHEMA_NAME = ResourceBundle.getBundle(
			PROP_FILE_PATH).getString("basex.schema.name");
}
