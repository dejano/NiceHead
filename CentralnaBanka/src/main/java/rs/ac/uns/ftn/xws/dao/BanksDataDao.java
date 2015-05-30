package rs.ac.uns.ftn.xws.dao;

import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;

import rs.ac.uns.ftn.xws.dao.util.RESTUtil;
import rs.ac.uns.ftn.xws.dao.util.RequestMethod;
import rs.ac.uns.ftn.xws.generated.cmn.BankDetails;
import rs.ac.uns.ftn.xws.misc.CentralBankConstants;
import rs.ac.uns.ftn.xws.misc.XmlHelper;

public class BanksDataDao {

	private static final int ROUNDING_SCALE = 4;

	private static final String[] isSwiftCodeValid = {
			"//*[local-name()='bank'][*[local-name()='bankDetails']/*[local-name()='swiftCode']='",
			"']&wrap=no" };

	private static final String[] getBankDetailsQuery = {
			"//*[local-name()='bank'][*[local-name()='bankCode']='",
			"']/*[local-name()='bankDetails']&wrap=no" };

	private static final String[] getBankBalanceQuery = {
			"//*[local-name()='bank'][*[local-name()='bankDetails']/*[local-name()='swiftCode']='",
			"']/*[local-name()='balance']/text()&wrap=no" };

	private static final String[] getBankWsUrl = {
			"//*[local-name()='bank'][*[local-name()='bankDetails']/*[local-name()='swiftCode']='",
			"']/*[local-name()='wsUrl']/text()&wrap=no" };

	private static final String[] updateBankBalanceQuery = {
			"replace value of node //*[local-name()='bank'][*[local-name()='bankDetails']/*[local-name()='swiftCode']='",
			"']/*[local-name()='balance'] with '", "'" };

	public static void main(String[] args) throws Exception {
		File file = new File("src/main/resources/");

		// RESTUtil.dropSchema(CentralBankConstants.SCHEMA_NAME);
		// RESTUtil.createSchema(CentralBankConstants.SCHEMA_NAME);
		// RESTUtil.deleteResource(CentralBankConstants.SCHEMA_NAME,
		// "banksData.xml");
		// RESTUtil.createResource(CentralBankConstants.SCHEMA_NAME,
		// "banksData.xml", new FileInputStream(new File(file,
		// "banksData.xml")));
		//
		// System.out.println(getBankDetails("888"));
		//
		// BigDecimal balance = getBankBalance("CONARS22");
		// System.out.println(balance);
		//
		// updateBankBalance("CONARS22", balance.add(new BigDecimal(10)));
		//
		// balance = getBankBalance("CONARS22");
		// System.out.println(balance);
		//
		// System.out.println(getBankWsUrl("CONARS22"));
		System.out.println(isSwiftCodeValid("CONARS24"));
	}

	public static BankDetails getBankDetails(String bankCode) {
		BankDetails ret = null;

		String q = getBankDetailsQuery[0] + bankCode + getBankDetailsQuery[1];
		try {
			InputStream is = RESTUtil.retrieveResource(q,
					CentralBankConstants.SCHEMA_NAME, RequestMethod.GET);

			if (is != null && is.available() > 0)
				ret = XmlHelper.unmarshall(is, BankDetails.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	public static BigDecimal getBankBalance(String swiftCode) {
		BigDecimal ret = null;

		String q = getBankBalanceQuery[0] + swiftCode + getBankBalanceQuery[1];

		System.out.println(swiftCode);

		try {
			ret = new BigDecimal(RESTUtil.readString(RESTUtil.retrieveResource(
					q, CentralBankConstants.SCHEMA_NAME, RequestMethod.GET)));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	public static String getBankWsUrl(String swiftCode) {
		String ret = null;

		String q = getBankWsUrl[0] + swiftCode + getBankWsUrl[1];
		try {
			ret = RESTUtil.readString(RESTUtil.retrieveResource(q,
					CentralBankConstants.SCHEMA_NAME, RequestMethod.GET));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	public static void updateBankBalance(String swiftCode, BigDecimal newBalance) {
		String q = updateBankBalanceQuery[0]
				+ swiftCode
				+ updateBankBalanceQuery[1]
				+ newBalance.setScale(ROUNDING_SCALE, RoundingMode.CEILING)
						.toPlainString() + updateBankBalanceQuery[2];
		try {
			RESTUtil.retrieveResource(q, CentralBankConstants.SCHEMA_NAME,
					RequestMethod.POST);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean isSwiftCodeValid(String swiftCode) {
		String ret = null;

		String q = isSwiftCodeValid[0] + swiftCode + isSwiftCodeValid[1];
		try {
			ret = RESTUtil.readString(RESTUtil.retrieveResource(q,
					CentralBankConstants.SCHEMA_NAME, RequestMethod.GET));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret != null && !ret.isEmpty();
	}

	private BanksDataDao() {
	}
}
