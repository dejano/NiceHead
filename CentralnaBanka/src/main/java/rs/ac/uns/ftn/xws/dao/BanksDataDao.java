package rs.ac.uns.ftn.xws.dao;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;

import rs.ac.uns.ftn.xws.dao.util.RESTUtil;
import rs.ac.uns.ftn.xws.dao.util.RequestMethod;

public class BanksDataDao {
	private static final String[] getBankClearingAccountNumberQuery = {
			"//bank[swiftCode='", "']/bankClearingAccountNumber/text()&wrap=no" };

	private static final String[] getBankSwiftCodeQuery = {
			"//bank[bankClearingAccountNumber='", "']/swiftCode/text()&wrap=no" };

	private static final String[] getBankBalanceQuery = { "//bank[swiftCode='",
			"']/balance/text()&wrap=no" };

	private static final String[] getBankWsUrl = { "//bank[swiftCode='",
			"']/wsUrl/text()&wrap=no" };

	private static final String[] updateBankBalanceQuery = {
			"replace value of node //bank[swiftCode='", "']/balance with '",
			"'" };

	private static final int ROUNDING_SCALE = 4;

	public static void main(String[] args) throws Exception {
		File file = new File("src/main/resources/");

		// RESTUtil.dropSchema("banks");
		// RESTUtil.createSchema("banks");
		// RESTUtil.createResource("banks", "banks.xml", new FileInputStream(
		// new File(file, "banks.xml")));

		BanksDataDao bdd = new BanksDataDao();

		System.out.println(bdd.getBankClearingAccountNumber("CONARS22"));

		System.out.println(bdd.getBankSwiftCode("111-1111111111111-11"));

		BigDecimal balance = bdd.getBankBalance("CONARS22");
		System.out.println(balance);

		// bdd.updateBankBalance("CONARS22", balance.add(new BigDecimal(10)));

		balance = bdd.getBankBalance("CONARS23");
		System.out.println(balance);

		System.out.println(bdd.getBankWsUrl("CONARS22"));
	}

	public String getBankClearingAccountNumber(String swiftCode) {
		String ret = null;

		String q = getBankClearingAccountNumberQuery[0] + swiftCode
				+ getBankClearingAccountNumberQuery[1];
		try {
			ret = RESTUtil.readString(RESTUtil.retrieveResource(q, "banks",
					RequestMethod.GET));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	public String getBankSwiftCode(String clearingAccountNumber) {
		String ret = null;

		String q = getBankSwiftCodeQuery[0] + clearingAccountNumber
				+ getBankSwiftCodeQuery[1];
		try {
			ret = RESTUtil.readString(RESTUtil.retrieveResource(q, "banks",
					RequestMethod.GET));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	public BigDecimal getBankBalance(String swiftCode) {
		BigDecimal ret = null;

		String q = getBankBalanceQuery[0] + swiftCode + getBankBalanceQuery[1];
		try {
			ret = new BigDecimal(RESTUtil.readString(RESTUtil.retrieveResource(
					q, "banks", RequestMethod.GET)));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	public String getBankWsUrl(String swiftCode) {
		String ret = null;

		String q = getBankWsUrl[0] + swiftCode + getBankWsUrl[1];
		try {
			ret = RESTUtil.readString(RESTUtil.retrieveResource(q, "banks",
					RequestMethod.GET));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	public void updateBankBalance(String swiftCode, BigDecimal newBalance) {
		String q = updateBankBalanceQuery[0]
				+ swiftCode
				+ updateBankBalanceQuery[1]
				+ newBalance.setScale(ROUNDING_SCALE, RoundingMode.CEILING)
						.toPlainString() + updateBankBalanceQuery[2];
		try {
			RESTUtil.readString(RESTUtil.retrieveResource(q, "banks",
					RequestMethod.POST));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
