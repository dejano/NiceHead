package rs.ac.uns.ftn.xws.ws.po;

import java.math.BigDecimal;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.jws.WebService;

import rs.ac.uns.ftn.xws.dao.CompanyDataDao;
import rs.ac.uns.ftn.xws.dao.Mt102DataDao;
import rs.ac.uns.ftn.xws.dao.PaymentDataDao;
import rs.ac.uns.ftn.xws.dao.PaymentOrderDataDao;
import rs.ac.uns.ftn.xws.generated.cmn.BankDetails;
import rs.ac.uns.ftn.xws.generated.mp.MpExceptionEnum;
import rs.ac.uns.ftn.xws.generated.mp.Mt103;
import rs.ac.uns.ftn.xws.generated.mp.Mt900;
import rs.ac.uns.ftn.xws.generated.po.PaymentOrder;
import rs.ac.uns.ftn.xws.generated.po.PoExceptionEnum;
import rs.ac.uns.ftn.xws.misc.BankConstants;
import rs.ac.uns.ftn.xws.misc.BankUtil;
import rs.ac.uns.ftn.xws.misc.ObjectMapper;
import rs.ac.uns.ftn.xws.misc.XmlHelper;
import rs.ac.uns.ftn.xws.ws.client.bankDetails.BdDocumentClient;
import rs.ac.uns.ftn.xws.ws.client.bankDetails.NoBankCodeException;
import rs.ac.uns.ftn.xws.ws.client.mpcb.MpException;
import rs.ac.uns.ftn.xws.ws.client.mpcb.MpcbDocumentClient;

@Stateless
@WebService(
		serviceName = "PoDocumentService",
		portName = "PoDocumentPort",
		targetNamespace = "http://www.ftn.uns.ac.rs/xws/ws/po",
		wsdlLocation = "file:/C:/Users/Nikola/Documents/Fakultet/XWS/projekat/NiceHead/Banka/WEB-INF/wsdl/po.wsdl",
		endpointInterface = "rs.ac.uns.ftn.xws.ws.po.PoDocument")
public class PoDocumentImpl implements PoDocument {

	private static final Logger LOG = Logger.getLogger(PoDocumentImpl.class.getName());

	public void paymentOrderHandle(PaymentOrder paymentOrderPart) throws PoException {
		LOG.info("Executing operation paymentOrderHandle");

		if (!XmlHelper.validate(paymentOrderPart, BankConstants.XSD_PATH + "paymentOrder.xsd"))
			throw new PoException("Invalid xml.", PoExceptionEnum.INVALID_XML);

		String debtorAccountNumber = paymentOrderPart.getDebtorAccountDetails().getAccountNumber();
		String creditorAccountNumber = paymentOrderPart.getCreditorAccountDetails()
				.getAccountNumber();

		if (!CompanyDataDao.accountNumberExists(debtorAccountNumber))
			throw new PoException("Debtor account does not exist.",
					PoExceptionEnum.DEBTOR_ACCOUNT_DOES_NOT_EXIST);

		BigDecimal debtorBalance = CompanyDataDao.getCompanyBalance(debtorAccountNumber);
		BigDecimal reservedAmount = CompanyDataDao.getCompanyReservedAmount(debtorAccountNumber);
		if (paymentOrderPart.getAmount().compareTo(debtorBalance.subtract(reservedAmount)) > 0)
			throw new PoException("Debtor has insufficient funds.",
					PoExceptionEnum.DEBTOR_INSUFFICIENT_FUNDS);

		if (BankUtil.areInTheSameBank(debtorAccountNumber, creditorAccountNumber)) {
			if (!CompanyDataDao.accountNumberExists(creditorAccountNumber))
				throw new PoException("Creditor account does not exist.",
						PoExceptionEnum.CREDITOR_ACCOUNT_DOES_NOT_EXIST);

			LOG.info("Same bank, transfering funds.");
			
			transferFunds(paymentOrderPart);
		} else if (paymentOrderPart.isUrgent()
				|| paymentOrderPart.getAmount().compareTo(new BigDecimal(250000)) > 0) {
			LOG.info("Rtgs request.");
			rtgs(paymentOrderPart);
		} else {
			CompanyDataDao.updateCompanyReservedAmount(debtorAccountNumber,
					reservedAmount.add(paymentOrderPart.getAmount()));
			
			LOG.info("Saving payment order");
			PaymentOrderDataDao.addPaymentOrder(paymentOrderPart);
		}
	}

