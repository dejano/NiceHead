package rs.ac.uns.ftn.xws.dao;

import java.io.File;
import java.math.BigDecimal;

import rs.ac.uns.ftn.xws.dao.util.ParserUtil;
import rs.ac.uns.ftn.xws.dao.util.RESTUtil;
import rs.ac.uns.ftn.xws.dao.util.RequestMethod;
import rs.ac.uns.ftn.xws.generated.po.PaymentOrder;
import rs.ac.uns.ftn.xws.misc.BankConstants;
import rs.ac.uns.ftn.xws.misc.XmlHelper;

public class PaymentOrderDataDao {
	
	
	private static final String[] insertPaymentOrderQuery = {"insert node ",
		" into //*[local-name()='paymentOrders']" };
	
	private static final String[] getPaymentOrders = {
		"/*[local-name()='paymentOrders']/*[local-name()='paymentOrder']&wrap=no" };
	
	private static final String[] getPaymentOrderByMessageId = {
	"/*[local-name()='paymentOrders']/*[local-name()='paymentOrder'][@messageId='","']&wrap=no" };
	
	
	public static void main(String[] args) throws Exception{
		
//		 RESTUtil.dropSchema("bank");
//		 RESTUtil.createSchema(BankConstants.BANK_NAME);
		
		File file = new File("src/main/resources/");
//		RESTUtil.deleteResource(BankConstants.BANK_NAME, "paymentOrders.xml");
//		RESTUtil.createResource(BankConstants.BANK_NAME, "paymentOrders.xml",
//				new FileInputStream(new File(file, "paymentOrders.xml")));

		PaymentOrder po = new PaymentOrder();
		po.setAmount(BigDecimal.TEN);
		po.setDebtor("vuksa");
		po.setCreditor("baki");
		po.setMessageId("123");
		addPaymentOrder(po);
		System.out.println(getPaymentOrders());
		getPaymentOrder("123");
		System.out.println(getPaymentOrders());
	}
	
	public static void addPaymentOrder (PaymentOrder po) {
		String poXml = XmlHelper.marshall(po);
		
		String q = insertPaymentOrderQuery[0] + poXml + insertPaymentOrderQuery[1];

		try {
			RESTUtil.retrieveResourcePost(q, BankConstants.BANK_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getPaymentOrders() {
		String retVal = "";
		try {
			retVal = RESTUtil.readString(RESTUtil.retrieveResource(getPaymentOrders[0], BankConstants.BANK_NAME, RequestMethod.GET));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retVal;
	}

	public static PaymentOrder getPaymentOrder (String messageId) {
		PaymentOrder retVal = null;
		
		String query = getPaymentOrderByMessageId[0] + messageId + getPaymentOrderByMessageId[1];
		
		try {
			String xmlRecord = RESTUtil.readString(RESTUtil.retrieveResource(query, BankConstants.BANK_NAME, RequestMethod.GET));
			retVal = ParserUtil.transformStringIntoJAXBeans(xmlRecord, PaymentOrder.class);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return retVal;
	}
}
	
