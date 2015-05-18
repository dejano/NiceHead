/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package rs.ac.uns.ftn.xws.ws.swiftcode;

import java.util.logging.Logger;

import javax.ejb.Stateless;

import rs.ac.uns.ftn.xws.dao.BanksDataDao;
import rs.ac.uns.ftn.xws.util.CentralBankUtil;

/**
 * This class was generated by Apache CXF 2.6.5 2015-05-16T22:10:49.128+02:00
 * Generated source version: 2.6.5
 * 
 */

@Stateless
@javax.jws.WebService(serviceName = "SwiftCodeDocumentService", portName = "SwiftCodeDocumentPort", targetNamespace = "http://www.ftn.uns.ac.rs/xws/ws/swiftCode", wsdlLocation = "file:/C:/Users/nikola42/Documents/Fakultet/XWS/projekat/NiceHead/CentralnaBanka/WEB-INF/wsdl/swiftCode.wsdl", endpointInterface = "rs.ac.uns.ftn.xws.ws.swiftcode.SwiftCodeDocument")
public class SwiftCodeDocumentImpl implements SwiftCodeDocument {

	private static final Logger LOG = Logger
			.getLogger(SwiftCodeDocumentImpl.class.getName());

	public java.lang.String getSwiftCode(
			java.lang.String bankClearingAccountNumber) {
		String swiftCode;
		BanksDataDao bdd = new BanksDataDao();

		LOG.info("scws - check for AN : " + bankClearingAccountNumber);
		swiftCode = bdd.getBankSwiftCode(bankClearingAccountNumber);

		if (swiftCode == null || swiftCode.isEmpty()) {
			String altFormAccountNumber = CentralBankUtil
					.getAccountNumberAltForm(bankClearingAccountNumber);

			LOG.info("scws - check for alternative form AN : "
					+ bankClearingAccountNumber);

			swiftCode = bdd.getBankSwiftCode(altFormAccountNumber);
		}

		LOG.info("scws - swift code : " + swiftCode);

		if (swiftCode == null || swiftCode.isEmpty()) {
			// TODO throw exception
		}

		return swiftCode;
	}
}
