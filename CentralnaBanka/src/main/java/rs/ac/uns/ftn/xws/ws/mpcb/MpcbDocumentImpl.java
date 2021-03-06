package rs.ac.uns.ftn.xws.ws.mpcb;

import java.math.BigDecimal;
import java.util.logging.Logger;

import javax.ejb.Stateless;

import rs.ac.uns.ftn.xws.dao.BanksDataDao;
import rs.ac.uns.ftn.xws.dao.ClearingDataDao;
import rs.ac.uns.ftn.xws.generated.mp.MpExceptionEnum;
import rs.ac.uns.ftn.xws.generated.mp.Mt102;
import rs.ac.uns.ftn.xws.generated.mp.Mt103;
import rs.ac.uns.ftn.xws.generated.mp.Mt900;
import rs.ac.uns.ftn.xws.misc.CentralBankConstants;
import rs.ac.uns.ftn.xws.misc.CentralBankUtil;
import rs.ac.uns.ftn.xws.misc.ObjectMapper;
import rs.ac.uns.ftn.xws.misc.XmlHelper;
import rs.ac.uns.ftn.xws.ws.client.mpb.MpbDocumentClient;
@Stateless
@javax.jws.WebService(
                      serviceName = "MpcbDocumentService",
                      portName = "MpcbDocumentPort",
                      targetNamespace = "http://www.ftn.uns.ac.rs/xws/ws/mpcb",
                      wsdlLocation = "file:/C:/Users/Nikola/Documents/Fakultet/XWS/projekat/NiceHead/CentralnaBanka/WEB-INF/wsdl/mpcb.wsdl",
                      endpointInterface = "rs.ac.uns.ftn.xws.ws.mpcb.MpcbDocument")
                      
public class MpcbDocumentImpl implements MpcbDocument {

    private static final Logger LOG = Logger.getLogger(MpcbDocumentImpl.class.getName());

	public Mt900 rtgsRequest(Mt103 rtgsRequestPart) throws MpException {
		LOG.info("Executing operation rtgsRequest");

		if(!XmlHelper.validate(rtgsRequestPart, CentralBankConstants.XSD_PATH + "mp.xsd"))
			throw new MpException("Invalid xml.", MpExceptionEnum.INVALID_XML);
			
		
		BigDecimal debtorBankBalance;
		BigDecimal creditorBankBalance;
		BigDecimal amount = rtgsRequestPart.getAmount();

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

		// validate debtor bank funds
		if (!CentralBankUtil.getBankCanPayAmount(debtorBankSwiftCode, amount)) {
			throw new MpException("Debtor bank has insufficien funds.",
					MpExceptionEnum.DEBTOR_BANK_HAS_INSUFFICIENT_FUNDS);
		}

		// update debtor balance
		debtorBankBalance = BanksDataDao.getBankBalance(debtorBankSwiftCode);
		BanksDataDao.updateBankBalance(debtorBankSwiftCode,
				debtorBankBalance.subtract(amount));

		// update creditor balance
		creditorBankBalance = BanksDataDao
				.getBankBalance(creditorBankSwiftCode);
		BanksDataDao.updateBankBalance(creditorBankSwiftCode,
				creditorBankBalance.add(amount));

		// send rtgs confirm message to creditor's bank
		MpbDocumentClient.invokeRtgsApproval(rtgsRequestPart);

		return ObjectMapper.getMt900(rtgsRequestPart);
	}

	public void clearingRequest(Mt102 clearingRequestPart) throws MpException {
		LOG.info("Executing operation clearingRequest");

		System.out.println("Clearing request ws called, mt102 : " + clearingRequestPart.getMessageId());

		if(!XmlHelper.validate(clearingRequestPart, CentralBankConstants.XSD_PATH + "mp.xsd"))
			throw new MpException("Invalid xml.", MpExceptionEnum.INVALID_XML);
		
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

		// validate are all payments to the same bank
		if (!CentralBankUtil.areAllPaymentsToSameBank(clearingRequestPart)) {
			throw new MpException("There are invalid payments.",
					MpExceptionEnum.MULTIPLE_BANKS);
		}

		// validate total amount
		if (!CentralBankUtil.isTotalAmountValid(clearingRequestPart)) {
			throw new MpException("Invalid total amount.",
					MpExceptionEnum.INVALID_AMOUNT);
		}

		// insert mt102 into db for later clearing
		ClearingDataDao.insertMt102(XmlHelper.marshall(clearingRequestPart));
	}

}
