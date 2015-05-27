package rs.ac.uns.ftn.xws.dao;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.xws.dao.util.ParserUtil;
import rs.ac.uns.ftn.xws.dao.util.RESTUtil;
import rs.ac.uns.ftn.xws.dao.util.RequestMethod;
import rs.ac.uns.ftn.xws.domain.bsb.PaymentData;

public class PaymentDataDao {

	private static final int SECTIONAL_INCREMENTAL_VALUE = 3;

	private static final String[] getOrdersByAccNumberAndOrderDate = {
			"//orders/order[assetElements/orderDate ='",
			"'][assetElements/debtorAccountDetails/accountNumber='",
			"'][position()= ", " to ", "]&wrap=no" };

	private static final String getPaymentsByAccNumberAndOrderDateString = "/*[local-name()='paymentsData']/*[local-name()='payment'][*[local-name()='debtorAccountDetails']/*[local-name()='accountNumber']='111-0000000000000-00' or *[local-name()='creditorAccountDetails']/*[local-name()='accountNumber']='111-0000000000000-00'][*[local-name()='orderDate']='2006-05-04'][position()= 1 to 3]&wrap=no";

	// private static final String getPaymentsByAccNumberAndOrderDateString =
	// "/*[local-name()='paymentsData']/*[local-name()='payment'][*[local-name()='debtorAccountDetails']/*[local-name()='accountNumber']='111-0000000000000-00'][*[local-name()='orderDate']='2006-05-04'][position()= 1 to 3]&wrap=no";

	// private static final String[] getPaymentsByAccNumberAndOrderDate = {
	// "/*[local-name()='paymentsData']/*[local-name()='payment'][*[local-name()='debtorAccountDetails']/*[local-name()='accountNumber']='",
	// "'][*[local-name()='orderDate']='","'][position()= ", " to ", "]&wrap=no"
	// };

	// private static final String[] getPaymentsByAccNumberAndOrderDate = {
	// "/*[local-name()='paymentsData']/*[local-name()='payment'][*[local-name()='debtorAccountDetails']/*[local-name()='accountNumber']='",
	// "'][*[local-name()='orderDate']='","'][position()= ", " to ", "]&wrap=no"
	// };

//	private static final String[] getPaymentsByAccNumberAndOrderDate = {
//			"/*[local-name()='paymentsData']/*[local-name()='payment'][*[local-name()='debtorAccountDetails']/*[local-name()='accountNumber']='",
//			"' or *[local-name()='creditorAccountDetails']/*[local-name()='accountNumber']='",
//			"'][*[local-name()='orderDate']='", "'][position()= ", " to ",
//			"]&wrap=no" };
	
	private static final String[] getPaymentsByAccNumberAndOrderDate = {
		"/*[local-name()='paymentsData']/*[local-name()='paymentData'][*[local-name()='debtorAccountDetails']/*[local-name()='accountNumber']='",
		"' or *[local-name()='creditorAccountDetails']/*[local-name()='accountNumber']='",
		"'][*[local-name()='orderDate']='", "'][position()= ", " to ",
		"]&wrap=no" };

	// private static final String getMjauData =
	// "/*[local-name()='paymentData']";
	// /*[local-name()='workbook']/*[local-name()='sheets']/*[local-name()='sheet'][1]

	private static final String[] getCompanyBalanceQuery = {
			"//*[local-name()='company'][*[local-name()='accountNumber']='",
			"']/*[local-name()='balance']/text()&wrap=no" };

	public static void main(String[] args) {

		File file = new File("src/main/resources/");
		//
		try {
			RESTUtil.dropSchema("payments");
			RESTUtil.createSchema("payments");

			if (new File(file, "payments.xml").exists()) {
				System.out.println("fajl payments.xml postoji");
			}

			RESTUtil.createResource("payments", "payments.xml",
					new FileInputStream(new File(file, "payments.xml")));

//			System.out
//					.println("test--------------------------------------------------");
//			String ret = RESTUtil.readString(RESTUtil.retrieveResource(
//					getPaymentsByAccNumberAndOrderDateString, "payments",
//					RequestMethod.GET));
//			System.out.println(ret);
//			System.out
//					.println("test--------------------------------------------------");

			System.out.println(getPayments("2006-05-04",
					"111-0000000000000-00", 1));
//			List<PaymentData> payments = ParserUtil
//					.transformStringsIntoJAXB(getPayments("2006-05-04",
//							"111-0000000000000-00", 1));
			System.out.println("najnovi test");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	public static String getPayments(String date, String accountNumber,
//			int statementNumber) {
//		String ret = null;
//
//		int maxOffSetValue = statementNumber * SECTIONAL_INCREMENTAL_VALUE;
//		int minOffSetValue = maxOffSetValue - SECTIONAL_INCREMENTAL_VALUE + 1;
//
//		// String q = getOrdersByAccNumberAndOrderDate[0];
//		String q = getPaymentsByAccNumberAndOrderDate[0] + accountNumber
//				+ getPaymentsByAccNumberAndOrderDate[1] + accountNumber
//				+ getPaymentsByAccNumberAndOrderDate[2] + date
//				+ getPaymentsByAccNumberAndOrderDate[3] + minOffSetValue
//				+ getPaymentsByAccNumberAndOrderDate[4] + maxOffSetValue
//				+ getPaymentsByAccNumberAndOrderDate[5];
//
//		System.out.println("Query :   " + q + "\n");
//
//		try {
//			ret = RESTUtil.readString(RESTUtil.retrieveResource(q, "payments",
//					RequestMethod.GET));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return ret;
//
//	}
	
	public static List<PaymentData> getPayments(String date, String accountNumber,
			int statementNumber) {
//		List<PaymentData> retList; //= new ArrayList<PaymentData>();
		List<PaymentData> retList = new ArrayList<PaymentData>();
		
		int maxOffSetValue = statementNumber * SECTIONAL_INCREMENTAL_VALUE;
		int minOffSetValue = maxOffSetValue - SECTIONAL_INCREMENTAL_VALUE + 1;

		// String q = getOrdersByAccNumberAndOrderDate[0];
		String q = getPaymentsByAccNumberAndOrderDate[0] + accountNumber
				+ getPaymentsByAccNumberAndOrderDate[1] + accountNumber
				+ getPaymentsByAccNumberAndOrderDate[2] + date
				+ getPaymentsByAccNumberAndOrderDate[3] + minOffSetValue
				+ getPaymentsByAccNumberAndOrderDate[4] + maxOffSetValue
				+ getPaymentsByAccNumberAndOrderDate[5];

		System.out.println("Query :   " + q + "\n");

		try {
			String queryResult = RESTUtil.readString(RESTUtil.retrieveResource(q, "payments",
					RequestMethod.GET));
			for (String xmlRecord : ParserUtil.getPayments(queryResult)) {
				
				//PaymentData payment = (PaymentData) ParserUtil.transformStringIntoJAXBeans(xmlRecord, PaymentData.class);
				PaymentData payment = ParserUtil.transformStringIntoJAXBeans(xmlRecord, PaymentData.class);
				retList.add(payment);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retList;
	}
}
