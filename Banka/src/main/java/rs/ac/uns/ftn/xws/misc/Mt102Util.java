package rs.ac.uns.ftn.xws.misc;

import java.math.BigDecimal;
import java.util.List;

import rs.ac.uns.ftn.xws.dao.PaymentOrderDataDao;
import rs.ac.uns.ftn.xws.dao.util.ParserUtil;
import rs.ac.uns.ftn.xws.domain.mpb.Mt102Ref;
import rs.ac.uns.ftn.xws.generated.bs.Statement;
import rs.ac.uns.ftn.xws.generated.cmn.BankDetails;
import rs.ac.uns.ftn.xws.generated.mp.Mt102;
import rs.ac.uns.ftn.xws.generated.mp.Mt102Payment;
import rs.ac.uns.ftn.xws.generated.mp.Mt102.Payments;
import rs.ac.uns.ftn.xws.generated.mp.ObjectFactory;
import rs.ac.uns.ftn.xws.generated.po.PaymentOrder;

public class Mt102Util {
	
	/*
	 * This method creates MT102 based on 
	 * unprocessed payment orders stored in XML DB.
	 */
	public static Mt102 createMt102(BankDetails creditorBankDetails, BankDetails debtorBankDetails, String messageId) {
		Mt102 mt102 = null;
		BigDecimal totalAmount = BigDecimal.ZERO;
		List<PaymentOrder> paymentOrders = ParserUtil.transformXmlListIntoPaymentOrderList(PaymentOrderDataDao.getPaymentOrders());
		
		if(paymentOrders.size()>0) {
			// kreiraj payments preko object factory-a i setuj ga u mt102
			ObjectFactory of = new ObjectFactory();
			mt102 = of.createMt102();
			mt102.setPayments(of.createMt102Payments());
			
			mt102.setCreditorBankDetails(creditorBankDetails);
			mt102.setDebtorBankDetails(debtorBankDetails);
			mt102.setMessageId(messageId);
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
		
		Mt102 mt102 =  createMt102(bd1, bd2, "12321");
		Mt102Ref mt102ref = Mt102RefUtil.createMt102Ref(mt102.getPayments().getPayment(), "19191");
		
		System.out.println(mt102.getCurrencyDate());
	}

}
