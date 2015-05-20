package rs.ac.uns.ftn.xws.util;

import rs.ac.uns.ftn.xws.dao.MessageIdDao;
import rs.ac.uns.ftn.xws.generated.ClearingConfirmMessage;
import rs.ac.uns.ftn.xws.generated.Mt102;
import rs.ac.uns.ftn.xws.generated.Mt103;
import rs.ac.uns.ftn.xws.generated.Mt900;
import rs.ac.uns.ftn.xws.generated.Mt910;
import rs.ac.uns.ftn.xws.generated.RtgsConfirmMessage;

public class ObjectFactory {

	public static Mt900 getMt900(Mt103 mt103) {
		Mt900 ret = new Mt900();

		ret.setMessageId(MessageIdDao.getMessageId());
		ret.setAmount(mt103.getAmount());
		ret.setCurrencyCode(mt103.getCurrencyCode());
		ret.setCurrencyDate(mt103.getCurrencyDate());
		ret.setDebtorBankDetails(mt103.getDebtorBankDetails());
		ret.setPaymentOrderId(mt103.getMessageId());

		return ret;
	}

	public static Mt900 getMt900(Mt102 mt102) {
		Mt900 ret = new Mt900();

		ret.setMessageId(MessageIdDao.getMessageId());
		ret.setAmount(mt102.getTotalAmount());
		ret.setCurrencyCode(mt102.getCurrencyCode());
		ret.setCurrencyDate(mt102.getCurrencyDate());
		ret.setDebtorBankDetails(mt102.getDebtorBankDetails());
		ret.setPaymentOrderId(mt102.getMessageId());

		return ret;
	}

	public static RtgsConfirmMessage getRtgsConfirmMessage(Mt103 mt103) {
		RtgsConfirmMessage ret = new RtgsConfirmMessage();

		ret.setMt103(mt103);

		Mt910 mt910 = new Mt910();
		mt910.setMessageId(MessageIdDao.getMessageId());
		mt910.setAmount(mt103.getAmount());
		mt910.setCreditorBankDetails(mt103.getCreditorBankDetails());
		mt910.setCurrencyCode(mt103.getCurrencyCode());
		mt910.setCurrencyDate(mt103.getCurrencyDate());
		mt910.setPaymentOrderId(mt103.getMessageId());
		ret.setMt910(mt910);

		return ret;
	}

	public static ClearingConfirmMessage getClearingConfirmMessage(Mt102 mt102) {
		ClearingConfirmMessage ret = new ClearingConfirmMessage();

		ret.setMt102(mt102);

		Mt910 mt910 = new Mt910();
		mt910.setMessageId(MessageIdDao.getMessageId());
		mt910.setAmount(mt102.getTotalAmount());
		mt910.setCreditorBankDetails(mt102.getCreditorBankDetails());
		mt910.setCurrencyCode(mt102.getCurrencyCode());
		mt910.setCurrencyDate(mt102.getCurrencyDate());
		mt910.setPaymentOrderId(mt102.getMessageId());
		ret.setMt910(mt910);

		return ret;
	}

	private ObjectFactory() {
	}
}
