package rs.ac.uns.ftn.xws.dao;

import java.io.File;
import java.io.FileInputStream;

import rs.ac.uns.ftn.xws.dao.util.RESTUtil;
import rs.ac.uns.ftn.xws.dao.util.RequestMethod;
import rs.ac.uns.ftn.xws.domain.ClearingData;
import rs.ac.uns.ftn.xws.misc.CentralBankConstants;
import rs.ac.uns.ftn.xws.misc.XmlHelper;

public class ClearingDataDao {

	private static final String[] insertMt102Query = { "insert node ",
			" into //*[local-name()='clearingData']" };

	private static final String[] deleteMt102Query = {
			"delete node //*[local-name()='mt102'][@messageId='", "']" };

	private static final String getClearingDataQuery = "//*[local-name()='clearingData']&wrap=no";

	public static void main(String[] args) throws Exception {
		File file = new File("src/main/resources/");
		RESTUtil.deleteResource(CentralBankConstants.SCHEMA_NAME,
				"clearingData.xml");
		RESTUtil.createResource(CentralBankConstants.SCHEMA_NAME,
				"clearingData.xml", new FileInputStream(new File(file,
						"clearingData.xml")));

		// System.out.println(getClearingDataAsString());

		// Mt102 mt102 = new Mt102();
		// mt102.setMessageId("123123");
		// mt102.setTotalAmount(new BigDecimal(12));
		// insertMt102(XmlHelper.marshall(mt102));
		//
		// System.out.println(getClearingDataAsString());
		//
		// deleteMt102("2");

		System.out.println(getClearingDataAsString());
	}

	public static String getClearingDataAsString() {
		String ret = null;

		try {
			ret = RESTUtil.readString(RESTUtil.retrieveResource(
					getClearingDataQuery, CentralBankConstants.SCHEMA_NAME,
					RequestMethod.GET));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	public static ClearingData getClearingData() {
		ClearingData ret = null;

		try {
			ret = XmlHelper.unmarshall(RESTUtil.retrieveResource(
					getClearingDataQuery, CentralBankConstants.SCHEMA_NAME,
					RequestMethod.GET), ClearingData.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	public static void insertMt102(String mt102Xml) {
		String q = insertMt102Query[0] + mt102Xml + insertMt102Query[1];

		try {
			RESTUtil.retrieveResourcePost(q, CentralBankConstants.SCHEMA_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void deleteMt102(String mt102MessageId) {
		String q = deleteMt102Query[0] + mt102MessageId + deleteMt102Query[1];

		try {
			RESTUtil.retrieveResource(q, CentralBankConstants.SCHEMA_NAME,
					RequestMethod.POST);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private ClearingDataDao() {
	}
}
