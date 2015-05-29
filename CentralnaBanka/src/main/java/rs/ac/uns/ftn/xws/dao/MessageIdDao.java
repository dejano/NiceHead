package rs.ac.uns.ftn.xws.dao;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;

import rs.ac.uns.ftn.xws.dao.util.RESTUtil;
import rs.ac.uns.ftn.xws.dao.util.RequestMethod;
import rs.ac.uns.ftn.xws.misc.CentralBankConstants;

public class MessageIdDao {

	private static final String getMessageIdQuery = "//*[local-name()='messageIdData']/text()&wrap=no";

	private static final String[] updateMessageIdDataQuery = {
			"replace value of node //*[local-name()='messageIdData'] with '", "'" };

	public static void main(String[] args) throws Exception {
		File file = new File("src/main/resources/");

		RESTUtil.deleteResource(CentralBankConstants.SCHEMA_NAME,
				"messageIdData.xml");
		RESTUtil.createResource(CentralBankConstants.SCHEMA_NAME, "messageIdData.xml",
				new FileInputStream(new File(file, "messageIdData.xml")));

		System.out.println(getMessageId());
	}

	public static String getMessageId() {
		String ret = null;

		String updateQuery;

		try {
			String response = RESTUtil.readString(RESTUtil.retrieveResource(
					getMessageIdQuery, CentralBankConstants.SCHEMA_NAME, RequestMethod.GET));
			BigInteger currMessageId = new BigInteger(response);

			BigInteger newMessageId = currMessageId.add(BigInteger.ONE);

			updateQuery = updateMessageIdDataQuery[0] + newMessageId
					+ updateMessageIdDataQuery[1];

			RESTUtil.retrieveResource(updateQuery, CentralBankConstants.SCHEMA_NAME,
					RequestMethod.POST);

			ret = newMessageId.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	private MessageIdDao() {
	}
}
