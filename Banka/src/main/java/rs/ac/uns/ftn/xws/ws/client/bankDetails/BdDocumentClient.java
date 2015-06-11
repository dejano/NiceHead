package rs.ac.uns.ftn.xws.ws.client.bankDetails;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import rs.ac.uns.ftn.xws.generated.cmn.BankDetails;
import rs.ac.uns.ftn.xws.misc.BankConstants;

public class BdDocumentClient {
	public static final String CENTRAL_BANK_URL = ResourceBundle.getBundle(
			BankConstants.PROP_FILE_PATH).getString("centralBank.url");

	public static final String MPCB_WS_URL = "/services/BdDocument?wsdl";

	public static BankDetails getBankDetails(String bankCode)
			throws NoBankCodeException {
		BdDocument bankDetailsService = getService();

		return bankDetailsService.getBankDetails(bankCode);
	}

	private static BdDocument getService() {
		BdDocument ret = null;

		try {
			URL wsdl = new URL(CENTRAL_BANK_URL + MPCB_WS_URL);

			QName serviceName = new QName(
					"http://www.ftn.uns.ac.rs/xws/ws/bankDetails",
					"BdDocumentService");
			QName portName = new QName(
					"http://www.ftn.uns.ac.rs/xws/ws/bankDetails",
					"BdDocumentPort");

			Service service = Service.create(wsdl, serviceName);

			ret = service.getPort(portName, BdDocument.class);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return ret;
	}

	private BdDocumentClient() {
	}
}
