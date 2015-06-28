package rs.ac.uns.ftn.xws.dao;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;

import rs.ac.uns.ftn.xws.dao.util.RESTUtil;
import rs.ac.uns.ftn.xws.dao.util.RequestMethod;
import rs.ac.uns.ftn.xws.misc.BankConstants;

public class CompanyDataDao {

	private static final int ROUNDING_SCALE = 4;

	private static final String[] getCompanyBalanceQuery = {
			"//*[local-name()='company'][*[local-name()='accountNumber']='",
			"']/*[local-name()='balance']/text()&wrap=no" };

	private static final String[] getCompanyReservedAmountQuery = {
			"//*[local-name()='company'][*[local-name()='accountNumber']='",
			"']/*[local-name()='reservedAmount']/text()&wrap=no" };

	private static final String[] getCompanyWsUrl = {
			"//*[local-name()='company'][*[local-name()='accountNumber']='",
			"']/*[local-name()='wsUrl']/text()&wrap=no" };

	private static final String[] updateCompanyBalanceQuery = {
			"replace value of node //*[local-name()='company'][*[local-name()='accountNumber']='",
			"']/*[local-name()='balance'] with '", "'" };

	private static final String[] updateCompanyReservedAmountQuery = {
			"replace value of node //*[local-name()='company'][*[local-name()='accountNumber']='",
			"']/*[local-name()='reservedAmount'] with '", "'" };

	public static void main(String[] args) throws Exception {
		File file = new File("src/main/resources/");
//		RESTUtil.dropSchema(BankConstants.BANK_NAME);
//		RESTUtil.createSchema(BankConstants.BANK_NAME);
		RESTUtil.createResource(BankConstants.BANK_NAME, "companyData.xml", new FileInputStream(
				new File(file, "companyData.xml")));

//		BigDecimal balance = getCompanyBalance("222-2222222222222-22");
//		System.out.println(balance);

//		updateCompanyBalance("222-2222222222222-22", balance.add(new BigDecimal(10)));

//		balance = getCompanyBalance("222-2222222222222-22");
//		System.out.println(balance);

		System.out.println(getCompanyWsUrl("223-2222222222222-22"));
		System.out.println(getCompanyWsUrl("222-2222222222222-22"));
	}

	public static BigDecimal getCompanyBalance(String accountNumber) {
		BigDecimal ret = null;

		String q = getCompanyBalanceQuery[0] + accountNumber + getCompanyBalanceQuery[1];
		try {
			ret = new BigDecimal(RESTUtil.readString(RESTUtil.retrieveResource(q,
					BankConstants.BANK_NAME, RequestMethod.GET)));
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
			ret = new BigDecimal(RESTUtil.readString(RESTUtil.retrieveResource(q,
					BankConstants.BANK_NAME, RequestMethod.GET)));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	public static String getCompanyWsUrl(String accountNumber) {
		String ret = null;

		String q = getCompanyWsUrl[0] + accountNumber + getCompanyWsUrl[1];
		try {
			ret = RESTUtil.readString(RESTUtil.retrieveResource(q, BankConstants.BANK_NAME,
					RequestMethod.GET));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}
	
	public static boolean accountNumberExists(String accountNumber) {
		String ret = null;

		String q = getCompanyWsUrl[0] + accountNumber + getCompanyWsUrl[1];
		try {
			ret = RESTUtil.readString(RESTUtil.retrieveResource(q, BankConstants.BANK_NAME,
					RequestMethod.GET));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret != null && !ret.equals("");
	}

	public static void updateCompanyBalance(String accountNumber, BigDecimal newBalance) {
		String q = updateCompanyBalanceQuery[0] + accountNumber + updateCompanyBalanceQuery[1]
				+ newBalance.setScale(ROUNDING_SCALE, RoundingMode.CEILING).toPlainString()
				+ updateCompanyBalanceQuery[2];
		try {
			RESTUtil.retrieveResource(q, BankConstants.BANK_NAME, RequestMethod.POST);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void updateCompanyReservedAmount(String accountNumber,
			BigDecimal newReservedAmount) {
		String q = updateCompanyReservedAmountQuery[0] + accountNumber
				+ updateCompanyReservedAmountQuery[1]
				+ newReservedAmount.setScale(ROUNDING_SCALE, RoundingMode.CEILING).toPlainString()
				+ updateCompanyReservedAmountQuery[2];
		try {
			RESTUtil.retrieveResource(q, BankConstants.BANK_NAME, RequestMethod.POST);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private CompanyDataDao() {
	}
}
