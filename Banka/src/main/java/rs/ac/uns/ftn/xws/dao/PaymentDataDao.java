package rs.ac.uns.ftn.xws.dao;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.xws.dao.util.ParserUtil;
import rs.ac.uns.ftn.xws.dao.util.RESTUtil;
import rs.ac.uns.ftn.xws.dao.util.RequestMethod;
import rs.ac.uns.ftn.xws.domain.bsb.PaymentData;
import rs.ac.uns.ftn.xws.generated.po.PaymentOrder;
import rs.ac.uns.ftn.xws.misc.BankConstants;
import rs.ac.uns.ftn.xws.misc.XmlHelper;

public class PaymentDataDao {

	private static final int SECTIONAL_INCREMENTAL_VALUE = 3;

	private static final String[] insertPaymentDataQuery = {"insert node ",
	" into //*[local-name()='paymentsData']" };
	
	private static final String[] getOrdersByAccNumberAndOrderDate = {
			"//orders/order[assetElements/orderDate ='",
			"'][assetElements/debtorAccountDetails/accountNumber='",
			"'][position()= ", " to ", "]&wrap=no" };

	private static final String getPaymentsByAccNumberAndOrderDateString = "/*[local-name()='paymentsData']/*[local-name()='payment'][*[local-name()='debtorAccountDetails']/*[local-name()='accountNumber']='111-0000000000000-00' or *[local-name()='creditorAccountDetails']/*[local-name()='accountNumber']='111-0000000000000-00'][*[local-name()='orderDate']='2006-05-04'][position()= 1 to 3]&wrap=no";

	private static final String[] getPaymentsByAccNumberAndOrderDate = {
		"/*[local-name()='paymentsData']/*[local-name()='paymentData'][*[local-name()='debtorAccountDetails']/*[local-name()='accountNumber']='",
		"' or *[local-name()='creditorAccountDetails']/*[local-name()='accountNumber']='",
		"'][*[local-name()='orderDate']='", "'][position()= ", " to ",
		"]&wrap=no" };

	private static final String[] getCompanyBalanceQuery = {
			"//*[local-name()='company'][*[local-name()='accountNumber']='",
			"']/*[local-name()='balance']/text()&wrap=no" };

	public static void main(String[] args) {

//		File file = new File("src/main/resources/");
		try {
			
//			RESTUtil.dropSchema("bank");
//			 RESTUtil.createSchema(BankConstants.BANK_NAME);
			
			File file = new File("src/main/resources/");
			RESTUtil.deleteResource(BankConstants.BANK_NAME, "payments.xml");
			RESTUtil.createResource(BankConstants.BANK_NAME, "payments.xml",
					new FileInputStream(new File(file, "payments.xml")));
			
			RESTUtil.dropSchema("payments");
			RESTUtil.createSchema("payments");

			if (new File(file, "payments.xml").exists()) {
				System.out.println("fajl payments.xml postoji");
			}

			RESTUtil.createResource("payments", "payments.xml",
					new FileInputStream(new File(file, "payments.xml")));

			System.out.println(getPayments("2006-05-04",
					"111-0000000000000-00", 1));
			System.out.println("najnovi test");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
			
			System.out.println(queryResult);
			
			for (String xmlRecord : getPayments(queryResult)) {
//				PaymentData payment = (PaymentData) ParserUtil.transformStringIntoJAXBeans(xmlRecord, PaymentData.class);
				PaymentData payment = ParserUtil.transformStringIntoJAXBeans(xmlRecord, PaymentData.class);
				retList.add(payment);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retList;
	}
	
	public static void addPayment(PaymentData paymentData) 
	{
		String paymentXml = XmlHelper.marshall(paymentData);
		
		String q = insertPaymentDataQuery[0] + paymentXml + insertPaymentDataQuery[1];

		try {
			RESTUtil.retrieveResourcePost(q, BankConstants.BANK_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
//	public static void addPayment(PaymentOrder po, BigDecimal previousBalance, BigDecimal newBalance) 
//	{
//		PaymentData newPayment = ObjectConverter.PaymentOrderToPaymentData(po, previousBalance, newBalance);
//		
//		String paymentXml = XmlHelper.marshall(newPayment);
//		
//		String q = insertPaymentDataQuery[0] + paymentXml + insertPaymentDataQuery[1];
//
//		try {
//			RESTUtil.retrieveResourcePost(q, BankConstants.BANK_NAME);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
	
	public static List<String> getPayments(String input) {
		List<String> retList = new ArrayList<String>();

		String[] parts = input.split("</bsb:paymentData>");
		for (String string : parts) {
			retList.add(string + "</bsb:paymentData>");
		}

		// retList = new ArrayList<String>(Arrays.asList(parts));
		return retList;
	}
}
