package rs.ac.uns.ftn.xws.misc;

import java.util.ResourceBundle;

public class CentralBankConstants {
	
	public static final String PROP_FILE_PATH = //"main/resources/"+
			 "centralBank";
	
	public static final String XSD_PATH = "../webapps/cb/WEB-INF/xsd/"; 

	public static final String KEYSTORE_FILE_PATH = "../webapps/cb/WEB-INF/classes/main/resources/cb.jks";
	public static final String CB_CERT_FILEPATH = "src/main/resources/cb.cer";
	public static final String BANKA1_CERT_FILEPATH = "../webapps/cb/WEB-INF/classes/main/resources/banka1.cer";
	//public static final String KEYSTORE_FILE_PATH = "src/main/resources/myKeyStore.jks";
	
	public static final String SCHEMA_NAME = ResourceBundle.getBundle(
			PROP_FILE_PATH).getString("basex.schema.name");
}
