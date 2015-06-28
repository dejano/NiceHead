package rs.ac.uns.ftn.xws.misc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rs.ac.uns.ftn.xws.dao.Mt102DataDao;
import rs.ac.uns.ftn.xws.dao.PaymentOrderDataDao;
import rs.ac.uns.ftn.xws.dao.util.ParserUtil;
import rs.ac.uns.ftn.xws.domain.mpb.Mt102Ref;
import rs.ac.uns.ftn.xws.generated.bs.Statement;
import rs.ac.uns.ftn.xws.generated.cmn.BankDetails;
import rs.ac.uns.ftn.xws.generated.cmn.Message;
import rs.ac.uns.ftn.xws.generated.mp.Mt102;
import rs.ac.uns.ftn.xws.generated.mp.Mt102Payment;
import rs.ac.uns.ftn.xws.generated.mp.Mt102.Payments;
import rs.ac.uns.ftn.xws.generated.mp.ObjectFactory;
import rs.ac.uns.ftn.xws.generated.po.PaymentOrder;

public class Mt102Util {
	
	
	/*
	 * Key = Bank Code of Banks who are going to get paid out, paymentOrders (payouts)
	 */
	public static Map<String, List<PaymentOrder>> getPaymentOrders() {
		Map<String, List<PaymentOrder>> retMap = new HashMap<String, List<PaymentOrder>>();
		
		List<PaymentOrder> paymentOrders = ParserUtil.transformXmlListIntoPaymentOrderList(PaymentOrderDataDao.getPaymentOrders());
		
		for (PaymentOrder paymentOrder : paymentOrders) {
			String creditorBankCode = paymentOrder.getCreditorAccountDetails().getAccountNumber().substring(0,3);
			
			if (!retMap.containsKey(creditorBankCode)) {
				retMap.put(creditorBankCode, new ArrayList<PaymentOrder>());
				retMap.get(creditorBankCode).add(paymentOrder);
			}
			else {
				retMap.get(creditorBankCode).add(paymentOrder);
			}
		}
		
		return retMap;
	}
	
	
	/*
	 * This method creates MT102 based on 
	 * unprocessed payment orders stored in XML DB.
	 */
	public static Mt102 createMt102(BankDetails creditorBankDetails, BankDetails debtorBankDetails, Message message) {
		Mt102 mt102 = null;
		BigDecimal totalAmount = BigDecimal.ZERO;
		
		String debtorBankCode = debtorBankDetails.getBankClearingAccountNumber().substring(0,3);
		List<PaymentOrder> paymentOrders = getPaymentOrders().get(debtorBankCode);
		
		if(paymentOrders.size()>0) {
			// kreiraj payments preko object factory-a i setuj ga u mt102
			ObjectFactory of = new ObjectFactory();
			mt102 = of.createMt102();
			mt102.setPayments(of.createMt102Payments());
			
			mt102.setCreditorBankDetails(creditorBankDetails);
			mt102.setDebtorBankDetails(debtorBankDetails);
			mt102.setMessageId(message.getMessageId());
			mt102.setCertificateRef(message.getCertificateRef());
			mt102.setTimestamp(message.getTimestamp());
			// hardcodovano settovanje mt102 header atributa iz paymentOrders[0]
			mt102.setCurrencyCode(paymentOrders.get(0).getCurrencyCode());
			mt102.setCurrencyDate(paymentOrders.get(0).getCurrencyDate());
			mt102.setDate(paymentOrders.get(0).getOrderDate()); // trebalo bi new Date() -> XMLCalendar

			for (PaymentOrder paymentOrder : paymentOrders) {
				totalAmount = totalAmount.add(paymentOrder.getAmount());

				Mt102Payment mt102payment = ObjectMapper.PaymentOrderToMt102Payment(paymentOrder);
				mt102.getPayments().getPayment().add(mt102payment);
			}
			
			mt102.setTotalAmount(totalAmount);

			//mt102ref messageId isti kao mt102 messageId?
			Mt102Ref mt102Ref = Mt102RefUtil.createMt102Ref(mt102.getPayments().getPayment(), message.getMessageId());
			Mt102DataDao.insertMt102(XmlHelper.marshall(mt102Ref));
			System.out.println(Mt102DataDao.getMt102Ref(message.getMessageId()).getMessageId());
		}
		
		return mt102;
	}
	
	
	
	public static void main(String[] args) {
		
		BankDetails bd1 = new BankDetails();
		bd1.setSwiftCode("CONARS23");
		bd1.setBankClearingAccountNumber("223-2222222222222-22");

		BankDetails bd2 = new BankDetails();
		bd2.setSwiftCode("CONARS22");
		bd2.setBankClearingAccountNumber("223-2222333222222-33");
		
		//Mt102 mt102 =  createMt102(bd1, bd2, "12321");
		//Mt102Ref mt102ref = Mt102RefUtil.createMt102Ref(mt102.getPayments().getPayment(), "19191");
		
		//System.out.println(mt102.getCurrencyDate());
	}

}
