package rs.ac.uns.ftn.xws.util;

import rs.ac.uns.ftn.xws.generated.Mt102;
import rs.ac.uns.ftn.xws.generated.Mt103;
import rs.ac.uns.ftn.xws.generated.Mt900;

public class ObjectFactory {

	public static Mt900 getMt900(Mt103 mt103) {
		Mt900 ret = new Mt900();

		// TODO set message id
		ret.setAmount(mt103.getAmount());
		ret.setCurrencyCode(mt103.getCurrencyCode());
		ret.setCurrencyDate(mt103.getCurrencyDate());
		ret.setDebtorBankDetails(mt103.getDebtorBankDetails());
		ret.setPaymentOrderId(mt103.getMessageId());

		return ret;
	}
	
	public static Mt900 getMt900(Mt102 mt102) {
		Mt900 ret = new Mt900();

		// TODO set message id
		ret.setAmount(mt102.getTotalAmount());
		ret.setCurrencyCode(mt102.getCurrencyCode());
		ret.setCurrencyDate(mt102.getCurrencyDate());
		ret.setDebtorBankDetails(mt102.getDebtorBankDetails());
		ret.setPaymentOrderId(mt102.getMessageId());

		return ret;
	}

	private ObjectFactory() {
	}
}
