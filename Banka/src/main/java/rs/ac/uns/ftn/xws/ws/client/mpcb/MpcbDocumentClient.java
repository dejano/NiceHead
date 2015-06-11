package rs.ac.uns.ftn.xws.ws.client.mpcb;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import rs.ac.uns.ftn.xws.generated.mp.MpExceptionEnum;
import rs.ac.uns.ftn.xws.generated.mp.Mt103;
import rs.ac.uns.ftn.xws.generated.mp.Mt900;
import rs.ac.uns.ftn.xws.misc.BankConstants;

public class MpcbDocumentClient {
	public static final String CENTRAL_BANK_URL = ResourceBundle.getBundle(
			BankConstants.PROP_FILE_PATH).getString("centralBank.url");

	public static final String MPCB_WS_URL = "/services/MpcbDocument?wsdl";

	public static Mt900 sendRtgsRequest(Mt103 mt103) throws MpException {
		MpcbDocument mpcbService = getService();

		return mpcbService.rtgsRequest(mt103);
	}

	private static MpcbDocument getService() {
		MpcbDocument ret = null;

		try {
			URL wsdl = new URL(CENTRAL_BANK_URL + MPCB_WS_URL);

			QName serviceName = new QName(
					"http://www.ftn.uns.ac.rs/xws/ws/mpcb",
					"MpcbDocumentService");
			QName portName = new QName("http://www.ftn.uns.ac.rs/xws/ws/mpcb",
					"MpcbDocumentPort");

			Service service = Service.create(wsdl, serviceName);

			ret = service.getPort(portName, MpcbDocument.class);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return ret;
	}

	private MpcbDocumentClient() {
	}
}
