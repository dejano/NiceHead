package rs.ac.uns.ftn.xws.dao;

import java.io.File;
import java.io.FileInputStream;

import rs.ac.uns.ftn.xws.dao.util.RESTUtil;
import rs.ac.uns.ftn.xws.dao.util.RequestMethod;
import rs.ac.uns.ftn.xws.generated.mp.Mt102;
import rs.ac.uns.ftn.xws.util.BankConstants;
import rs.ac.uns.ftn.xws.util.XmlHelper;

public class ClearingDataDao {

	private static final String[] insertMt102Query = { "insert node ",
			" into //*[local-name()='clearingData']" };

	private static final String[] deleteMt102Query = {
			"delete node //*[local-name()='mt102'][@messageId='", "']" };

	private static final String[] getMt102Query = {
			"//*[local-name()='mt102'][@messageId='", "']&wrap=no" };

	public static void main(String[] args) throws Exception {
		File file = new File("src/main/resources/");
		RESTUtil.deleteResource(BankConstants.BANK_NAME, "clearingData.xml");
		RESTUtil.createResource(BankConstants.BANK_NAME, "clearingData.xml",
				new FileInputStream(new File(file, "clearingData.xml")));
//		System.out.println(getClearingDataAsString("123123"));

		Mt102 mt102 = new Mt102();
		mt102.setMessageId("999");
		insertMt102(XmlHelper.marshall(mt102));

//		@SuppressWarnings("unused")
//		Mt102 mt102 = getMt102("555");

		System.out.println(getClearingDataAsString("999"));
	}

	public static String getClearingDataAsString(String messageId) {
		String ret = null;

		String query = getMt102Query[0] + messageId + getMt102Query[1];

		try {
			ret = RESTUtil.readString(RESTUtil.retrieveResource(query,
					BankConstants.BANK_NAME, RequestMethod.GET));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	public static Mt102 getMt102(String messageId) {
		Mt102 ret = null;

		String query = getMt102Query[0] + messageId + getMt102Query[1];

		try {
			ret = XmlHelper.unmarshall(RESTUtil.retrieveResource(query,
					BankConstants.BANK_NAME, RequestMethod.GET), Mt102.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	public static void insertMt102(String mt102Xml) {
		String q = insertMt102Query[0] + mt102Xml + insertMt102Query[1];

		try {
			RESTUtil.retrieveResourcePost(q, BankConstants.BANK_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void deleteMt102(String mt102MessageId) {
		String q = deleteMt102Query[0] + mt102MessageId + deleteMt102Query[1];

		try {
			RESTUtil.retrieveResource(q, BankConstants.BANK_NAME,
					RequestMethod.POST);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private ClearingDataDao() {
	}
}
