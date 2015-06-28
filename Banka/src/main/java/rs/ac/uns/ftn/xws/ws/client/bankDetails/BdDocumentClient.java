package rs.ac.uns.ftn.xws.ws.client.bankDetails;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.handler.Handler;

import rs.ac.uns.ftn.xws.generated.cmn.BankDetails;
import rs.ac.uns.ftn.xws.handler.client.mpcb.ClientCryptoHandler;
import rs.ac.uns.ftn.xws.handler.client.mpcb.ClientSignatureHandler;
import rs.ac.uns.ftn.xws.misc.BankConstants;

public class BdDocumentClient {
	public static final String CENTRAL_BANK_URL = ResourceBundle.getBundle(
			BankConstants.PROP_FILE_PATH).getString("centralBank.url");

	public static final String MPCB_WS_URL = "/services/BdDocument?wsdl";

	public static BankDetails getBankDetails(String bankCode)
			throws NoBankCodeException {
		BdDocument bankDetailsService = getService();
		
		ClientCryptoHandler crypto = new ClientCryptoHandler();
		ClientSignatureHandler sing = new ClientSignatureHandler();
		
		@SuppressWarnings("rawtypes")
		List<Handler> handlerChain = new ArrayList<Handler>();
		//handlerChain.add(attack);
		handlerChain.add(sing);
		handlerChain.add(crypto);
		
		((BindingProvider) bankDetailsService).getBinding().setHandlerChain(handlerChain);

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
