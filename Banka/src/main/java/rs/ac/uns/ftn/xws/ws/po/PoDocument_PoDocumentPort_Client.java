
package rs.ac.uns.ftn.xws.ws.po;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import rs.ac.uns.ftn.xws.generated.po.PaymentOrder;

public final class PoDocument_PoDocumentPort_Client {


    public static void main(String args[]) throws java.lang.Exception {
    	try {
			URL wsdl = new URL(
					"http://localhost:8080/banka2/services/PoDocument?wsdl");

			QName serviceName = new QName(
					"http://www.ftn.uns.ac.rs/xws/ws/po",
					"PoDocumentService");
			QName portName = new QName(
					"http://www.ftn.uns.ac.rs/xws/ws/po",
					"PoDocumentPort");

			Service service = Service.create(wsdl, serviceName);

			PoDocument poService = service.getPort(portName,
					PoDocument.class);
			
			try {
				poService.paymentOrderHandle(new PaymentOrder());
			} catch (PoException e) {
				System.out.println(e.getFaultInfo());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
    }

}
