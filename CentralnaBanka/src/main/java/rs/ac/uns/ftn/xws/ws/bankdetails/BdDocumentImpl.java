package rs.ac.uns.ftn.xws.ws.bankdetails;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.jws.WebService;

import rs.ac.uns.ftn.xws.dao.BanksDataDao;
import rs.ac.uns.ftn.xws.generated.cmn.BankDetails;

@Stateless
@WebService(serviceName = "BdDocumentService", portName = "BdDocumentPort", targetNamespace = "http://www.ftn.uns.ac.rs/xws/ws/bankDetails", wsdlLocation = "file:/C:/Users/nikola42/Documents/Fakultet/XWS/projekat/NiceHead/CentralnaBanka/WEB-INF/wsdl/bankDetails.wsdl", endpointInterface = "rs.ac.uns.ftn.xws.ws.bankdetails.BdDocument")
public class BdDocumentImpl implements BdDocument {

	private static final Logger LOG = Logger.getLogger(BdDocumentImpl.class
			.getName());

	public BankDetails getBankDetails(String bankCodePart)
			throws NoBankCodeException {
		LOG.info("Executing operation getBankDetails");
		BankDetails ret = BanksDataDao.getBankDetails(bankCodePart);

		if (ret == null)
			throw new NoBankCodeException("Error",
					"No bank details for bank code : " + bankCodePart);

		return ret;
	}

}
