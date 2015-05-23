package rs.ac.uns.ftn.xws.dao;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;

import rs.ac.uns.ftn.xws.dao.util.RESTUtil;
import rs.ac.uns.ftn.xws.dao.util.RequestMethod;

public class CompanyDataDao {
	private static final String SCHEMA_NAME = "bank";

	private static final int ROUNDING_SCALE = 4;

	private static final String[] getCompanyBalanceQuery = {
			"//company[accountNumber='", "']/balance/text()&wrap=no" };
	
	private static final String[] getCompanyReservedAmountQuery = {
		"//company[accountNumber='", "']/reservedAmount/text()&wrap=no" };

	private static final String[] getCompanyWsUrl = {
			"//company[accountNumber='", "']/wsUrl/text()&wrap=no" };

	private static final String[] updateCompanyBalanceQuery = {
			"replace value of node //company[accountNumber='",
			"']/balance with '", "'" };
	
	private static final String[] updateCompanyReservedAmountQuery = {
		"replace value of node //company[accountNumber='",
		"']/reservedAmount with '", "'" };

	public static void main(String[] args) throws Exception {
		File file = new File("src/main/resources/");
//		RESTUtil.dropSchema(SCHEMA_NAME);
//		RESTUtil.createSchema(SCHEMA_NAME);
//		RESTUtil.createResource(SCHEMA_NAME, "companyData.xml",
//				new FileInputStream(new File(file, "companyData.xml")));

		BigDecimal balance = getCompanyBalance("222-2222222222222-22");
		System.out.println(balance);

		updateCompanyBalance("222-2222222222222-22",
				balance.add(new BigDecimal(10)));

		balance = getCompanyBalance("222-2222222222222-22");
		System.out.println(balance);

		System.out.println(getCompanyWsUrl("223-2222222222222-22"));
	}

	public static BigDecimal getCompanyBalance(String accountNumber) {
		BigDecimal ret = null;

		String q = getCompanyBalanceQuery[0] + accountNumber
				+ getCompanyBalanceQuery[1];
		try {
			ret = new BigDecimal(RESTUtil.readString(RESTUtil.retrieveResource(
					q, SCHEMA_NAME, RequestMethod.GET)));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}
	
	public static BigDecimal getCompanyReservedAmount(String accountNumber) {
		BigDecimal ret = null;

		String q = getCompanyReservedAmountQuery[0] + accountNumber
				+ getCompanyReservedAmountQuery[1];
		try {
			ret = new BigDecimal(RESTUtil.readString(RESTUtil.retrieveResource(
					q, SCHEMA_NAME, RequestMethod.GET)));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	public static String getCompanyWsUrl(String accountNumber) {
		String ret = null;

		String q = getCompanyWsUrl[0] + accountNumber + getCompanyWsUrl[1];
		try {
			ret = RESTUtil.readString(RESTUtil.retrieveResource(q, SCHEMA_NAME,
					RequestMethod.GET));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	public static void updateCompanyBalance(String accountNumber,
			BigDecimal newBalance) {
		String q = updateCompanyBalanceQuery[0]
				+ accountNumber
				+ updateCompanyBalanceQuery[1]
				+ newBalance.setScale(ROUNDING_SCALE, RoundingMode.CEILING)
						.toPlainString() + updateCompanyBalanceQuery[2];
		try {
			RESTUtil.retrieveResource(q, SCHEMA_NAME, RequestMethod.POST);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void updateCompanyReservedAmount(String accountNumber,
			BigDecimal newReservedAmount) {
		String q = updateCompanyReservedAmountQuery[0]
				+ accountNumber
				+ updateCompanyReservedAmountQuery[1]
				+ newReservedAmount.setScale(ROUNDING_SCALE, RoundingMode.CEILING)
						.toPlainString() + updateCompanyReservedAmountQuery[2];
		try {
			RESTUtil.retrieveResource(q, SCHEMA_NAME, RequestMethod.POST);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private CompanyDataDao() {
	}
}
