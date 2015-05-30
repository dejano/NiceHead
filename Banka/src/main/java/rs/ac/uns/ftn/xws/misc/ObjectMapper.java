package rs.ac.uns.ftn.xws.misc;

import rs.ac.uns.ftn.xws.domain.mpb.Mt102Ref;
import rs.ac.uns.ftn.xws.generated.cmn.BankDetails;
import rs.ac.uns.ftn.xws.generated.mp.Mt102;
import rs.ac.uns.ftn.xws.generated.mp.Mt102Payment;
import rs.ac.uns.ftn.xws.generated.mp.Mt103;
import rs.ac.uns.ftn.xws.generated.po.PaymentOrder;

public class ObjectMapper {

	public static Mt102Ref getMt102Ref(Mt102 mt102) {
		Mt102Ref ret = new Mt102Ref();

		ret.setMessageId(mt102.getMessageId());

		for (Mt102Payment payment : mt102.getPayments().getPayment()) {
			ret.getPaymentOrderId().add(payment.getPaymentOrderId());
		}

		return ret;
	}

	public static Mt103 getMt103(PaymentOrder paymentOrder,
			BankDetails debtorBankDetails, BankDetails creditorBankDetails) {
		Mt103 ret = new Mt103();

		ret.setDebtorBankDetails(debtorBankDetails);
		ret.setCreditorBankDetails(creditorBankDetails);
		ret.setDebtor(paymentOrder.getDebtor());
		ret.setPaymentPurpose(paymentOrder.getPaymentPurpose());
		ret.setCreditor(paymentOrder.getCreditor());
		ret.setOrderDate(paymentOrder.getOrderDate());
		ret.setDebtorAccountDetails(paymentOrder.getDebtorAccountDetails());
		ret.setCreditorAccountDetails(paymentOrder.getCreditorAccountDetails());
		ret.setAmount(paymentOrder.getAmount());
		ret.setCurrencyDate(paymentOrder.getCurrencyDate());
		ret.setCurrencyCode(paymentOrder.getCurrencyCode());

		return ret;
	}
}
