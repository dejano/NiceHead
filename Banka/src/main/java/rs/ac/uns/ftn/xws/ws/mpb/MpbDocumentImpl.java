package rs.ac.uns.ftn.xws.ws.mpb;

import java.math.BigDecimal;
import java.util.logging.Logger;

import javax.ejb.Stateless;

import rs.ac.uns.ftn.xws.dao.CompanyDataDao;
import rs.ac.uns.ftn.xws.generated.ClearingApprovalMessage;
import rs.ac.uns.ftn.xws.generated.Mt102;
import rs.ac.uns.ftn.xws.generated.Mt103;
import rs.ac.uns.ftn.xws.generated.Mt900;
import rs.ac.uns.ftn.xws.generated.Payment;
import rs.ac.uns.ftn.xws.generated.RtgsApprovalMessage;

@Stateless
@javax.jws.WebService(serviceName = "MpbDocumentService", portName = "MpbDocumentPort", targetNamespace = "http://www.ftn.uns.ac.rs/xws/ws/mpb", wsdlLocation = "file:/C:/Users/nikola42/Documents/Fakultet/XWS/projekat/NiceHead/Banka/WEB-INF/wsdl/mpb.wsdl", endpointInterface = "rs.ac.uns.ftn.xws.ws.mpb.MpbDocument")
public class MpbDocumentImpl implements MpbDocument {

	private static final Logger LOG = Logger.getLogger(MpbDocumentImpl.class
			.getName());

	public void clearingDebit(Mt900 clearingDebitPart) {
		LOG.info("Executing operation clearingDebit");

		// TODO use mt900.paymentOrderId to get and resolve mt102
		// foreach payment : mt102.payments
		String mockAccountNumber = "222-2222222222222-22";
		BigDecimal mockAmount = clearingDebitPart.getAmount();

		BigDecimal balance = CompanyDataDao
				.getCompanyBalance(mockAccountNumber);
		BigDecimal reservedAmount = CompanyDataDao
				.getCompanyReservedAmount(mockAccountNumber);

		CompanyDataDao.updateCompanyBalance(mockAccountNumber,
				balance.subtract(mockAmount));
		CompanyDataDao.updateCompanyReservedAmount(mockAccountNumber,
				reservedAmount.subtract(mockAmount));

		// TODO save payment
	}

	public void rtgsApproval(RtgsApprovalMessage rtgsApprovalPart) {
		LOG.info("Executing operation rtgsApproval");

		Mt103 mt103 = rtgsApprovalPart.getMt103();

		String accountNumber = mt103.getCreditorAccountDetails()
				.getAccountNumber();
		BigDecimal amount = mt103.getAmount();

		BigDecimal balance = CompanyDataDao.getCompanyBalance(accountNumber);

		// pay-in funds to creditor account
		CompanyDataDao.updateCompanyBalance(accountNumber, balance.add(amount));

		// TODO save payment
	}

	public void clearingApproval(ClearingApprovalMessage clearingApprovalPart) {
		LOG.info("Executing operation clearingApproval");

		Mt102 mt102 = clearingApprovalPart.getMt102();

		// iterate through payments and pay in funds to creditor accounts
		for (Payment payment : mt102.getPayments().getPayments()) {
			String accountNumber = payment.getCreditorAccountDetails()
					.getAccountNumber();
			BigDecimal amount = payment.getAmount();

			BigDecimal balance = CompanyDataDao
					.getCompanyBalance(accountNumber);

			// pay-in funds
			CompanyDataDao.updateCompanyBalance(accountNumber,
					balance.add(amount));

			// TODO save payment
		}
	}

}
