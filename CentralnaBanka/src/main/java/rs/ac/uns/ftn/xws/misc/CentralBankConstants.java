package rs.ac.uns.ftn.xws.misc;

import java.util.ResourceBundle;

public class CentralBankConstants {
	
	public static final String PROP_FILE_PATH = //"main/resources/"+
			 "centralBank";
	
	public static final String XSD_PATH = "../webapps/cb/WEB-INF/xsd/"; 

	public static final String KEYSTORE_FILE_PATH = 
			//"../webapps/cb/WEB-INF/classes/" +
			"src/" +
			"main/resources/cb.jks";
	
	public static final String[] CERT_FILEPATH = new String[] {
			//	"../webapps/cb/WEB-INF/classes/" +
				"src/" +
				"main/resources/", ".cer" };
	
	public static final String SCHEMA_NAME = ResourceBundle.getBundle(
			PROP_FILE_PATH).getString("basex.schema.name");
}
