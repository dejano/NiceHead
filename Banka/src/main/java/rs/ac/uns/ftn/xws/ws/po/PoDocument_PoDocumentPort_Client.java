package rs.ac.uns.ftn.xws.ws.po;

import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import rs.ac.uns.ftn.xws.generated.po.PaymentOrder;
import rs.ac.uns.ftn.xws.misc.XmlHelper;

public final class PoDocument_PoDocumentPort_Client {

	public static void main(String args[]) throws java.lang.Exception {
		try {
			URL wsdl = new URL("http://localhost:8080/banka2/services/PoDocument?wsdl");

			QName serviceName = new QName("http://www.ftn.uns.ac.rs/xws/ws/po", "PoDocumentService");
			QName portName = new QName("http://www.ftn.uns.ac.rs/xws/ws/po", "PoDocumentPort");

			Service service = Service.create(wsdl, serviceName);

			PoDocument poService = service.getPort(portName, PoDocument.class);

			File file = new File("src/main/resources/");
			FileInputStream fos = new FileInputStream(new File(file, "paymentOrder.xml"));
			PaymentOrder paymentOrder = XmlHelper.unmarshall(fos, PaymentOrder.class);
			try {
				poService.paymentOrderHandle(paymentOrder);
			} catch (PoException e) {
				System.out.println(e.getFaultInfo());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
