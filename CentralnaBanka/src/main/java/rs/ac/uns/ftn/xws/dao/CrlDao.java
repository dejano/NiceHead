package rs.ac.uns.ftn.xws.dao;

import java.io.File;
import java.io.FileInputStream;

import rs.ac.uns.ftn.xws.dao.util.RESTUtil;
import rs.ac.uns.ftn.xws.dao.util.RequestMethod;
import rs.ac.uns.ftn.xws.misc.CentralBankConstants;

public class CrlDao {

	private static final String[] getSerialNumberQuery = {
			"//*[local-name()='serialNumber' and text()='", "']&wrap=no" };

	public static void main(String[] args) throws Exception {
		File file = new File("src/main/resources/");

		RESTUtil.deleteResource(CentralBankConstants.SCHEMA_NAME, "crl.xml");
		RESTUtil.createResource(CentralBankConstants.SCHEMA_NAME, "crl.xml",
				new FileInputStream(new File(file, "crl.xml")));

		System.out.println(isInCrl("22"));
	}

	public static boolean isInCrl(String serialNumber) {
		boolean ret = false;

		String query = getSerialNumberQuery[0] + serialNumber
				+ getSerialNumberQuery[1];
		try {
			String serialNumberXml = RESTUtil.readString(RESTUtil
					.retrieveResource(query, CentralBankConstants.SCHEMA_NAME,
							RequestMethod.GET));

			ret = serialNumberXml != null && !serialNumberXml.isEmpty();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	private CrlDao() {
	}
}
