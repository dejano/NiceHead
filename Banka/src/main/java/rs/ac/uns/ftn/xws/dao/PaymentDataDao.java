package rs.ac.uns.ftn.xws.dao;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.w3c.dom.Document;

import rs.ac.uns.ftn.xws.dao.util.ParserUtil;
import rs.ac.uns.ftn.xws.dao.util.RESTUtil;
import rs.ac.uns.ftn.xws.dao.util.RequestMethod;
import rs.ac.uns.ftn.xws.generated.cmn.Payment;

public class PaymentDataDao {

	private static final int SECTIONAL_INCREMENTAL_VALUE = 3;

//	private static final String[] getOrderValueOfDebtor = {
//			"//order/assetElements[debtor='", "']/value/text()&wrap=no" };

	// private static final String[] getOrdersByAccNumberAndOrderDate = {
	// "//orders/order[assetElements/orderDate ='2015-05-21'][assetElements/debtorAccountDetails/accountNumber='310-0000000000111-83']&wrap=no"
	// };

	private static final String[] getOrdersByAccNumberAndOrderDate = {
			"//orders/order[assetElements/orderDate ='",
			"'][assetElements/debtorAccountDetails/accountNumber='",
			"'][position()= ", " to ", "]&wrap=no" };

//	private static final String[] getPaymentsByAccNumberAndOrderDate = {
//		"//bsb:paymentsData/bsb:payment[cmn:orderDate ='",
//		"'][cmn:debtorAccountDetails/cmn:accountNumber='",
//		"'][position()= ", " to ", "]&wrap=no" };
	
	private static final String getPaymentsByAccNumberAndOrderDateString = 
	"/*[local-name()='paymentsData']/*[local-name()='payment'][*[local-name()='debtorAccountDetails']/*[local-name()='accountNumber']='111-0000000000000-00'][*[local-name()='orderDate']='2006-05-04'][position()= 1 to 3]&wrap=no";
	
	private static final String[] getPaymentsByAccNumberAndOrderDate = {
	"/*[local-name()='paymentsData']/*[local-name()='payment'][*[local-name()='debtorAccountDetails']/*[local-name()='accountNumber']='",
	"'][*[local-name()='orderDate']='","'][position()= ", " to ", "]&wrap=no" };
	
	
//	private static final String getMjauData = "/*[local-name()='paymentData']";
	///*[local-name()='workbook']/*[local-name()='sheets']/*[local-name()='sheet'][1]
	
	private static final String[] getCompanyBalanceQuery = {
		"//*[local-name()='company'][*[local-name()='accountNumber']='",
		"']/*[local-name()='balance']/text()&wrap=no" };

	public static void main(String[] args) {

		File file = new File("src/main/resources/");
		//
		try {
			RESTUtil.dropSchema("payments");
			RESTUtil.createSchema("payments");
			
			if(new File(file,"payments.xml").exists()) {
				System.out.println("fajl payments.xml postoji");
			}
			RESTUtil.createResource("payments", "payments.xml",
					new FileInputStream(new File(file, "payments.xml")));
			
			//
			System.out.println(getPayments("2006-05-04", "111-0000000000000-00", 1));
			List<Payment> payments = ParserUtil.transformStringsIntoJAXB(getPayments("2006-05-04", "111-0000000000000-00", 1));
//			if (payments.get(0).getCreditor()==null) 
//				System.out.println("SKEEENJ!");
			System.out.println("MJAAAU");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getPayments(String date,String accountNumber, int statementNumber) {
		String ret = null;

		int maxOffSetValue = statementNumber * SECTIONAL_INCREMENTAL_VALUE;
		int minOffSetValue = maxOffSetValue - SECTIONAL_INCREMENTAL_VALUE + 1;

		// String q = getOrdersByAccNumberAndOrderDate[0];
		String q = getPaymentsByAccNumberAndOrderDate[0] + accountNumber
				+ getPaymentsByAccNumberAndOrderDate[1] + date
				+ getPaymentsByAccNumberAndOrderDate[2] + minOffSetValue +
				getPaymentsByAccNumberAndOrderDate[3] + maxOffSetValue +
				getPaymentsByAccNumberAndOrderDate[4];
		
		System.out.println("Query :   "+q+"\n");
		
		try {
			ret = RESTUtil.readString(RESTUtil.retrieveResource(q, "payments",
					RequestMethod.GET));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	
	}
	
//	public static String getOrders(String date, String accountNumber,
//			int sectionalNumber) {
//		// public static String getOrders() {
//		String ret = null;
//
//		int maxOffSetValue = sectionalNumber * SECTIONAL_INCREMENTAL_VALUE;
//		int minOffSetValue = maxOffSetValue - SECTIONAL_INCREMENTAL_VALUE + 1;
//
//		// String q = getOrdersByAccNumberAndOrderDate[0];
//		String q = getOrdersByAccNumberAndOrderDate[0] + date
//				+ getOrdersByAccNumberAndOrderDate[1] + accountNumber
//				+ getOrdersByAccNumberAndOrderDate[2] + minOffSetValue +
//				getOrdersByAccNumberAndOrderDate[3] + maxOffSetValue +
//				getOrdersByAccNumberAndOrderDate[4];
//		
//		System.out.println("Query :   "+q+"\n");
//		
//		try {
//			ret = RESTUtil.readString(RESTUtil.retrieveResource(q, "orders",
//					RequestMethod.GET));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return ret;
//	}

}
