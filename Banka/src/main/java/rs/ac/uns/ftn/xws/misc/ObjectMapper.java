package rs.ac.uns.ftn.xws.misc;

import java.math.BigDecimal;

import javax.xml.datatype.XMLGregorianCalendar;

import rs.ac.uns.ftn.xws.domain.bsb.PaymentData;
import rs.ac.uns.ftn.xws.domain.mpb.Mt102Ref;
import rs.ac.uns.ftn.xws.generated.mp.Mt102;
import rs.ac.uns.ftn.xws.generated.mp.Mt102Payment;
import rs.ac.uns.ftn.xws.generated.mp.Mt103;
import rs.ac.uns.ftn.xws.generated.po.PaymentOrder;

public class ObjectMapper {

	public static Mt102Ref getMt102Ref(Mt102 mt102){
		Mt102Ref ret = new Mt102Ref();
		
		ret.setMessageId(mt102.getMessageId());
		
		for (Mt102Payment payment : mt102.getPayments().getPayment()) {
			ret.getPaymentOrderId().add(payment.getPaymentOrderId());
		}
		
		return ret;
	}
	
	public static PaymentData PaymentOrderToPaymentData(PaymentOrder po, BigDecimal previousBalance, BigDecimal newBalance) {
		PaymentData retVal = new PaymentData();
		
		retVal.setAmount(po.getAmount());
		retVal.setCreditor(po.getCreditor());
		retVal.setCreditorAccountDetails(po.getCreditorAccountDetails());
		retVal.setCurrencyDate(po.getCurrencyDate());
		retVal.setDebtor(po.getDebtor());
		retVal.setDebtorAccountDetails(po.getDebtorAccountDetails());
		retVal.setOrderDate(po.getOrderDate());
		retVal.setPaymentPurpose(po.getPaymentPurpose());
		retVal.setPreviousBalance(previousBalance);
		retVal.setNewBalance(newBalance);
		
		return retVal;
	}
	
	public static PaymentData Mt103ToPaymentData(Mt103 mt103, BigDecimal previousBalance, BigDecimal newBalance) {
		PaymentData retVal = new PaymentData();
		
		retVal.setAmount(mt103.getAmount());
		retVal.setCreditor(mt103.getCreditor());
		retVal.setCreditorAccountDetails(mt103.getCreditorAccountDetails());
		retVal.setCurrencyDate(mt103.getCurrencyDate());
		retVal.setDebtor(mt103.getDebtor());
		retVal.setDebtorAccountDetails(mt103.getDebtorAccountDetails());
		retVal.setOrderDate(mt103.getOrderDate());
		retVal.setPaymentPurpose(mt103.getPaymentPurpose());
		retVal.setPreviousBalance(previousBalance);
		retVal.setNewBalance(newBalance);
		
		return retVal;
	}

	public static Mt102Payment PaymentOrderToMt102Payment(
			PaymentOrder paymentOrder) {
		Mt102Payment mt102Payment = new Mt102Payment();
		
		mt102Payment.setAmount(paymentOrder.getAmount());
		mt102Payment.setCreditor(paymentOrder.getCreditor());
		mt102Payment.setCreditorAccountDetails(paymentOrder.getCreditorAccountDetails());
		mt102Payment.setDebtor(paymentOrder.getDebtor());
		mt102Payment.setDebtorAccountDetails(paymentOrder.getDebtorAccountDetails());
		mt102Payment.setOrderDate(paymentOrder.getOrderDate());
		mt102Payment.setPaymentPurpose(paymentOrder.getPaymentPurpose());
		mt102Payment.setCurrencyCode(paymentOrder.getCurrencyCode());
		mt102Payment.setPaymentOrderId(paymentOrder.getMessageId());
		
		return mt102Payment;
	}
	
	public static PaymentData PaymentToPaymentData (Mt102Payment payment, BigDecimal previousBalance, BigDecimal newBalance, XMLGregorianCalendar currencyDate ) {
		PaymentData paymentData = new PaymentData();
		
		paymentData.setAmount(payment.getAmount());
		paymentData.setCreditor(payment.getCreditor());
		paymentData.setCreditorAccountDetails(payment.getCreditorAccountDetails());
		paymentData.setDebtor(payment.getDebtor());
		paymentData.setDebtorAccountDetails(payment.getDebtorAccountDetails());
		paymentData.setOrderDate(payment.getOrderDate());
		paymentData.setPaymentPurpose(payment.getPaymentPurpose());
		paymentData.setPreviousBalance(previousBalance);
		paymentData.setNewBalance(newBalance);
		paymentData.setCurrencyDate(currencyDate);
		
		return paymentData;
	}
}
