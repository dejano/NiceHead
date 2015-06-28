package rs.ac.uns.ftn.xws.ws.mpb.bankdetails;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.handler.Handler;

import rs.ac.uns.ftn.xws.generated.cmn.BankDetails;
import rs.ac.uns.ftn.xws.handler.client.mpcb.ClientCryptoHandler;
import rs.ac.uns.ftn.xws.handler.client.mpcb.ClientSignatureHandler;

public final class BdDocument_BdDocumentPort_Client {

	public static BankDetails getBankDetails(String bankCode) throws Exception {
		BankDetails bd = null;
		BdDocument bdService = null;
		
		URL wsdl = new URL(
				"http://localhost:8080/cb/services/BdDocument?wsdl");

		QName serviceName = new QName(
				"http://www.ftn.uns.ac.rs/xws/ws/bankDetails",
				"BdDocumentService");
		QName portName = new QName(
				"http://www.ftn.uns.ac.rs/xws/ws/bankDetails",
				"BdDocumentPort");

		Service service = Service.create(wsdl, serviceName);

		bdService = service.getPort(portName, BdDocument.class);
		
		ClientCryptoHandler crypto = new ClientCryptoHandler();
		ClientSignatureHandler sing = new ClientSignatureHandler();
		
		@SuppressWarnings("rawtypes")
		List<Handler> handlerChain = new ArrayList<Handler>();
		//handlerChain.add(attack);
		handlerChain.add(sing);
		handlerChain.add(crypto);
		
		
		((BindingProvider) bdService).getBinding().setHandlerChain(handlerChain);
		
		bd = bdService.getBankDetails(bankCode);

		System.out.println(bd.getSwiftCode() + "   "
				+ bd.getBankClearingAccountNumber());
		
		return bd;
	}
}
