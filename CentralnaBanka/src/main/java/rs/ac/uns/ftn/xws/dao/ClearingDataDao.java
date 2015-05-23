package rs.ac.uns.ftn.xws.dao;

import java.io.File;
import java.io.FileInputStream;

import rs.ac.uns.ftn.xws.dao.util.RESTUtil;
import rs.ac.uns.ftn.xws.dao.util.RequestMethod;
import rs.ac.uns.ftn.xws.domain.ClearingData;
import rs.ac.uns.ftn.xws.util.XmlHelper;

public class ClearingDataDao {

	private static final String SCHEMA_NAME = "centralBank";

	private static final String[] insertMt102Query = { "insert node ",
			" into //clearingData" };

	// TODO xpath namespace problem
	private static final String[] deleteMt102Query = {
			"delete node //clearingData/*[local-name()='mt102'][@messageId='", "']" };

	private static final String getClearingDataQuery = "//clearingData&wrap=no";

	public static void main(String[] args) throws Exception {
		File file = new File("src/main/resources/");

//		RESTUtil.deleteResource(SCHEMA_NAME, "clearingData.xml");
//		RESTUtil.createResource(SCHEMA_NAME, "clearingData.xml",
//				new FileInputStream(new File(file, "clearingData.xml")));

//		System.out.println(getClearingDataAsString());

//		deleteMt102("999");
		
		System.out.println(getClearingDataAsString());
	}

	public static String getClearingDataAsString() {
		String ret = null;

		try {
			ret = RESTUtil.readString(RESTUtil.retrieveResource(
					getClearingDataQuery, SCHEMA_NAME, RequestMethod.GET));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	public static ClearingData getClearingData() {
		ClearingData ret = null;

		try {
			ret = XmlHelper.unmarshall(RESTUtil.retrieveResource(
					getClearingDataQuery, SCHEMA_NAME, RequestMethod.GET),
					ClearingData.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	public static void insertMt102(String mt102Xml) {
		String q = insertMt102Query[0] + mt102Xml + insertMt102Query[1];

		try {
			RESTUtil.retrieveResourcePost(q, SCHEMA_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void deleteMt102(String mt102MessageId) {
		String q = deleteMt102Query[0] + mt102MessageId + deleteMt102Query[1];
		
		try {
			RESTUtil.retrieveResource(q, SCHEMA_NAME, RequestMethod.POST);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private ClearingDataDao() {
	}
}
