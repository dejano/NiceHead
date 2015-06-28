package rs.ac.uns.ftn.xws.misc;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import rs.ac.uns.ftn.xws.dao.MessageIdDao;
import rs.ac.uns.ftn.xws.generated.mp.ClearingApprovalMessage;
import rs.ac.uns.ftn.xws.generated.mp.Mt102;
import rs.ac.uns.ftn.xws.generated.mp.Mt103;
import rs.ac.uns.ftn.xws.generated.mp.Mt900;
import rs.ac.uns.ftn.xws.generated.mp.Mt910;
import rs.ac.uns.ftn.xws.generated.mp.RtgsApprovalMessage;

public class ObjectMapper {

	public static Mt900 getMt900(Mt103 mt103) {
		Mt900 ret = new Mt900();

		ret.setMessageId(MessageIdDao.getMessageId());
		// dodato
		ret.setCertificateRef(mt103.getCertificateRef());
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(new Date());
		try {
			ret.setTimestamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		//
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
		//dodato
		ret.setCertificateRef(mt102.getCertificateRef());
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(new Date());
		try {
			ret.setTimestamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		//
		ret.setAmount(mt102.getTotalAmount());
		ret.setCurrencyCode(mt102.getCurrencyCode());
		ret.setCurrencyDate(mt102.getCurrencyDate());
		ret.setDebtorBankDetails(mt102.getDebtorBankDetails());
		ret.setPaymentOrderId(mt102.getMessageId());

		return ret;
	}

	public static RtgsApprovalMessage getRtgsApprovalMessageMessage(Mt103 mt103) {
		RtgsApprovalMessage ret = new RtgsApprovalMessage();

		ret.setMt103(mt103);
		// TODO @nikolina za mt900 pokupim certifiate ref iz mt102/mt103. 
		// Kod mt910 ne mogu tako jer mi treba bank name od druge banke.
		
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

	public static ClearingApprovalMessage getClearingApprovalMessage(Mt102 mt102) {
		ClearingApprovalMessage ret = new ClearingApprovalMessage();

		ret.setMt102(mt102);
		// TODO @nikolina za mt900 pokupim certifiate ref iz mt102/mt103. 
		// Kod mt910 ne mogu tako jer mi treba bank name od druge banke.

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

	private ObjectMapper() {
	}
}
