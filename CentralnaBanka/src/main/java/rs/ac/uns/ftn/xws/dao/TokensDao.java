package rs.ac.uns.ftn.xws.dao;

import java.io.File;
import java.io.FileInputStream;

import rs.ac.uns.ftn.xws.dao.util.RESTUtil;
import rs.ac.uns.ftn.xws.dao.util.RequestMethod;
import rs.ac.uns.ftn.xws.domain.tokens.ObjectFactory;
import rs.ac.uns.ftn.xws.misc.CentralBankConstants;
import rs.ac.uns.ftn.xws.misc.XmlHelper;

public class TokensDao {

	private static final String[] getTokenQuery = { "//*[local-name()='token' and text()='",
			"']&wrap=no" };

	private static final String[] insertTokenQuery = { "insert node ",
			" into //*[local-name()='tokenList']" };

	public static void main(String[] args) throws Exception {
		File file = new File("src/main/resources/");

		RESTUtil.deleteResource(CentralBankConstants.SCHEMA_NAME, "tokens.xml");
		RESTUtil.createResource(CentralBankConstants.SCHEMA_NAME, "tokens.xml", new FileInputStream(
				new File(file, "tokens.xml")));

		System.out.println(isAlreadyUsed("111"));
		insertToken("112");
		System.out.println(isAlreadyUsed("112"));
	}

	public static boolean isAlreadyUsed(String token) {
		boolean ret = false;

		String query = getTokenQuery[0] + token + getTokenQuery[1];
		try {
			String serialNumberXml = RESTUtil.readString(RESTUtil.retrieveResource(query,
					CentralBankConstants.SCHEMA_NAME, RequestMethod.GET));

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
			RESTUtil.retrieveResourcePost(q, CentralBankConstants.SCHEMA_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private TokensDao() {
	}
}