	private void transferFunds(PaymentOrder paymentOrder) throws PoException {
		BigDecimal amount = paymentOrder.getAmount();

		String debtorAccountNumber = paymentOrder.getDebtorAccountDetails().getAccountNumber();
		BigDecimal debtorBalance = CompanyDataDao.getCompanyBalance(debtorAccountNumber);

		String creditorAccountNumber = paymentOrder.getCreditorAccountDetails().getAccountNumber();
		BigDecimal creditorBalance = CompanyDataDao.getCompanyBalance(creditorAccountNumber);

		// transfer funds
		CompanyDataDao.updateCompanyBalance(debtorAccountNumber, debtorBalance.subtract(amount));
		CompanyDataDao.updateCompanyBalance(creditorAccountNumber, creditorBalance.add(amount));

		PaymentDataDao.addPayment(ObjectMapper.getPaymentData(paymentOrder, debtorBalance,
				debtorBalance.subtract(amount)));
		PaymentDataDao.addPayment(ObjectMapper.getPaymentData(paymentOrder, creditorBalance,
				debtorBalance.add(amount)));
	}

	private void rtgs(PaymentOrder paymentOrder) throws PoException {
		String debtorAccountNumber = paymentOrder.getDebtorAccountDetails().getAccountNumber();
		String creditorAccountNumber = paymentOrder.getCreditorAccountDetails().getAccountNumber();

		try {
			BankDetails debtorBankDetails = BdDocumentClient.getBankDetails(BankUtil
					.getBankCode(debtorAccountNumber));
			BankDetails creditorBankDetails = BdDocumentClient.getBankDetails(BankUtil
					.getBankCode(creditorAccountNumber));

			Mt103 mt103 = ObjectMapper.getMt103(paymentOrder, debtorBankDetails,
					creditorBankDetails);

			Mt900 rtgsResponse = MpcbDocumentClient.sendRtgsRequest(mt103);

			BigDecimal amount = paymentOrder.getAmount();

			// update debtor balance
			BigDecimal balance = CompanyDataDao.getCompanyBalance(debtorAccountNumber);
			CompanyDataDao.updateCompanyBalance(debtorAccountNumber, balance.subtract(amount));

			BigDecimal reservedAmount = CompanyDataDao
					.getCompanyReservedAmount(debtorAccountNumber);
			CompanyDataDao.updateCompanyReservedAmount(debtorAccountNumber,
					reservedAmount.subtract(amount));

			PaymentDataDao.addPayment(ObjectMapper.getPaymentData(paymentOrder, balance,
					balance.subtract(amount)));
		} catch (NoBankCodeException e1) {
			throw new PoException(e1.getMessage(), PoExceptionEnum.INVALID_BANK_CODE);
		} catch (MpException e) {
			MpExceptionEnum error = e.getFaultInfo();

			switch (error) {
			case DEBTOR_BANK_HAS_INSUFFICIENT_FUNDS:
				throw new PoException(e.getMessage(), PoExceptionEnum.DEBTOR_INSUFFICIENT_FUNDS);
			case INVALID_SWIFT_CODE:
				throw new PoException(e.getMessage(), PoExceptionEnum.INVALID_SWIFT_CODE);
			case INVALID_XML:
				throw new PoException(e.getMessage(), PoExceptionEnum.INVALID_XML);
			default:
				break;
			}
		}
	}
}
