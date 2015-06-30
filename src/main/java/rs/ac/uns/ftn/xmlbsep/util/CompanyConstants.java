package rs.ac.uns.ftn.xmlbsep.util;

import java.util.ResourceBundle;

public class CompanyConstants {

    public static final String PROP_FILE_PATH =//"main/resources/" +
            "company";

    public static final String COMPANY_NAME = ResourceBundle.getBundle(PROP_FILE_PATH).getString(
            "company.name");

    public static final String KEYSTORE_FILE_PATH = "/" + COMPANY_NAME + ".jks";

    public static final String[] CERT_FILEPATH = new String[]{
//			"../webapps/cb/WEB-INF/classes/" +
//			"src/" +
//			"main/resources/"
            "/", ".cer"};

//	public static final String BANK_CODE = ResourceBundle.getBundle(PROP_FILE_PATH).getString(
//			"bank.code");

    public static final String XSD_PATH = "../webapps/" + COMPANY_NAME + "/WEB-INF/xsd/";

}
