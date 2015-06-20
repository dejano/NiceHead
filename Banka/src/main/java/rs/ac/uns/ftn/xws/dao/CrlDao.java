package rs.ac.uns.ftn.xws.dao;

import java.io.File;
import java.io.FileInputStream;

import rs.ac.uns.ftn.xws.dao.util.RESTUtil;
import rs.ac.uns.ftn.xws.dao.util.RequestMethod;
import rs.ac.uns.ftn.xws.misc.BankConstants;

public class CrlDao {

	private static final String[] getSerialNumberQuery = {
			"//*[local-name()='serialNumber' and text()='", "']&wrap=no" };

	public static void main(String[] args) throws Exception {
		File file = new File("src/main/resources/");

		RESTUtil.deleteResource(BankConstants.BANK_NAME, "crl.xml");
		RESTUtil.createResource(BankConstants.BANK_NAME, "crl.xml",
				new FileInputStream(new File(file, "crl.xml")));

		System.out.println(isInCrl("22"));
	}

	public static boolean isInCrl(String serialNumber) {
		boolean ret = false;

		String query = getSerialNumberQuery[0] + serialNumber
				+ getSerialNumberQuery[1];
		try {
			String serialNumberXml = RESTUtil.readString(RESTUtil
					.retrieveResource(query, BankConstants.BANK_NAME,
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
