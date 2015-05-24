package rs.ac.uns.ftn.xws.dao;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.w3c.dom.Document;

import rs.ac.uns.ftn.xws.dao.util.ParserUtil;
import rs.ac.uns.ftn.xws.dao.util.RESTUtil;
import rs.ac.uns.ftn.xws.dao.util.RequestMethod;

public class OrdersDataDao {

	private static final int SECTIONAL_INCREMENTAL_VALUE = 3;

	// private static final String[] getBankClearingAccountNumberQuery = {
	// "//bank[swiftCode='", "']/bankClearingAccountNumber/text()&wrap=no" };

	private static final String[] getOrderValueOfDebtor = {
			"//order/assetElements[debtor='", "']/value/text()&wrap=no" };

	// private static final String[] getOrdersByAccNumberAndOrderDate = {
	// "//orders/order[assetElements/orderDate ='2015-05-21'][assetElements/debtorAccountDetails/accountNumber='310-0000000000111-83']&wrap=no"
	// };

	private static final String[] getOrdersByAccNumberAndOrderDate = {
			"//orders/order[assetElements/orderDate ='",
			"'][assetElements/debtorAccountDetails/accountNumber='",
			"'][position()= ", " to ", "]&wrap=no" };

//	private static final String[] getPaymentsByAccNumberAndOrderDate = {
//		"//paymentsData/payment[orderDate ='",
//		"'][debtorAccountDetails/accountNumber='",
//		"'][position()= ", " to ", "]&wrap=no" };
//	private static final String[] getPaymentsByAccNumberAndOrderDate = {
//		"//bsb:paymentsData/bsb:payment[cmn:orderDate ='",
//		"'][cmn:debtorAccountDetails/cmn:accountNumber='",
//		"'][position()= ", " to ", "]&wrap=no" };
//	
	private static final String[] getPaymentsByAccNumberAndOrderDate = {
		"//bsb:paymentsData/bsb:payment[bs:previousBalance=0.0]" };
	
	// private static final String[] getBankSwiftCodeQuery = {
	// "//bank[bankClearingAccountNumber='", "']/swiftCode/text()&wrap=no" };
	//
	// private static final String[] getBankBalanceQuery = {
	// "//bank[swiftCode='",
	// "']/balance/text()&wrap=no" };
	//
	// private static final String[] getBankWsUrl = { "//bank[swiftCode='",
	// "']/wsUrl/text()&wrap=no" };
	//
	// private static final String[] updateBankBalanceQuery = {
	// "replace value of node //bank[swiftCode='", "']/balance with '",
	// "'" };

	public static void main(String[] args) {

		File file = new File("src/main/resources/");
		//
		try {
			RESTUtil.dropSchema("orders");
			RESTUtil.dropSchema("payments");
			RESTUtil.createSchema("payments");
			
			RESTUtil.createResource("payments", "payments.xml",
					new FileInputStream(new File(file, "payments.xml")));
			
			try {
				String ret = RESTUtil.readString(RESTUtil.retrieveResource(getPaymentsByAccNumberAndOrderDate[0], "orders",
						RequestMethod.GET));
				System.out.println(ret);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//getPaymentsByAccNumberAndOrderDate[0];
			
//			RESTUtil.createResource("orders", "orders.xml",
//					new FileInputStream(new File(file, "orders.xml")));

//			String str = getPayments("2015-05-21", "310-0000000000111-83", 1);
//			System.out.println(str);
			
			
			
//			List<String> parsed = ParserUtil.getOrders(str); 
//			
//			Document doc = ParserUtil.StringToXML(parsed.get(0));
//			ParserUtil.test(doc);
			
//			System.out.println(doc.getXmlVersion());
			
//			System.out.println(orderi.get(0).getAssetElements().getDebtor());
//			System.out.println(parsed.get(0));
//			System.out.println(parsed.get(1));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public static String getPayments(String date,String accountNumber, int statementNumber) {
		String ret = null;

		int maxOffSetValue = statementNumber * SECTIONAL_INCREMENTAL_VALUE;
		int minOffSetValue = maxOffSetValue - SECTIONAL_INCREMENTAL_VALUE + 1;

		// String q = getOrdersByAccNumberAndOrderDate[0];
		String q = getPaymentsByAccNumberAndOrderDate[0] + date
				+ getPaymentsByAccNumberAndOrderDate[1] + accountNumber
				+ getPaymentsByAccNumberAndOrderDate[2] + minOffSetValue +
				getPaymentsByAccNumberAndOrderDate[3] + maxOffSetValue +
				getPaymentsByAccNumberAndOrderDate[4];
		
		System.out.println("Query :   "+q+"\n");
		
		try {
			ret = RESTUtil.readString(RESTUtil.retrieveResource(q, "orders",
					RequestMethod.GET));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	
	}
	
	public static String getOrders(String date, String accountNumber,
			int sectionalNumber) {
		// public static String getOrders() {
		String ret = null;

		int maxOffSetValue = sectionalNumber * SECTIONAL_INCREMENTAL_VALUE;
		int minOffSetValue = maxOffSetValue - SECTIONAL_INCREMENTAL_VALUE + 1;

		// String q = getOrdersByAccNumberAndOrderDate[0];
		String q = getOrdersByAccNumberAndOrderDate[0] + date
				+ getOrdersByAccNumberAndOrderDate[1] + accountNumber
				+ getOrdersByAccNumberAndOrderDate[2] + minOffSetValue +
				getOrdersByAccNumberAndOrderDate[3] + maxOffSetValue +
				getOrdersByAccNumberAndOrderDate[4];
		
		System.out.println("Query :   "+q+"\n");
		
		try {
			ret = RESTUtil.readString(RESTUtil.retrieveResource(q, "orders",
					RequestMethod.GET));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	public static String getOrderValues(String debtorName) {
		String ret = null;

		// String q = "//orders";
		String q = getOrderValueOfDebtor[0] + debtorName
				+ getOrderValueOfDebtor[1];
		try {
			ret = RESTUtil.readString(RESTUtil.retrieveResource(q, "orders",
					RequestMethod.GET));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

}
