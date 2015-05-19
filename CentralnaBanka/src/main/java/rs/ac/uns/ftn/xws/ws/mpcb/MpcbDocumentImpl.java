/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package rs.ac.uns.ftn.xws.ws.mpcb;

import java.math.BigDecimal;
import java.util.logging.Logger;

import javax.ejb.Stateless;

import rs.ac.uns.ftn.xws.dao.BanksDataDao;
import rs.ac.uns.ftn.xws.generated.MpExceptionEnum;
import rs.ac.uns.ftn.xws.generated.Mt102;
import rs.ac.uns.ftn.xws.generated.Mt103;
import rs.ac.uns.ftn.xws.generated.Mt900;
import rs.ac.uns.ftn.xws.util.CentralBankUtil;
import rs.ac.uns.ftn.xws.util.ObjectFactory;

@Stateless
@javax.jws.WebService(serviceName = "MpcbDocumentService", portName = "MpcbDocumentPort", targetNamespace = "http://www.ftn.uns.ac.rs/xws/ws/mpcb", wsdlLocation = "file:/C:/Users/nikola42/Documents/Fakultet/XWS/projekat/NiceHead/CentralnaBanka/WEB-INF/wsdl/mpcb.wsdl", endpointInterface = "rs.ac.uns.ftn.xws.ws.mpcb.MpcbDocument")
public class MpcbDocumentImpl implements MpcbDocument {

	private static final Logger LOG = Logger.getLogger(MpcbDocumentImpl.class
			.getName());

	public Mt900 rtgsRequest(Mt102 rtgsRequestPart) throws MpException {
		LOG.info("Executing operation rtgsRequest");

		BigDecimal debtorBankBalance;
		BigDecimal creditorBankBalance;
		BigDecimal amount = rtgsRequestPart.getTotalAmount();

		BanksDataDao bdd = new BanksDataDao();

		String debtorBankSwiftCode = rtgsRequestPart.getDebtorBankDetails()
				.getSwiftCode();
		String creditorBankSwiftCode = rtgsRequestPart.getCreditorBankDetails()
				.getSwiftCode();

		// validate swift codes
		if (!CentralBankUtil.isSwiftCodeValid(debtorBankSwiftCode)) {
			throw new MpException("Debtor swift code invalid.",
					MpExceptionEnum.INVALID_SWIFT_CODE);
		}

		if (!CentralBankUtil.isSwiftCodeValid(creditorBankSwiftCode)) {
			throw new MpException("Creditor swift code invalid.",
					MpExceptionEnum.INVALID_SWIFT_CODE);
		}

		// validate are all payments to the same bank
		if (!CentralBankUtil.areAllPaymentsToSameBank(rtgsRequestPart)) {
			throw new MpException("There are invalid payments.",
					MpExceptionEnum.MULTIPLE_BANKS);
		}

		// validate total amount
		if (!CentralBankUtil.isTotalAmountValid(rtgsRequestPart)) {
			throw new MpException("Invalid total amount.",
					MpExceptionEnum.INVALID_AMOUNT);
		}

		// update debtor balance
		debtorBankBalance = bdd.getBankBalance(debtorBankSwiftCode);
		bdd.updateBankBalance(debtorBankSwiftCode,
				debtorBankBalance.subtract(amount));

		// update creditor balance
		creditorBankBalance = bdd.getBankBalance(creditorBankSwiftCode);
		bdd.updateBankBalance(creditorBankSwiftCode,
				creditorBankBalance.add(amount));

		return ObjectFactory.getMt900(rtgsRequestPart);
	}

	public Mt900 clearingRequest(Mt103 clearingRequestPart) throws MpException {
		LOG.info("Executing operation clearingRequest");

		BigDecimal debtorBankBalance;
		BigDecimal creditorBankBalance;
		BigDecimal amount = clearingRequestPart.getAmount();

		BanksDataDao bdd = new BanksDataDao();

		String debtorBankSwiftCode = clearingRequestPart.getDebtorBankDetails()
				.getSwiftCode();
		String creditorBankSwiftCode = clearingRequestPart
				.getCreditorBankDetails().getSwiftCode();

		// validate swift codes
		if (!CentralBankUtil.isSwiftCodeValid(debtorBankSwiftCode)) {
			throw new MpException("Debtor swift code invalid.",
					MpExceptionEnum.INVALID_SWIFT_CODE);
		}

		if (!CentralBankUtil.isSwiftCodeValid(creditorBankSwiftCode)) {
			throw new MpException("Creditor swift code invalid.",
					MpExceptionEnum.INVALID_SWIFT_CODE);
		}

		// update debtor balance
		debtorBankBalance = bdd.getBankBalance(debtorBankSwiftCode);
		bdd.updateBankBalance(debtorBankSwiftCode,
				debtorBankBalance.subtract(amount));

		// update creditor balance
		creditorBankBalance = bdd.getBankBalance(creditorBankSwiftCode);
		bdd.updateBankBalance(creditorBankSwiftCode,
				creditorBankBalance.add(amount));

		return ObjectFactory.getMt900(clearingRequestPart);
	}

}
