package rs.ac.uns.ftn.xmlbsep.dao.impl;

import rs.ac.uns.ftn.xmlbsep.dao.util.RESTUtil;
import rs.ac.uns.ftn.xmlbsep.dao.util.RequestMethod;
import rs.ac.uns.ftn.xmlbsep.domain.tokens.ObjectFactory;
import rs.ac.uns.ftn.xmlbsep.util.CompanyConstants;
import rs.ac.uns.ftn.xmlbsep.util.XmlHelper;

import java.io.File;
import java.io.FileInputStream;


public class TokensDao {


	private static final String[] getTokenQuery = { "//*[local-name()='token' and text()='",
			"']&wrap=no" };

	private static final String[] insertTokenQuery = { "insert node ",
			" into //*[local-name()='tokenList']" };

	public static void main(String[] args) throws Exception {
		File file = new File("src/main/resources/");

		RESTUtil.dropSchema("firma1");
		RESTUtil.createSchema(CompanyConstants.COMPANY_NAME);


		RESTUtil.deleteResource(CompanyConstants.COMPANY_NAME, "tokens.xml");
		RESTUtil.createResource(CompanyConstants.COMPANY_NAME, "tokens.xml", new FileInputStream(
				new File(file, "tokens.xml")));

		System.out.println(isAlreadyUsed("111"));
	}

	public static boolean isAlreadyUsed(String token) {
		boolean ret = false;

		String query = getTokenQuery[0] + token + getTokenQuery[1];
		try {
			String serialNumberXml = RESTUtil.readString(RESTUtil.retrieveResource(query,
					CompanyConstants.COMPANY_NAME, RequestMethod.GET));

			ret = serialNumberXml != null && !serialNumberXml.isEmpty();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	public static void insertToken(String token) {
		String tokenXml = XmlHelper.marshall(token, ObjectFactory.class, "Token");

		String q = insertTokenQuery[0] + tokenXml + insertTokenQuery[1];

		try {
			RESTUtil.retrieveResourcePost(q, CompanyConstants.COMPANY_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private TokensDao() {
	}
}
