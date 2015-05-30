package rs.ac.uns.ftn.xws.ws.po;

import java.math.BigDecimal;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.jws.WebService;

import rs.ac.uns.ftn.xws.dao.CompanyDataDao;
import rs.ac.uns.ftn.xws.generated.po.PaymentOrder;
import rs.ac.uns.ftn.xws.misc.BankUtil;

@Stateless
@WebService(serviceName = "PoDocumentService", portName = "PoDocumentPort", targetNamespace = "http://www.ftn.uns.ac.rs/xws/ws/po", wsdlLocation = "file:/C:/Users/nikola42/Documents/Fakultet/XWS/projekat/NiceHead/Banka/WEB-INF/wsdl/po.wsdl", endpointInterface = "rs.ac.uns.ftn.xws.ws.po.PoDocument")
public class PoDocumentImpl implements PoDocument {

	private static final Logger LOG = Logger.getLogger(PoDocumentImpl.class
			.getName());

	public void paymentOrderHandle(PaymentOrder paymentOrderPart)
			throws PoException {
		LOG.info("Executing operation paymentOrderHandle");

		// TODO validate payment order and throw exception if needed
		// TODO add values in PoExceptionEnum

		// case 1 : ista banka
		if (BankUtil.areInTheSameBank(paymentOrderPart
				.getDebtorAccountDetails().getAccountNumber(), paymentOrderPart
				.getCreditorAccountDetails().getAccountNumber())) {
			// TODO provera da li postoje racuni

			transferFunds(paymentOrderPart);
		}

		// case 2 : rtgs

		// case 3 : mt102

		// TODO baki : save payment order if mt102
	}

	private void transferFunds(PaymentOrder paymentOrder) {
		BigDecimal amount = paymentOrder.getAmount();

		String debtorAccountNumber = paymentOrder.getDebtorAccountDetails()
				.getAccountNumber();
		BigDecimal debtorBalance = CompanyDataDao
				.getCompanyBalance(debtorAccountNumber);
		// TODO provera stanja - exception nema dovoljno para
		// TODO exception racun blokiran

		String creditorAccountNumber = paymentOrder.getCreditorAccountDetails()
				.getAccountNumber();
		BigDecimal creditorBalance = CompanyDataDao
				.getCompanyBalance(creditorAccountNumber);

		// transfer funds
		CompanyDataDao.updateCompanyBalance(debtorAccountNumber,
				debtorBalance.subtract(amount));
		CompanyDataDao.updateCompanyBalance(creditorAccountNumber,
				creditorBalance.add(amount));

		// TODO save paymentData
	}
}
