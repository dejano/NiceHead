package rs.ac.uns.ftn.xws.ws.po;

import java.math.BigDecimal;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.jws.WebService;

import rs.ac.uns.ftn.xws.dao.CompanyDataDao;
import rs.ac.uns.ftn.xws.generated.cmn.BankDetails;
import rs.ac.uns.ftn.xws.generated.mp.MpExceptionEnum;
import rs.ac.uns.ftn.xws.generated.mp.Mt103;
import rs.ac.uns.ftn.xws.generated.mp.Mt900;
import rs.ac.uns.ftn.xws.generated.po.PaymentOrder;
import rs.ac.uns.ftn.xws.misc.BankUtil;
import rs.ac.uns.ftn.xws.misc.ObjectMapper;
import rs.ac.uns.ftn.xws.ws.client.bankDetails.BdDocumentClient;
import rs.ac.uns.ftn.xws.ws.client.bankDetails.NoBankCodeException;
import rs.ac.uns.ftn.xws.ws.client.mpcb.MpException;
import rs.ac.uns.ftn.xws.ws.client.mpcb.MpcbDocumentClient;

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

		if (BankUtil.areInTheSameBank(paymentOrderPart
				.getDebtorAccountDetails().getAccountNumber(), paymentOrderPart
				.getCreditorAccountDetails().getAccountNumber())) {
			// TODO provera da li postoje racuni

			transferFunds(paymentOrderPart);
		} else if (paymentOrderPart.isUrgent()
				|| paymentOrderPart.getAmount().compareTo(
						new BigDecimal(250000)) > 0) {	
			rtgs(paymentOrderPart);
		}


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
	
	private void rtgs(PaymentOrder paymentOrder){
		String debtorAccountNumber  = paymentOrder.getDebtorAccountDetails().getAccountNumber();
		String creditorAccountNumber  = paymentOrder.getCreditorAccountDetails().getAccountNumber();
		
		try {
			BankDetails debtorBankDetails = BdDocumentClient.getBankDetails(BankUtil.getBankCode(debtorAccountNumber));
			BankDetails creditorBankDetails = BdDocumentClient.getBankDetails(BankUtil.getBankCode(creditorAccountNumber));
			
			Mt103 mt103 = ObjectMapper.getMt103(paymentOrder, debtorBankDetails, creditorBankDetails);
			
			Mt900 rtgsResponse = MpcbDocumentClient.sendRtgsRequest(mt103);
			
			BigDecimal amount = paymentOrder.getAmount();

			// update debtor balance
			BigDecimal balance = CompanyDataDao
					.getCompanyBalance(debtorAccountNumber);
			CompanyDataDao.updateCompanyBalance(debtorAccountNumber,
					balance.subtract(amount));
			
//			BigDecimal reservedAmount = CompanyDataDao
//					.getCompanyReservedAmount(debtorAccountNumber);
//			CompanyDataDao.updateCompanyReservedAmount(debtorAccountNumber,
//					reservedAmount.subtract(amount));
			
			// TODO save payment
		} catch (NoBankCodeException e1) {
			// TODO handle error
		} catch (MpException e) {
			MpExceptionEnum error = e.getFaultInfo();

			// TODO handle error
			switch (error) {
			case DEBTOR_BANK_HAS_INSUFFICIENT_FUNDS:
				break;
			case INVALID_SWIFT_CODE:
				break;
			default:
				break;
			}
		}
	}
}
