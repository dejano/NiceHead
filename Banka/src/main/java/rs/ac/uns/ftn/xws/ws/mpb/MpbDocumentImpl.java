package rs.ac.uns.ftn.xws.ws.mpb;

import java.math.BigDecimal;
import java.util.logging.Logger;

import javax.ejb.Stateless;

import rs.ac.uns.ftn.xws.dao.CompanyDataDao;
import rs.ac.uns.ftn.xws.dao.Mt102DataDao;
import rs.ac.uns.ftn.xws.domain.mpb.Mt102Ref;
import rs.ac.uns.ftn.xws.generated.cmn.Payment;
import rs.ac.uns.ftn.xws.generated.mp.ClearingApprovalMessage;
import rs.ac.uns.ftn.xws.generated.mp.Mt102;
import rs.ac.uns.ftn.xws.generated.mp.Mt103;
import rs.ac.uns.ftn.xws.generated.mp.Mt900;
import rs.ac.uns.ftn.xws.generated.mp.RtgsApprovalMessage;
import rs.ac.uns.ftn.xws.generated.po.PaymentOrder;

@Stateless
@javax.jws.WebService(serviceName = "MpbDocumentService", portName = "MpbDocumentPort", targetNamespace = "http://www.ftn.uns.ac.rs/xws/ws/mpb", wsdlLocation = "file:/C:/Users/nikola42/Documents/Fakultet/XWS/projekat/NiceHead/Banka/WEB-INF/wsdl/mpb.wsdl", endpointInterface = "rs.ac.uns.ftn.xws.ws.mpb.MpbDocument")
public class MpbDocumentImpl implements MpbDocument {

	private static final Logger LOG = Logger.getLogger(MpbDocumentImpl.class
			.getName());

	public void clearingDebit(Mt900 clearingDebitPart) {
		LOG.info("Executing operation clearingDebit");

		LOG.info("Clearing message mt102 : " + clearingDebitPart.getPaymentOrderId());
		
		Mt102Ref mt102Ref = Mt102DataDao.getMt102Ref(clearingDebitPart.getPaymentOrderId());

		for (String paymentOrderId : mt102Ref.getPaymentOrderId()) {
			PaymentOrder paymentOrder = null; // TODO baki
			String accountNumber = paymentOrder.getDebtorAccountDetails().getAccountNumber();
			BigDecimal amount = paymentOrder.getAmount();

			BigDecimal balance = CompanyDataDao
					.getCompanyBalance(accountNumber);
			BigDecimal reservedAmount = CompanyDataDao
					.getCompanyReservedAmount(accountNumber);

			CompanyDataDao.updateCompanyBalance(accountNumber,
					balance.subtract(amount));
			CompanyDataDao.updateCompanyReservedAmount(accountNumber,
					reservedAmount.subtract(amount));

			// TODO save payment
		}
	}

	public void rtgsApproval(RtgsApprovalMessage rtgsApprovalPart) {
		LOG.info("Executing operation rtgsApproval");

		Mt103 mt103 = rtgsApprovalPart.getMt103();

		String accountNumber = mt103.getCreditorAccountDetails()
				.getAccountNumber();
		BigDecimal amount = mt103.getAmount();

		BigDecimal balance = CompanyDataDao.getCompanyBalance(accountNumber);

		LOG.info("Updating balance of client with account number : "
				+ accountNumber);

		// pay-in funds to creditor account
		CompanyDataDao.updateCompanyBalance(accountNumber, balance.add(amount));

		// TODO save payment
	}

	public void clearingApproval(ClearingApprovalMessage clearingApprovalPart) {
		LOG.info("Executing operation clearingApproval");

		Mt102 mt102 = clearingApprovalPart.getMt102();

		// iterate through payments and pay in funds to creditor accounts
		for (Payment payment : mt102.getPayments().getPayment()) {
			String accountNumber = payment.getCreditorAccountDetails()
					.getAccountNumber();
			BigDecimal amount = payment.getAmount();

			BigDecimal balance = CompanyDataDao
					.getCompanyBalance(accountNumber);

			LOG.info("Updating balance of client with account number : "
					+ accountNumber);

			// pay-in funds
			CompanyDataDao.updateCompanyBalance(accountNumber,
					balance.add(amount));

			// TODO save payment
		}
	}

}
