package rs.ac.uns.ftn.xws.ws.po;

import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.handler.Handler;

import rs.ac.uns.ftn.xws.generated.po.PaymentOrder;
import rs.ac.uns.ftn.xws.handler.ClientSecMessageHandler;
import rs.ac.uns.ftn.xws.handler.client.mpcb.ClientCryptoHandler;
import rs.ac.uns.ftn.xws.handler.client.mpcb.ClientSignatureHandler;
import rs.ac.uns.ftn.xws.misc.CertMap;
import rs.ac.uns.ftn.xws.misc.XmlHelper;

public final class PoDocument_PoDocumentPort_Client {

	public static void main(String args[]) throws java.lang.Exception {
		try {
			URL wsdl = new URL("http://localhost:8080/banka2/services/PoDocument?wsdl");

			QName serviceName = new QName("http://www.ftn.uns.ac.rs/xws/ws/po", "PoDocumentService");
			QName portName = new QName("http://www.ftn.uns.ac.rs/xws/ws/po", "PoDocumentPort");

			Service service = Service.create(wsdl, serviceName);

			PoDocument poService = service.getPort(portName, PoDocument.class);
			
			ClientSecMessageHandler secMessage = new ClientSecMessageHandler();
			ClientCryptoHandler crypto = new ClientCryptoHandler();
			ClientSignatureHandler sign = new ClientSignatureHandler();

			@SuppressWarnings("rawtypes")
			List<Handler> handlerChain = new ArrayList<Handler>();
			handlerChain.add(secMessage);
			handlerChain.add(sign);
			handlerChain.add(crypto);

			((BindingProvider) poService).getBinding().setHandlerChain(handlerChain);

			File file = new File("src/main/resources/");
			FileInputStream fis = new FileInputStream(new File(file, "paymentOrder.xml"));
			PaymentOrder paymentOrder = XmlHelper.unmarshall(fis, PaymentOrder.class);
			
			try {
				CertMap.add(paymentOrder, "banka2");
				
				poService.paymentOrderHandle(paymentOrder);
			} catch (PoException e) {
				System.out.println(e.getFaultInfo());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
