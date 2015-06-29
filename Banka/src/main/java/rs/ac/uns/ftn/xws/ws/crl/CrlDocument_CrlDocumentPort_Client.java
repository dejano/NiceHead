package rs.ac.uns.ftn.xws.ws.crl;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.handler.Handler;

import rs.ac.uns.ftn.xws.handler.SecMessageHandler;
import rs.ac.uns.ftn.xws.handler.client.mpcb.ClientCryptoHandler;
import rs.ac.uns.ftn.xws.handler.client.mpcb.ClientSignatureHandler;

public final class CrlDocument_CrlDocumentPort_Client {

	private CrlDocument_CrlDocumentPort_Client() {
	}

	public static boolean isCertificateRevoked(BigInteger certSerialNumber) throws MalformedURLException {
		boolean retBool = false;
		
			URL wsdl = new URL("http://localhost:8080/banka2/services/CrlDocument?wsdl");

			QName serviceName = new QName("http://www.ftn.uns.ac.rs/xws/ws/crl",
					"CrlDocumentService");
			QName portName = new QName("http://www.ftn.uns.ac.rs/xws/ws/crl", "CrlDocumentPort");

			Service service = Service.create(wsdl, serviceName);

			CrlDocument crlService = service.getPort(portName, CrlDocument.class);

//			SecMessageHandler secMessage = new SecMessageHandler();
//			ClientCryptoHandler crypto = new ClientCryptoHandler();
//			ClientSignatureHandler sign = new ClientSignatureHandler();

//			@SuppressWarnings("rawtypes")
//			List<Handler> handlerChain = new ArrayList<Handler>();
//			handlerChain.add(secMessage);
//			handlerChain.add(sign);
//			handlerChain.add(crypto);
//
//			((BindingProvider) crlService).getBinding().setHandlerChain(handlerChain);
			
			String serialNumber = certSerialNumber.toString();
			
			retBool = crlService.isInCrl(serialNumber);
			//System.out.println(crlService.isInCrl(serialNumber));
		return retBool;
	}
	
	
	public static void main(String args[]) throws java.lang.Exception {
		
	}

}
